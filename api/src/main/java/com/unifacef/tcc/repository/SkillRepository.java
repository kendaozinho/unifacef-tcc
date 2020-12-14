package com.unifacef.tcc.repository;

import com.unifacef.tcc.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
  Skill findOneByName(String name);
}
