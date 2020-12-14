package com.unifacef.tcc.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String developerMessage) {
    super(developerMessage);
  }
}
