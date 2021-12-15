package com.aftership.filmlocations.domain.entities;

public class ProductionCompany {
  private String name;

  public ProductionCompany(String name) {
    this.name = name.trim().toLowerCase();
  }
}
