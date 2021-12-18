package com.aftership.filmlocations.infrastructure;

import java.util.HashMap;
import java.util.Map;

import com.aftership.filmlocations.domain.entities.Writer;
import com.aftership.filmlocations.domain.repositories.WriterRepository;

import org.springframework.stereotype.Repository;

@Repository
public class WriterRepositoryImpl implements WriterRepository {
  private Map<String, Writer> repository;

  public WriterRepositoryImpl() {
    this.repository = new HashMap<>();
  }

  @Override
  public Writer getWriter(String name) {
    return this.repository.get(name);
  }

  @Override
  public Writer saveWriter(Writer writer) {
    this.repository.remove(writer.getName());
    this.repository.put(writer.getName(), writer);
    return writer;
  }
}
