package com.unifacef.tcc.controller.v1.dto;

import com.unifacef.tcc.model.DeveloperSkill;

public class DeveloperSkillDto {
  private DeveloperDto developer;
  private SkillDto skill;

  public DeveloperSkillDto() {
  }

  public DeveloperSkillDto(DeveloperDto developer, SkillDto skill) {
    this.developer = developer;
    this.skill = skill;
  }

  public DeveloperDto getDeveloper() {
    return this.developer;
  }

  public SkillDto getSkill() {
    return this.skill;
  }

  public DeveloperSkill toModel() {
    return new DeveloperSkill(
        this.developer == null ? null : this.developer.getId(),
        this.skill == null ? null : this.skill.getId()
    );
  }
}
