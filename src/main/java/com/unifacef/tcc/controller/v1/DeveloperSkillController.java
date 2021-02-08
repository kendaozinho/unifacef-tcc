package com.unifacef.tcc.controller.v1;

import com.unifacef.tcc.base.dto.BaseResponseSuccess;
import com.unifacef.tcc.business.DeveloperSkillBusiness;
import com.unifacef.tcc.controller.v1.dto.DeveloperSkillDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/developers")
@Api(tags = { "Developer Skill Controller" }, description = "/v1/developers/skills")
public class DeveloperSkillController {
  @Autowired
  private DeveloperSkillBusiness business;

  @GetMapping("/skills")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get Developer Skill(s)")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 404, message = "Developer Skill not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public BaseResponseSuccess<DeveloperSkillDto> getAll(
      @RequestParam(required = false) Integer developerId,
      @RequestParam(required = false) Integer skillId,
      @RequestParam(required = false) Integer offset,
      @RequestParam(required = false) Integer limit
  ) {
    return BaseResponseSuccess.instanceOf(
        this.business.getAll(developerId, skillId), offset, limit
    );
  }

  @GetMapping("/{developerId}/skills/{skillId}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get Developer Skill")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 400, message = "Invalid developerId | Invalid skillId"),
          @ApiResponse(code = 404, message = "Developer Skill not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public BaseResponseSuccess<DeveloperSkillDto> getById(
      @PathVariable Integer developerId,
      @PathVariable Integer skillId
  ) {
    return BaseResponseSuccess.instanceOf(
        this.business.getById(developerId, skillId)
    );
  }

  @PostMapping("/skills")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create Developer Skill")
  @ApiResponses(
      value = {
          @ApiResponse(code = 201, message = "Created"),
          @ApiResponse(code = 404, message = "Developer not found | Skill not found"),
          @ApiResponse(code = 409, message = "Developer Skill already exists"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public BaseResponseSuccess<DeveloperSkillDto> post(@RequestBody DeveloperSkillDto request) {
    return BaseResponseSuccess.instanceOf(
        this.business.post(request)
    );
  }

  @DeleteMapping("/{developerId}/skills/{skillId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Delete Developer Skill")
  @ApiResponses(
      value = {
          @ApiResponse(code = 204, message = "No Content"),
          @ApiResponse(code = 400, message = "Invalid developerId | Invalid skillId"),
          @ApiResponse(code = 404, message = "Developer Skill not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public void delete(@PathVariable Integer developerId, @PathVariable Integer skillId) {
    this.business.delete(developerId, skillId);
  }
}
