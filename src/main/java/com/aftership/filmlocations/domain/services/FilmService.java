package com.aftership.filmlocations.domain.services;

import java.io.InputStream;

import com.aftership.filmlocations.domain.responses.FileUploadResponse;
import com.aftership.filmlocations.domain.responses.SearchResponse;

public interface FilmService {
  FileUploadResponse loadFilms(InputStream dataInputStream, String fileName);

  SearchResponse searchFilm(String title);
}
