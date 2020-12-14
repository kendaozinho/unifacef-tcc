package com.unifacef.tcc.controller.v1.dto;

import com.unifacef.tcc.model.Skill;

public class SkillDto {
  private Integer id;
  private String name;

  public SkillDto() {
  }

  public SkillDto(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public Skill toModel() {
    return new Skill(this.id, this.name);
  }
}
