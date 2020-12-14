package com.unifacef.tcc.model;

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
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public DeveloperSkill() {
  }

  public DeveloperSkill(Integer developerId, Integer skillId) {
    this.developerId = developerId;
    this.skillId = skillId;
  }

  public Integer getDeveloperId() {
    return developerId;
  }

  public Integer getSkillId() {
    return skillId;
  }

  @PrePersist
  private void setCreatedAt() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  private void setUpdatedAt() {
    this.updatedAt = LocalDateTime.now();
  }

  public static class DeveloperSkillId implements Serializable {
    private Integer developerId;
    private Integer skillId;

    public DeveloperSkillId() {
    }
  }
}
