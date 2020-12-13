package com.unifacef.tcc.advice.dto;

public class BaseResponseError {
  private String userMessage;
  private String developerMessage;
  private Integer errorCode;

  public BaseResponseError(String userMessage, String developerMessage, Integer errorCode) {
    this.userMessage = userMessage;
    this.developerMessage = developerMessage;
    this.errorCode = errorCode;
  }

  public String getUserMessage() {
    return this.userMessage;
  }

  public String getDeveloperMessage() {
    return this.developerMessage;
  }

  public Integer getErrorCode() {
    if (this.errorCode == null) {
      this.errorCode = 0;
    }
    return this.errorCode;
  }
}
