package com.unifacef.tcc.controller.v1;

import com.unifacef.tcc.controller.base.BaseControllerTest;
import com.unifacef.tcc.controller.v1.dto.SkillDto;
import com.unifacef.tcc.model.Skill;
import com.unifacef.tcc.repository.SkillRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SkillControllerTest extends BaseControllerTest {
  private String path = "/v1/skills";

  @Autowired
  private SkillRepository repository;

  @Test
  @Order(1)
  public void getByIdIsOk() throws Throwable {
    super.getIsOk(this.path + "/1");
  }

  @Test
  @Order(2)
  public void getByIdIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "/999", "Skill not found");
  }

  @Test
  @Order(3)
  public void getByNameIsOk() throws Throwable {
    super.getIsOk(this.path + "?name=Java");
  }

  @Test
  @Order(4)
  public void getByNameIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "?name=Go", "Skill not found");
  }

  @Test
  @Order(5)
  public void getAllIsOk() throws Throwable {
    super.getIsOk(this.path);
  }

  @Test
  @Order(6)
  public void postIsCreated() throws Throwable {
    super.postIsCreated(this.path, new SkillDto(null, "Python"));
  }

  @Test
  @Order(7)
  public void postIsConflict() throws Throwable {
    super.postIsConflict(this.path, new SkillDto(null, "Python"), "Skill already exists");
  }

  @Test
  @Order(8)
  public void putIsOk() throws Throwable {
    super.putIsOk(this.path + "/1", new SkillDto(null, "Ruby"));
  }

  @Test
  @Order(9)
  public void putIsConflict() throws Throwable {
    super.putIsConflict(this.path + "/1", new SkillDto(null, "C#"), "Skill already exists");
  }

  @Test
  @Order(10)
  public void putIsNotFound() throws Throwable {
    super.putIsNotFound(this.path + "/999", new SkillDto(null, "C++"), "Skill not found");
  }

  @Test
  @Order(11)
  public void deleteIsNoContent() throws Throwable {
    List<Skill> skills = this.repository.findAll();
    skills.sort(Comparator.comparing(Skill::getId));

    Integer id = skills.get(skills.size() - 1).getId();

    super.deleteIsNoContent(this.path + "/" + id);
  }

  @Test
  @Order(12)
  public void deleteIsNotFound() throws Throwable {
    super.deleteIsNotFound(this.path + "/999", "Skill not found");
  }

  @Test
  @Order(13)
  public void deleteIsUnprocessableEntity() throws Throwable {
    super.deleteIsUnprocessableEntity(this.path + "/1", "Skill is being used");
  }
}
