package com.unifacef.tcc.controller.v1;

import com.unifacef.tcc.controller.base.BaseControllerTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeveloperControllerTest extends BaseControllerTest {
  private String path = "/v1/developers";

  @Test
  @Order(1)
  public void getDeveloperIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "/999", "Developer not found");
  }
}
