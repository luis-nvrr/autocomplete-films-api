package com.aftership.filmlocations.domain.repositories;

import com.aftership.filmlocations.domain.entities.Film;

public interface FilmRepository {

  public Film addFilm(Film film);

  public Film getFilm(String filmTitle);

  public void updateFilm(Film film);
}
