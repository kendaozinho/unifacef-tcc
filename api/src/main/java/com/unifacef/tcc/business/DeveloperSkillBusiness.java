package com.unifacef.tcc.business;

import com.unifacef.tcc.controller.v1.dto.DeveloperSkillDto;
import com.unifacef.tcc.exception.ConflictException;
import com.unifacef.tcc.exception.NotFoundException;
import com.unifacef.tcc.model.Developer;
import com.unifacef.tcc.model.DeveloperSkill;
import com.unifacef.tcc.model.Skill;
import com.unifacef.tcc.repository.DeveloperRepository;
import com.unifacef.tcc.repository.DeveloperSkillRepository;
import com.unifacef.tcc.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeveloperSkillBusiness {
  @Autowired
  private DeveloperSkillRepository repository;
  @Autowired
  private DeveloperRepository developerRepository;
  @Autowired
  private SkillRepository skillRepository;

  public ArrayList<DeveloperSkillDto> get(Integer developerId, Integer skillId) {
    List<DeveloperSkill> developersAndSkills = new ArrayList<>();

    if (developerId != null && skillId != null) {
      developersAndSkills.add(this.repository.findOneByDeveloperIdAndSkillId(developerId, skillId));
    } else if (developerId != null) {
      developersAndSkills.addAll(this.repository.findAllByDeveloperId(developerId));
    } else if (skillId != null) {
      developersAndSkills.addAll(this.repository.findAllBySkillId(skillId));
    } else {
      developersAndSkills.addAll(this.repository.findAll());
    }

    if (developersAndSkills.isEmpty()) {
      throw new NotFoundException("Developer Skill not found");
    }

    return developersAndSkills.stream().map(this::parseModelToDto).collect(Collectors.toCollection(ArrayList::new));
  }

  public DeveloperSkillDto post(DeveloperSkillDto request) {
    Optional<Developer> developer =
        this.developerRepository.findById(request.getDeveloper() == null ? 0 : request.getDeveloper().getId());

    if (!developer.isPresent()) {
      throw new NotFoundException("Developer not found");
    }

    Optional<Skill> skill =
        this.skillRepository.findById(request.getSkill() == null ? 0 : request.getSkill().getId());

    if (!skill.isPresent()) {
      throw new NotFoundException("Skill not found");
    }

    DeveloperSkill developerSkill =
        this.repository.findOneByDeveloperIdAndSkillId(request.getDeveloper().getId(), request.getSkill().getId());

    if (developerSkill != null) {
      throw new ConflictException("Developer Skill already exists");
    }

    return this.parseModelToDto(
        this.repository.saveAndFlush(request.toModel())
    );
  }

  public void delete(Integer developerId, Integer skillId) {
    DeveloperSkill developerSkill = this.repository.findOneByDeveloperIdAndSkillId(developerId, skillId);

    if (developerSkill == null) {
      throw new NotFoundException("Developer Skill not found");
    }

    this.repository.delete(developerSkill);
  }

  public DeveloperSkillDto parseModelToDto(DeveloperSkill developerSkill) {
    return new DeveloperSkillDto(
        this.developerRepository.findById(developerSkill.getDeveloperId()).get().toDto(),
        this.skillRepository.findById(developerSkill.getSkillId()).get().toDto()
    );
  }
}