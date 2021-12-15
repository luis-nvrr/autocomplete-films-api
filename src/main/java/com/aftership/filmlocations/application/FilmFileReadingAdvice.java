package com.aftership.filmlocations.application;

import com.aftership.filmlocations.domain.exceptions.FilmsFileReadingException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FilmFileReadingAdvice {

  @ResponseBody
  @ExceptionHandler(FilmsFileReadingException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String filmFileReadingErrorHandler(FilmsFileReadingException exception) {
    return exception.getMessage();
  }
}
