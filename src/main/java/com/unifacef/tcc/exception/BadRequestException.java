package com.unifacef.tcc.exception;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String developerMessage) {
    super(developerMessage);
  }
}
