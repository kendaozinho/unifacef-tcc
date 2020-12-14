package com.unifacef.tcc.exception;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String developerMessage) {
    super(developerMessage);
  }
}
