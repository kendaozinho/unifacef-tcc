package com.unifacef.tcc.business;

import com.unifacef.tcc.controller.v1.dto.DeveloperDto;
import com.unifacef.tcc.exception.BadRequestException;
import com.unifacef.tcc.exception.NotFoundException;
import com.unifacef.tcc.exception.UnprocessableEntityException;
import com.unifacef.tcc.model.Developer;
import com.unifacef.tcc.repository.DeveloperRepository;
import com.unifacef.tcc.repository.DeveloperSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
      Developer developer = this.repository.findOneById(id);

      if (developer != null) {
        developers.add(developer);
      }
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
    if (id == null || id <= 0) {
      throw new BadRequestException("Invalid id");
    }

    Developer developer = this.repository.findOneById(id);

    if (developer == null) {
      throw new NotFoundException("Developer not found");
    }

    return developer.toDto();
  }

  public DeveloperDto post(DeveloperDto request) {
    request.setId(null);

    return this.repository.saveAndFlush(request.toModel()).toDto();
  }

  public DeveloperDto put(Integer id, DeveloperDto request) {
    if (id == null || id <= 0) {
      throw new BadRequestException("Invalid id");
    }

    request.setId(id);

    Developer developer = this.repository.findOneById(id);

    if (developer == null) {
      throw new NotFoundException("Developer not found");
    }

    developer.setName(request.getName());

    this.repository.saveAndFlush(developer);

    return developer.toDto();
  }

  public void delete(Integer id) {
    if (id == null || id <= 0) {
      throw new BadRequestException("Invalid id");
    }

    Developer developer = this.repository.findOneById(id);

    if (developer == null) {
      throw new NotFoundException("Developer not found");
    }

    if (!this.developerSkillRepository.findAllByDeveloperId(id).isEmpty()) {
      throw new UnprocessableEntityException("Developer is being used");
    }

    this.repository.delete(developer);
  }
}