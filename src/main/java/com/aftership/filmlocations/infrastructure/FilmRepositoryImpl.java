package com.aftership.filmlocations.infrastructure;

import java.util.HashMap;
import java.util.Map;

import com.aftership.filmlocations.domain.entities.Film;
import com.aftership.filmlocations.domain.repositories.FilmRepository;

import org.springframework.stereotype.Repository;

@Repository
public class FilmRepositoryImpl implements FilmRepository {
  private Map<String, Film> repository;

  public FilmRepositoryImpl() {
    repository = new HashMap<>();
  }

  @Override
  public Film addFilm(Film film) {
    this.repository.putIfAbsent(film.getTitle(), film);
    return film;
  }

  @Override
  public Film getFilm(String filmTitle) {
    return this.repository.get(filmTitle);
  }

  @Override
  public void updateFilm(Film film) {
    this.repository.replace(film.getTitle(), film);
  }
}
