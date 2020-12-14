package com.unifacef.tcc.redirect.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {
  @RequestMapping({"/", "/swagger", "/docs", "/swagger-ui.html"})
  public String home() {
    return "redirect:/swagger-ui/";
  }
}