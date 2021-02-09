package com.unifacef.tcc.controller.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {
  @RequestMapping({"/", "/docs", "/swagger", "/swagger-ui.html"})
  public String redirect() {
    return "redirect:/swagger-ui/";
  }
}