package com.unifacef.tcc.controller.v1;

import com.unifacef.tcc.controller.base.BaseControllerTest;
import com.unifacef.tcc.controller.v1.dto.DeveloperDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeveloperControllerTest extends BaseControllerTest {
  private String path = "/v1/developers";

  @Test
  @Order(1)
  public void getByIdIsOk() throws Throwable {
    super.getIsOk(this.path + "/1");
  }

  @Test
  @Order(2)
  public void getByIdIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "/999", "Developer not found");
  }

  @Test
  @Order(3)
  public void getByNameIsOk() throws Throwable {
    super.getIsOk(this.path + "?name=Kenneth Gottschalk de Azevedo");
  }

  @Test
  @Order(4)
  public void getByNameIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "?name=Silvana", "Developer not found");
  }

  @Test
  @Order(5)
  public void getAllIsOk() throws Throwable {
    super.getIsOk(this.path);
  }

  @Test
  @Order(6)
  public void postIsCreated() throws Throwable {
    super.postIsCreated(this.path, new DeveloperDto(null, "João"));
  }

  @Test
  @Order(7)
  public void putIsOk() throws Throwable {
    super.putIsOk(this.path + "/1", new DeveloperDto(null, "Maria"));
  }

  @Test
  @Order(8)
  public void putIsNotFound() throws Throwable {
    super.putIsNotFound(this.path + "/999", new DeveloperDto(null, "João"), "Developer not found");
  }

  @Test
  @Order(9)
  public void deleteIsNoContent() throws Throwable {
    super.deleteIsNoContent(this.path + "/1");
  }

  @Test
  @Order(10)
  public void deleteIsNotFound() throws Throwable {
    super.deleteIsNotFound(this.path + "/999", "Developer not found");
  }
}
