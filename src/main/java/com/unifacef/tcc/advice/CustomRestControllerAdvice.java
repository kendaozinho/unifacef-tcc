package com.unifacef.tcc.advice;

import com.unifacef.tcc.base.dto.BaseResponseError;
import com.unifacef.tcc.exception.BadRequestException;
import com.unifacef.tcc.exception.ConflictException;
import com.unifacef.tcc.exception.InternalServerErrorException;
import com.unifacef.tcc.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestControllerAdvice {
  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public BaseResponseError catchBadRequestException(Throwable t) {
    return new BaseResponseError("Bad Request", t.getMessage(), HttpStatus.BAD_REQUEST.value());
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public BaseResponseError catchNotFoundException(Throwable t) {
    return new BaseResponseError("Not Found", t.getMessage(), HttpStatus.NOT_FOUND.value());
  }

  @ExceptionHandler(ConflictException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public BaseResponseError catchConflictException(Throwable t) {
    return new BaseResponseError("Conflict", t.getMessage(), HttpStatus.CONFLICT.value());
  }

  @ExceptionHandler({InternalServerErrorException.class, Throwable.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseResponseError catchInternalServerErrorException(Throwable t) {
    return new BaseResponseError("Internal Server Error", t.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
  }
}
