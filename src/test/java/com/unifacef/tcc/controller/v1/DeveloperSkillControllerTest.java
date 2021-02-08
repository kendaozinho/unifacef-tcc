package com.unifacef.tcc.controller.v1;

import com.unifacef.tcc.controller.base.BaseControllerTest;
import com.unifacef.tcc.controller.v1.dto.DeveloperDto;
import com.unifacef.tcc.controller.v1.dto.DeveloperSkillDto;
import com.unifacef.tcc.controller.v1.dto.SkillDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeveloperSkillControllerTest extends BaseControllerTest {
  private String shortPath = "/v1/developers/skills";
  private String fullPath = "/v1/developers/{developerId}/skills/{skillId}";

  @Test
  @Order(1)
  public void getByIdIsOk() throws Throwable {
    super.getIsOk(this.fullPath.replace("{developerId}", "1").replace("{skillId}", "1"));
  }

  @Test
  @Order(2)
  public void getByIdIsNotFound() throws Throwable {
    super.getIsNotFound(this.fullPath.replace("{developerId}", "999").replace("{skillId}", "999"), "Developer Skill not found");
  }

  @Test
  @Order(3)
  public void getAllByDeveloperIdIsOk() throws Throwable {
    super.getIsOk(this.shortPath + "?developerId=1");
  }

  @Test
  @Order(4)
  public void getAllByDeveloperIdIsNotFound() throws Throwable {
    super.getIsNotFound(this.shortPath + "?developerId=999", "Developer Skill not found");
  }

  @Test
  @Order(5)
  public void getAllBySkillIdIsOk() throws Throwable {
    super.getIsOk(this.shortPath + "?skillId=1");
  }

  @Test
  @Order(6)
  public void getAllBySkillIdIsNotFound() throws Throwable {
    super.getIsNotFound(this.shortPath + "?skillId=999", "Developer Skill not found");
  }

  @Test
  @Order(7)
  public void getAllIsOk() throws Throwable {
    super.getIsOk(this.shortPath + "?offset=0&limit=3");
  }

  @Test
  @Order(8)
  public void getAllIsNotFound() throws Throwable {
    super.getIsNotFound(this.shortPath + "?offset=10&limit=100", "Data not found");
  }

  @Test
  @Order(9)
  public void postIsCreated() throws Throwable {
    super.postIsCreated(this.shortPath, new DeveloperSkillDto(new DeveloperDto(2, null), new SkillDto(2, null)));
  }

  @Test
  @Order(10)
  public void postIsConflict() throws Throwable {
    super.postIsConflict(this.shortPath, new DeveloperSkillDto(new DeveloperDto(2, null), new SkillDto(2, null)), "Developer Skill already exists");
  }

  @Test
  @Order(11)
  public void postIsDeveloperNotFound() throws Throwable {
    super.postIsNotFound(this.shortPath, new DeveloperSkillDto(new DeveloperDto(999, null), new SkillDto(1, null)), "Developer not found");
  }

  @Test
  @Order(12)
  public void postIsSkillNotFound() throws Throwable {
    super.postIsNotFound(this.shortPath, new DeveloperSkillDto(new DeveloperDto(1, null), new SkillDto(999, null)), "Skill not found");
  }

  @Test
  @Order(13)
  public void deleteIsNoContent() throws Throwable {
    super.deleteIsNoContent(this.fullPath.replace("{developerId}", "2").replace("{skillId}", "2"));
  }

  @Test
  @Order(14)
  public void deleteIsNotFound() throws Throwable {
    super.deleteIsNotFound(this.fullPath.replace("{developerId}", "2").replace("{skillId}", "2"), "Developer Skill not found");
  }
}
