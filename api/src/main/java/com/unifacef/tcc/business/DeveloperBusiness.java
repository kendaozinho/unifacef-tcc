package com.unifacef.tcc.business;

import com.unifacef.tcc.controller.v1.dto.DeveloperDto;
import com.unifacef.tcc.exception.NotFoundException;
import com.unifacef.tcc.model.Developer;
import com.unifacef.tcc.repository.DeveloperRepository;
import com.unifacef.tcc.repository.DeveloperSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeveloperBusiness {
  @Autowired
  private DeveloperRepository repository;
  @Autowired
  private DeveloperSkillRepository developerSkillRepository;

  public ArrayList<DeveloperDto> getAll(Integer id, String name) {
    ArrayList<Developer> developers = new ArrayList<>();

    if (id != null) {
      this.repository.findById(id).ifPresent(developers::add);
    } else if (name != null && !name.trim().isEmpty()) {
      developers.addAll(this.repository.findAllByName(name));
    } else {
      developers.addAll(this.repository.findAll());
    }

    if (developers.isEmpty()) {
      throw new NotFoundException("Developer not found");
    }

    return developers.stream().map(Developer::toDto).collect(Collectors.toCollection(ArrayList::new));
  }

  public DeveloperDto getById(Integer id) {
    Optional<Developer> developer = this.repository.findById(id);

    if (!developer.isPresent()) {
      throw new NotFoundException("Developer not found");
    }

    return developer.get().toDto();
  }

  public DeveloperDto post(DeveloperDto request) {
    request.setId(null);

    return this.repository.saveAndFlush(request.toModel()).toDto();
  }

  public DeveloperDto put(Integer id, DeveloperDto request) {
    request.setId(id);

    Optional<Developer> developer = this.repository.findById(id);

    if (!developer.isPresent()) {
      throw new NotFoundException("Developer not found");
    }

    return this.repository.saveAndFlush(request.toModel()).toDto();
  }

  public void delete(Integer id) {
    Optional<Developer> developer = this.repository.findById(id);

    if (!developer.isPresent()) {
      throw new NotFoundException("Developer not found");
    }

    this.developerSkillRepository.findAllByDeveloperId(id).forEach(
        developerSkill -> this.developerSkillRepository.delete(developerSkill)
    );

    this.repository.delete(developer.get());
  }
}