package com.unifacef.tcc.controller.v1;

import com.unifacef.tcc.controller.base.BaseControllerTest;
import com.unifacef.tcc.controller.v1.dto.DeveloperDto;
import com.unifacef.tcc.model.Developer;
import com.unifacef.tcc.repository.DeveloperRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeveloperControllerTest extends BaseControllerTest {
  private String path = "/v1/developers";

  @Autowired
  private DeveloperRepository repository;

  @Test
  @Order(1)
  public void getByIdIsOk() throws Throwable {
    super.getIsOk(this.path + "/1");
  }

  @Test
  @Order(2)
  public void getByIdIsBadRequest() throws Throwable {
    super.getIsBadRequest(this.path + "/0", "Invalid id");
  }

  @Test
  @Order(3)
  public void getByIdIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "/999", "Developer not found");
  }

  @Test
  @Order(4)
  public void getAllByIdIsOk() throws Throwable {
    super.getIsOk(this.path + "?id=1");
  }

  @Test
  @Order(5)
  public void getAllByIdIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "?id=999", "Developer not found");
  }

  @Test
  @Order(6)
  public void getAllByNameIsOk() throws Throwable {
    super.getIsOk(this.path + "?name=Kenneth Gottschalk de Azevedo");
  }

  @Test
  @Order(7)
  public void getAllByNameIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "?name=Silvana", "Developer not found");
  }

  @Test
  @Order(8)
  public void getAllIsOk() throws Throwable {
    super.getIsOk(this.path);
  }

  @Test
  @Order(9)
  public void postIsCreated() throws Throwable {
    super.postIsCreated(this.path, new DeveloperDto(null, "João"));
  }

  @Test
  @Order(10)
  public void putIsOk() throws Throwable {
    super.putIsOk(this.path + "/1", new DeveloperDto(null, "Maria"));
  }

  @Test
  @Order(11)
  public void putIsBadRequest() throws Throwable {
    super.putIsBadRequest(this.path + "/0", new DeveloperDto(null, "Zé"), "Invalid id");
  }

  @Test
  @Order(12)
  public void putIsNotFound() throws Throwable {
    super.putIsNotFound(this.path + "/999", new DeveloperDto(null, "João"), "Developer not found");
  }

  @Test
  @Order(13)
  public void deleteIsNoContent() throws Throwable {
    List<Developer> developers = this.repository.findAll();
    developers.sort(Comparator.comparing(Developer::getId));

    Integer id = developers.get(developers.size() - 1).getId();

    super.deleteIsNoContent(this.path + "/" + id);
  }

  @Test
  @Order(14)
  public void deleteIsBadRequest() throws Throwable {
    super.deleteIsBadRequest(this.path + "/0", "Invalid id");
  }

  @Test
  @Order(15)
  public void deleteIsNotFound() throws Throwable {
    super.deleteIsNotFound(this.path + "/999", "Developer not found");
  }

  @Test
  @Order(16)
  public void deleteIsUnprocessableEntity() throws Throwable {
    super.deleteIsUnprocessableEntity(this.path + "/1", "Developer is being used");
  }
}
