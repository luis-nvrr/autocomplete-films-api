package com.aftership.filmlocations.infrastructure;

import java.util.HashMap;
import java.util.Map;

import com.aftership.filmlocations.domain.entities.Location;
import com.aftership.filmlocations.domain.repositories.LocationRepository;

import org.springframework.stereotype.Repository;

@Repository
public class LocationRepositoryImpl implements LocationRepository {
  private Map<String, Location> repository;

  public LocationRepositoryImpl() {
    this.repository = new HashMap<>();
  }

  @Override
  public Location getLocation(String name) {
    return this.repository.get(name);
  }

  @Override
  public Location saveLocation(Location location) {
    this.repository.remove(location.getName());
    this.repository.put(location.getName(), location);
    return location;
  }
}
