package com.unifacef.tcc.controller.v1.developer;

import com.unifacef.tcc.controller.dto.BaseResponseSuccess;
import com.unifacef.tcc.controller.v1.developer.dto.DeveloperDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/developers")
public class DeveloperController {
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public BaseResponseSuccess<DeveloperDto> getAll() {
    return BaseResponseSuccess.instanceOf(new DeveloperDto(1, "kendao"), 10, 10);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponseSuccess<DeveloperDto> getById(@PathVariable Integer id) {
    return BaseResponseSuccess.instanceOf(new DeveloperDto(id, "kendao"), 10, 10);
  }
}
