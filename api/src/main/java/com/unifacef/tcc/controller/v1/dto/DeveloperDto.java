package com.unifacef.tcc.controller.v1.dto;

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

  public String getName() {
    return this.name;
  }
}
