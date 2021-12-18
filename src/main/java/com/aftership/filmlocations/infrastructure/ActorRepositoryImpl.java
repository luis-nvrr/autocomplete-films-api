package com.aftership.filmlocations.infrastructure;

import java.util.HashMap;
import java.util.Map;

import com.aftership.filmlocations.domain.entities.Actor;
import com.aftership.filmlocations.domain.repositories.ActorRepository;

import org.springframework.stereotype.Repository;

@Repository
public class ActorRepositoryImpl implements ActorRepository {
  private Map<String, Actor> actorRepository;

  public ActorRepositoryImpl() {
    this.actorRepository = new HashMap<>();
  }

  @Override
  public Actor getActor(String name) {
    return this.actorRepository.get(name);
  }

  @Override
  public Actor saveActor(Actor actor) {
    this.actorRepository.remove(actor.getName());
    this.actorRepository.put(actor.getName(), actor);

    return actor;
  }

}
