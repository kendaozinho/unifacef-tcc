package com.unifacef.tcc.business;

import com.unifacef.tcc.controller.v1.dto.SkillDto;
import com.unifacef.tcc.exception.ConflictException;
import com.unifacef.tcc.exception.NotFoundException;
import com.unifacef.tcc.exception.UnprocessableEntityException;
import com.unifacef.tcc.model.Skill;
import com.unifacef.tcc.repository.DeveloperSkillRepository;
import com.unifacef.tcc.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class SkillBusiness {
  @Autowired
  private SkillRepository repository;
  @Autowired
  private DeveloperSkillRepository developerSkillRepository;

  public ArrayList<SkillDto> getAll(Integer id, String name) {
    ArrayList<Skill> skills = new ArrayList<>();

    if (id != null) {
      Skill skill = this.repository.findOneById(id);

      if (skill != null) {
        skills.add(skill);
      }
    } else if (name != null && !name.trim().isEmpty()) {
      Skill skill = this.repository.findOneByName(name);

      if (skill != null) {
        skills.add(skill);
      }
    } else {
      skills.addAll(this.repository.findAll());
    }

    if (skills.isEmpty()) {
      throw new NotFoundException("Skill not found");
    }

    return skills.stream().map(Skill::toDto).collect(Collectors.toCollection(ArrayList::new));
  }

  public SkillDto getById(Integer id) {
    Skill skill = this.repository.findOneById(id);

    if (skill == null) {
      throw new NotFoundException("Skill not found");
    }

    return skill.toDto();
  }

  public SkillDto post(SkillDto request) {
    request.setId(null);

    Skill skill = this.repository.findOneByName(request.getName());

    if (skill != null) {
      throw new ConflictException("Skill already exists");
    }

    return this.repository.saveAndFlush(request.toModel()).toDto();
  }

  public SkillDto put(Integer id, SkillDto request) {
    request.setId(id);

    Skill skill = this.repository.findOneById(id);

    if (skill == null) {
      throw new NotFoundException("Skill not found");
    }

    Skill existingSkill = this.repository.findOneByName(request.getName());

    if (existingSkill != null && !existingSkill.getId().equals(id)) {
      throw new ConflictException("Skill already exists");
    }

    skill.setName(request.getName());

    this.repository.saveAndFlush(skill);

    return skill.toDto();
  }

  public void delete(Integer id) {
    Skill skill = this.repository.findOneById(id);

    if (skill == null) {
      throw new NotFoundException("Skill not found");
    }

    if (!this.developerSkillRepository.findAllBySkillId(id).isEmpty()) {
      throw new UnprocessableEntityException("Skill is being used");
    }

    this.repository.delete(skill);
  }
}