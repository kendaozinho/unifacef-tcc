package com.unifacef.tcc.business;

import com.unifacef.tcc.controller.v1.dto.SkillDto;
import com.unifacef.tcc.exception.ConflictException;
import com.unifacef.tcc.exception.NotFoundException;
import com.unifacef.tcc.model.Skill;
import com.unifacef.tcc.repository.DeveloperSkillRepository;
import com.unifacef.tcc.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
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
      this.repository.findById(id).ifPresent(skills::add);
    } else if (name != null && !name.trim().isEmpty()) {
      skills.add(this.repository.findOneByName(name));
    } else {
      skills.addAll(this.repository.findAll());
    }

    if (skills.isEmpty()) {
      throw new NotFoundException("Skill not found");
    }

    return skills.stream().map(Skill::toDto).collect(Collectors.toCollection(ArrayList::new));
  }

  public SkillDto getById(Integer id) {
    Optional<Skill> skill = this.repository.findById(id);

    if (!skill.isPresent()) {
      throw new NotFoundException("Skill not found");
    }

    return skill.get().toDto();
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

    Optional<Skill> skill = this.repository.findById(id);

    if (!skill.isPresent()) {
      throw new NotFoundException("Skill not found");
    }

    Skill existingSkill = this.repository.findOneByName(request.getName());

    if (existingSkill != null && !existingSkill.getId().equals(id)) {
      throw new ConflictException("Skill already exists");
    }

    return this.repository.saveAndFlush(request.toModel()).toDto();
  }

  public void delete(Integer id) {
    Optional<Skill> skill = this.repository.findById(id);

    if (!skill.isPresent()) {
      throw new NotFoundException("Skill not found");
    }

    this.developerSkillRepository.findAllBySkillId(id).forEach(
        developerSkill -> this.developerSkillRepository.delete(developerSkill)
    );

    this.repository.delete(skill.get());
  }
}