package com.unifacef.tcc.configuration.swagger;

import com.unifacef.tcc.util.ApplicationUtil;
import com.unifacef.tcc.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.HashSet;

@Configuration
public class SwaggerConfiguration {
  @Value("${jwt.secret-key}")
  private String jwtSecretKey;

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .pathMapping("/")
        .apiInfo(this.getApiInfo())
        .forCodeGeneration(true)
        .genericModelSubstitutes(ResponseEntity.class)
        .securityContexts(Collections.singletonList(
            this.getSecurityContext()
        ))
        .securitySchemes(Collections.singletonList(
            new ApiKey("JWT", "Authorization", "header")
        ))
        .useDefaultResponseMessages(false)
        .consumes(new HashSet<String>() {{
          add("application/json");
        }})
        .produces(new HashSet<String>() {{
          add("application/json");
        }})
        .select()
        .paths(PathSelectors.regex("/v[0-9]+/.*"))
        .apis(RequestHandlerSelectors.basePackage("com.unifacef"))
        .build();
  }

  private ApiInfo getApiInfo() {
    return new ApiInfoBuilder()
        .title("unifacef-tcc")
        .description(JwtUtil.getEncodedJwt(this.jwtSecretKey))
        .version(ApplicationUtil.getVersion())
        .build();
  }

  private SecurityContext getSecurityContext() {
    return SecurityContext.builder()
        .securityReferences(
            Collections.singletonList(
                new SecurityReference(
                    "JWT",
                    Collections.singletonList(
                        new AuthorizationScope("global", "accessEverything")
                    ).toArray(new AuthorizationScope[1])
                )
            )
        )
        .forPaths(PathSelectors.any())
        .build();
  }
}
