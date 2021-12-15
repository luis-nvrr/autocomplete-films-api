package com.aftership.filmlocations.domain.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.aftership.filmlocations.domain.entities.Film;
import com.aftership.filmlocations.domain.entities.SuggestTree;
import com.aftership.filmlocations.domain.entities.SuggestTree.Node;
import com.aftership.filmlocations.domain.exceptions.FilmsFileReadingException;
import com.aftership.filmlocations.domain.repositories.FilmRepository;
import com.aftership.filmlocations.domain.responses.FileUploadResponse;
import com.aftership.filmlocations.domain.responses.SearchResponse;

import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService {
  private final FilmRepository filmRepository;
  private final SuggestTree tree;

  public FilmServiceImpl(FilmRepository filmRepository, SuggestTree tree) {
    this.filmRepository = filmRepository;
    this.tree = tree;
  }

  @Override
  public FileUploadResponse loadFilms(InputStream dataInputStream, String fileName) {

    try (BufferedReader bufferedReader = new BufferedReader(
        new InputStreamReader(dataInputStream, StandardCharsets.UTF_8));) {

      bufferedReader.readLine();
      String line = bufferedReader.readLine();

      while (line != null) {
        Film parsedFilm = createFilm(line);
        Film existingFilm = filmRepository.getFilm(parsedFilm.getTitle());

        line = bufferedReader.readLine();
        if (existingFilm != null) {
          existingFilm.addLocation(parsedFilm.getFirstLocationName());
          this.filmRepository.updateFilm(existingFilm);
          continue;
        }

        this.filmRepository.addFilm(parsedFilm);
        this.tree.put(parsedFilm.getTitle(), 1);
      }

    } catch (IOException e) {
      throw new FilmsFileReadingException(fileName);
    }

    return new FileUploadResponse(fileName);
  }

  private Film createFilm(String line) {
    String title = "";
    String releaseYear = "";
    String location = "";
    String productionCompany = "";
    String distributor = "";
    String director = "";
    String writer = "";
    List<String> actors = new ArrayList<>();

    try {
      String[] fields = line.split(",");
      title = fields[0].trim();
      releaseYear = fields[1].trim();
      location = fields[2].trim();
      productionCompany = fields[4].trim();
      distributor = fields[5].trim();
      director = fields[6].trim();
      writer = fields[7].trim();
      actors.add(fields[8].trim());
      actors.add(fields[9].trim());
    } catch (IndexOutOfBoundsException e) {
    }

    return new Film(title, releaseYear, location, productionCompany, distributor, director, writer, actors);
  }

  @Override
  public SearchResponse searchFilm(String title) {
    Node node = this.tree.getAutocompleteSuggestions(title);
    List<String> suggestions = new ArrayList<>();

    if (node == null) {
      return new SearchResponse(title, suggestions);
    }

    for (int i = 0; i < node.listLength(); i++) {
      suggestions.add(node.getSuggestion(i).getTerm());
    }

    return new SearchResponse(title, suggestions);
  }
}
