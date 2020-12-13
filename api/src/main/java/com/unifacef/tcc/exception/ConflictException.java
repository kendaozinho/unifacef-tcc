package com.unifacef.tcc.exception;

public class ConflictException extends RuntimeException {
  public ConflictException(String developerMessage) {
    super(developerMessage);
  }
}
