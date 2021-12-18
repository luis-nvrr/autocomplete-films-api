package com.aftership.filmlocations.domain.repositories;

import com.aftership.filmlocations.domain.entities.Director;

public interface DirectorRepository {
  public Director getDirector(String name);

  public Director saveDirector(Director director);
}
