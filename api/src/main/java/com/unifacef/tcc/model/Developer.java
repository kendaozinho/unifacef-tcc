package com.unifacef.tcc.model;

import com.unifacef.tcc.controller.v1.dto.DeveloperDto;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
  @CreatedDate
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column(name = "updated_at")
  @LastModifiedDate
  private LocalDateTime updatedAt;

  @PreUpdate
  private void setUpdatedAt() {
    this.updatedAt = LocalDateTime.now();
  }

  public DeveloperDto toDto() {
    return new DeveloperDto(this.id, this.name);
  }
}
