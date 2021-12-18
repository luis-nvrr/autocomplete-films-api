package com.aftership.filmlocations.domain.entities;

public class Distributor {
  private String name;

  public Distributor(String name) {
    this.name = name.trim().toLowerCase();
  }

  public String getName() {
    return this.name;
  }
}
