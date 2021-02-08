package com.unifacef.tcc.business;

import com.unifacef.tcc.controller.v1.dto.DeveloperSkillDto;
import com.unifacef.tcc.exception.BadRequestException;
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
import java.util.stream.Collectors;

@Service
public class DeveloperSkillBusiness {
  @Autowired
  private DeveloperSkillRepository repository;
  @Autowired
  private DeveloperRepository developerRepository;
  @Autowired
  private SkillRepository skillRepository;

  public ArrayList<DeveloperSkillDto> getAll(Integer developerId, Integer skillId) {
    List<DeveloperSkill> developersAndSkills = new ArrayList<>();

    if (developerId != null && skillId != null) {
      DeveloperSkill developerSkill = this.repository.findOneByDeveloperIdAndSkillId(developerId, skillId);

      if (developerSkill != null) {
        developersAndSkills.add(developerSkill);
      }
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

  public DeveloperSkillDto getById(Integer developerId, Integer skillId) {
    if (developerId == null || developerId <= 0) {
      throw new BadRequestException("Invalid developerId");
    }

    if (skillId == null || skillId <= 0) {
      throw new BadRequestException("Invalid skillId");
    }

    DeveloperSkill developerSkill = this.repository.findOneByDeveloperIdAndSkillId(developerId, skillId);

    if (developerSkill == null) {
      throw new NotFoundException("Developer Skill not found");
    }

    return this.parseModelToDto(developerSkill);
  }

  public DeveloperSkillDto post(DeveloperSkillDto request) {
    Developer developer =
        this.developerRepository.findOneById(request.getDeveloper() == null ? 0 : request.getDeveloper().getId());

    if (developer == null) {
      throw new NotFoundException("Developer not found");
    }

    Skill skill =
        this.skillRepository.findOneById(request.getSkill() == null ? 0 : request.getSkill().getId());

    if (skill == null) {
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
    if (developerId == null || developerId <= 0) {
      throw new BadRequestException("Invalid developerId");
    }

    if (skillId == null || skillId <= 0) {
      throw new BadRequestException("Invalid skillId");
    }

    DeveloperSkill developerSkill = this.repository.findOneByDeveloperIdAndSkillId(developerId, skillId);

    if (developerSkill == null) {
      throw new NotFoundException("Developer Skill not found");
    }

    this.repository.delete(developerSkill);
  }

  public DeveloperSkillDto parseModelToDto(DeveloperSkill developerSkill) {
    return new DeveloperSkillDto(
        this.developerRepository.findOneById(developerSkill.getDeveloperId()).toDto(),
        this.skillRepository.findOneById(developerSkill.getSkillId()).toDto()
    );
  }
}