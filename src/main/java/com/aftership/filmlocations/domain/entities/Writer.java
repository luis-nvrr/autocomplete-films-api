package com.aftership.filmlocations.domain.entities;

public class Writer {
  private String name;

  public Writer(String name) {
    this.name = name.trim().toLowerCase();
  }
}
