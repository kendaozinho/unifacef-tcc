package com.unifacef.tcc.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtUtilTest {
  @Test
  public void validateJwtWithSuccess() {
    Assertions.assertTrue(
        JwtUtil.isValidJwt(
            "1234567890-1234567890-1234567890",
            JwtUtil.getEncodedJwt("1234567890-1234567890-1234567890")
        )
    );
  }

  @Test
  public void validateJwtWithError() {
    Assertions.assertFalse(
        JwtUtil.isValidJwt(
            "0987654321-0987654321-0987654321",
            JwtUtil.getEncodedJwt("1234567890-1234567890-1234567890")
        )
    );
  }
}
