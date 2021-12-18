package com.aftership.filmlocations.domain.repositories;

import com.aftership.filmlocations.domain.entities.Location;

public interface LocationRepository {
  public Location getLocation(String name);

  public Location saveLocation(Location location);
}
