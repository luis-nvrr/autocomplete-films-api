package com.aftership.filmlocations.infrastructure;

import java.util.HashMap;
import java.util.Map;

import com.aftership.filmlocations.domain.entities.Director;
import com.aftership.filmlocations.domain.repositories.DirectorRepository;

import org.springframework.stereotype.Repository;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {
  private Map<String, Director> respository;

  public DirectorRepositoryImpl() {
    this.respository = new HashMap<>();
  }

  @Override
  public Director getDirector(String name) {
    return this.respository.get(name);
  }

  @Override
  public Director saveDirector(Director director) {
    this.respository.remove(director.getName());
    this.respository.put(director.getName(), director);
    return director;
  }

}
