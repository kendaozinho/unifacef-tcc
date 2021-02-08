package com.unifacef.tcc.controller.v1;

import com.unifacef.tcc.base.dto.BaseResponseSuccess;
import com.unifacef.tcc.business.SkillBusiness;
import com.unifacef.tcc.controller.v1.dto.SkillDto;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/skills")
@Api(tags = {"Skill Controller"}, description = "/v1/skills")
public class SkillController {
  @Autowired
  private SkillBusiness business;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get Skill(s)")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Skill not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public BaseResponseSuccess<SkillDto> getAll(
      @RequestParam(required = false) @ApiParam(name = "id", value = "id") Integer id,
      @RequestParam(required = false) @ApiParam(name = "name", value = "name") String name,
      @RequestParam(required = false) @ApiParam(name = "offset", value = "page number") Integer offset,
      @RequestParam(required = false) @ApiParam(name = "limit", value = "page size") Integer limit
  ) {
    return BaseResponseSuccess.instanceOf(
        this.business.getAll(id, name), offset, limit
    );
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get Skill")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 400, message = "Invalid id"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Skill not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public BaseResponseSuccess<SkillDto> getById(
      @PathVariable @ApiParam(name = "id", value = "id") Integer id
  ) {
    return BaseResponseSuccess.instanceOf(
        this.business.getById(id)
    );
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create Skill")
  @ApiResponses(
      value = {
          @ApiResponse(code = 201, message = "Created"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 409, message = "Skill already exists"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public BaseResponseSuccess<SkillDto> post(@RequestBody SkillDto request) {
    return BaseResponseSuccess.instanceOf(
        this.business.post(request)
    );
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Update Skill")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 400, message = "Invalid id"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Skill not found"),
          @ApiResponse(code = 409, message = "Skill already exists"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public BaseResponseSuccess<SkillDto> put(
      @PathVariable @ApiParam(name = "id", value = "id") Integer id,
      @RequestBody SkillDto request
  ) {
    return BaseResponseSuccess.instanceOf(
        this.business.put(id, request)
    );
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Delete Skill")
  @ApiResponses(
      value = {
          @ApiResponse(code = 204, message = "No Content"),
          @ApiResponse(code = 400, message = "Invalid id"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Skill not found"),
          @ApiResponse(code = 422, message = "Skill is being used"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public void delete(
      @PathVariable @ApiParam(name = "id", value = "id") Integer id
  ) {
    this.business.delete(id);
  }
}
