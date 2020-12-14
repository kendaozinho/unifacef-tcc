package com.unifacef.tcc.controller.v1.dto;

import com.unifacef.tcc.model.Developer;

public class DeveloperDto {
  private Integer id;
  private String name;

  public DeveloperDto() {
  }

  public DeveloperDto(Integer id, String name) {
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

  public Developer toModel() {
    return new Developer(this.id, this.name);
  }
}
