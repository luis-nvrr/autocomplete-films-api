package com.aftership.filmlocations.domain.repositories;

import com.aftership.filmlocations.domain.entities.Writer;

public interface WriterRepository {
  public Writer getWriter(String name);

  public Writer saveWriter(Writer writer);
}
