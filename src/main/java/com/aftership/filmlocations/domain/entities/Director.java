package com.aftership.filmlocations.domain.entities;

public class Director {
  private String name;

  public Director(String name) {
    this.name = name.trim().toLowerCase();
  }
}
