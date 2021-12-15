package com.aftership.filmlocations.domain.entities;

import java.text.ParseException;
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

  public Film(String title, String releaseYear, String locationName, String productionCompany, String distributor,
      String director, String writer, List<String> actorsNames) {
    this.title = title.trim().toLowerCase();
    this.releaseYear = parseToInt(releaseYear);
    this.locations = createLocationsList(locationName);
    this.productionCompany = new ProductionCompany(productionCompany);
    this.distributor = new Distributor(distributor);
    this.director = new Director(director);
    this.writer = new Writer(writer);
    this.actors = createActorsList(actorsNames);
  }

  private int parseToInt(String num) {
    try {
      return Integer.parseInt(num);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  private List<Actor> createActorsList(List<String> actorsNames) {
    List<Actor> actors = new ArrayList<>();
    for (String actorName : actorsNames) {
      actors.add(new Actor(actorName));
    }
    return actors;
  }

  private List<Location> createLocationsList(String locationName) {
    List<Location> locations = new ArrayList<>();
    locations.add(new Location(locationName));
    return locations;
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
