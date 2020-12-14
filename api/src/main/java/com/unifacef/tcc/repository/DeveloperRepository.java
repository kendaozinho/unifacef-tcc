package com.unifacef.tcc.repository;

import com.unifacef.tcc.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
  Developer findOneById(Integer id);

  ArrayList<Developer> findAllByName(String name);
}
