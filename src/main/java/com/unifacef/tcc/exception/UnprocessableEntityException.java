package com.unifacef.tcc.exception;

public class UnprocessableEntityException extends RuntimeException {
  public UnprocessableEntityException(String developerMessage) {
    super(developerMessage);
  }
}
