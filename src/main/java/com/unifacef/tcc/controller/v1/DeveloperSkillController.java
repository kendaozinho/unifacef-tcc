package com.unifacef.tcc.controller.v1;

import com.unifacef.tcc.base.dto.BaseResponseSuccess;
import com.unifacef.tcc.business.DeveloperSkillBusiness;
import com.unifacef.tcc.controller.v1.dto.DeveloperSkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/developers")
public class DeveloperSkillController {
  @Autowired
  private DeveloperSkillBusiness business;

  @GetMapping("/skills")
  @ResponseStatus(HttpStatus.OK)
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
  public BaseResponseSuccess<DeveloperSkillDto> post(@RequestBody DeveloperSkillDto request) {
    return BaseResponseSuccess.instanceOf(
        this.business.post(request)
    );
  }

  @DeleteMapping("/{developerId}/skills/{skillId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Integer developerId, @PathVariable Integer skillId) {
    this.business.delete(developerId, skillId);
  }
}
