package com.aftership.filmlocations.domain.repositories;

import com.aftership.filmlocations.domain.entities.Actor;

public interface ActorRepository {
  public Actor getActor(String name);

  public Actor saveActor(Actor actor);
}
