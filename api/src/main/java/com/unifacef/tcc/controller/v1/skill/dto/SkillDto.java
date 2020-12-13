package com.unifacef.tcc.controller.v1.skill.dto;

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

  public String getName() {
    return this.name;
  }
}
