package com.aftership.filmlocations.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Film {
  private String title;
  private int releaseYear;
  private List<Location> locations;
  private ProductionCompany productionCompany;
  private Distributor distributor;
  private Director director;
  private Writer writer;
  private List<Actor> actors;

  public Film(String title, String releaseYear, Location location,
      ProductionCompany productionCompany, Distributor distributor,
      Director director, Writer writer, List<Actor> actors) {
    this.title = title.trim().toLowerCase();
    this.releaseYear = parseToInt(releaseYear);
    this.locations = new ArrayList<>();
    this.locations.add(location);
    this.productionCompany = productionCompany;
    this.distributor = distributor;
    this.director = director;
    this.writer = writer;
    this.actors = actors;
  }

  private int parseToInt(String num) {
    try {
      return Integer.parseInt(num);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  public void addLocation(String locationName) {
    this.locations.add(new Location(locationName));
  }

  public String getTitle() {
    return this.title;
  }

  public String getFirstLocationName() {
    return this.locations.get(0).getName();
  }
}
