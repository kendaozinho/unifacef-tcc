package com.unifacef.tcc.exception;

public class InternalServerErrorException extends RuntimeException {
  public InternalServerErrorException(String developerMessage) {
    super(developerMessage);
  }
}
