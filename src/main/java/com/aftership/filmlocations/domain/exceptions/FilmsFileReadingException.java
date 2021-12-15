package com.aftership.filmlocations.domain.exceptions;

public class FilmsFileReadingException extends RuntimeException {
  public FilmsFileReadingException(String fileName) {
    super("Could not read file " + fileName);
  }
}
