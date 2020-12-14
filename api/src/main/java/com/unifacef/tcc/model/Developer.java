package com.unifacef.tcc.model;

import com.unifacef.tcc.controller.v1.dto.DeveloperDto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "developer")
public class Developer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Developer() {
  }

  public Developer(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @PrePersist
  private void setCreatedAt() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  private void setUpdatedAt() {
    this.updatedAt = LocalDateTime.now();
  }

  public DeveloperDto toDto() {
    return new DeveloperDto(this.id, this.name);
  }
}
