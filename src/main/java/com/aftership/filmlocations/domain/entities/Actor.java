package com.aftership.filmlocations.domain.entities;

public class Actor {
  private String name;

  public Actor(String name) {
    this.name = name.trim().toLowerCase();
  }
}
