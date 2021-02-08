package com.unifacef.tcc.controller.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unifacef.tcc.base.dto.BaseResponseError;
import com.unifacef.tcc.controller.base.BaseControllerTest;
import com.unifacef.tcc.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class SwaggerControllerTest extends BaseControllerTest {
  @Autowired
  private ObjectMapper mapper;

  @Value("${jwt.secret-key}")
  private String jwtSecretKey;

  @Test
  public void redirectWithSuccess() {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + super.port + "/swagger", String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    Assertions.assertTrue(response.getBody() != null && response.getBody().contains("Swagger UI"));
  }

  @Test
  public void routeIsUnauthorized() throws Throwable {
    try {
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.getForEntity("http://localhost:" + super.port + "/abc", String.class);
      Assertions.fail();
    } catch (HttpStatusCodeException e) {
      Assertions.assertEquals(e.getStatusCode(), HttpStatus.UNAUTHORIZED);

      BaseResponseError response = this.mapper.readValue(e.getResponseBodyAsString(), BaseResponseError.class);

      Assertions.assertNotNull(response);
      Assertions.assertEquals(response.getErrorCode(), HttpStatus.UNAUTHORIZED.value());
      Assertions.assertEquals(response.getUserMessage(), "Unauthorized");
      Assertions.assertEquals(response.getDeveloperMessage(), "Unauthorized");
    }
  }

  @Test
  public void routeIsAuthorized() throws Throwable {
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<String> httpEntity = new HttpEntity<>(
        new HttpHeaders() {{
          add("Authorization", "Bearer " + JwtUtil.getEncodedJwt(jwtSecretKey));
        }}
    );

    try {
      restTemplate.exchange("http://localhost:" + super.port + "/abc", HttpMethod.GET, httpEntity, String.class);
    } catch (HttpStatusCodeException e) {
      Assertions.assertNotEquals(e.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }
  }
}
