package com.unifacef.tcc.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "developer_skill")
@IdClass(DeveloperSkill.DeveloperSkillId.class)
public class DeveloperSkill implements Serializable {
  @Id
  @Column(name = "developer_id", nullable = false, updatable = false)
  private Integer developerId;

  @Id
  @Column(name = "skill_id", nullable = false, updatable = false)
  private Integer skillId;

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

  public class DeveloperSkillId implements Serializable {
    private Integer developerId;
    private Integer skillId;

    public DeveloperSkillId() {}
  }
}
