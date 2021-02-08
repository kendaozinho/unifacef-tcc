package com.unifacef.tcc.controller.v1;

import com.unifacef.tcc.base.dto.BaseResponseSuccess;
import com.unifacef.tcc.business.DeveloperBusiness;
import com.unifacef.tcc.controller.v1.dto.DeveloperDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/developers")
@Api(tags = { "Developer Controller" }, description = "/v1/developers")
public class DeveloperController {
  @Autowired
  private DeveloperBusiness business;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get Developer(s)")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 404, message = "Developer not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public BaseResponseSuccess<DeveloperDto> getAll(
      @RequestParam(required = false) Integer id,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Integer offset,
      @RequestParam(required = false) Integer limit
  ) {
    return BaseResponseSuccess.instanceOf(
        this.business.getAll(id, name), offset, limit
    );
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get Developer")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 400, message = "Invalid id"),
          @ApiResponse(code = 404, message = "Developer not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public BaseResponseSuccess<DeveloperDto> getById(@PathVariable Integer id) {
    return BaseResponseSuccess.instanceOf(
        this.business.getById(id)
    );
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create Developer")
  @ApiResponses(
      value = {
          @ApiResponse(code = 201, message = "Created"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public BaseResponseSuccess<DeveloperDto> post(@RequestBody DeveloperDto request) {
    return BaseResponseSuccess.instanceOf(
        this.business.post(request)
    );
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Update Developer")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 400, message = "Invalid id"),
          @ApiResponse(code = 404, message = "Developer not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public BaseResponseSuccess<DeveloperDto> put(
      @PathVariable Integer id,
      @RequestBody DeveloperDto request
  ) {
    return BaseResponseSuccess.instanceOf(
        this.business.put(id, request)
    );
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Delete Developer")
  @ApiResponses(
      value = {
          @ApiResponse(code = 204, message = "No Content"),
          @ApiResponse(code = 400, message = "Invalid id"),
          @ApiResponse(code = 404, message = "Developer not found"),
          @ApiResponse(code = 422, message = "Developer is being used"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public void delete(@PathVariable Integer id) {
    this.business.delete(id);
  }
}
