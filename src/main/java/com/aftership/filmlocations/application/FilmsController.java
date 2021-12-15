package com.aftership.filmlocations.application;

import java.io.IOException;

import com.aftership.filmlocations.domain.exceptions.FilmsFileReadingException;
import com.aftership.filmlocations.domain.responses.FileUploadResponse;
import com.aftership.filmlocations.domain.responses.SearchResponse;
import com.aftership.filmlocations.domain.services.FilmService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/films")
public class FilmsController {
  private final FilmService filmService;

  public FilmsController(FilmService filmService) {
    this.filmService = filmService;
  }

  @PostMapping("/upload")
  public ResponseEntity<FileUploadResponse> loadFilms(@RequestParam("file") MultipartFile file) {
    try {
      return ResponseEntity.ok(filmService.loadFilms(file.getInputStream(), file.getOriginalFilename()));
    } catch (IOException e) {
      throw new FilmsFileReadingException(file.getOriginalFilename());
    }
  }

  @GetMapping("/search/{title}")
  public ResponseEntity<SearchResponse> getFilms(@PathVariable("title") String title) {
    return ResponseEntity.ok(filmService.searchFilm(title.trim().toLowerCase()));
  }
}
