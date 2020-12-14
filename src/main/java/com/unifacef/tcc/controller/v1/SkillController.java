package com.unifacef.tcc.controller.v1;

import com.unifacef.tcc.base.dto.BaseResponseSuccess;
import com.unifacef.tcc.business.SkillBusiness;
import com.unifacef.tcc.controller.v1.dto.SkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/skills")
public class SkillController {
  @Autowired
  private SkillBusiness business;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public BaseResponseSuccess<SkillDto> getAll(
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
  public BaseResponseSuccess<SkillDto> getById(@PathVariable Integer id) {
    return BaseResponseSuccess.instanceOf(
        this.business.getById(id)
    );
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BaseResponseSuccess<SkillDto> post(@RequestBody SkillDto request) {
    return BaseResponseSuccess.instanceOf(
        this.business.post(request)
    );
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponseSuccess<SkillDto> put(
      @PathVariable Integer id,
      @RequestBody SkillDto request
  ) {
    return BaseResponseSuccess.instanceOf(
        this.business.put(id, request)
    );
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Integer id) {
    this.business.delete(id);
  }
}
