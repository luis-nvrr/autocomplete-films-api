package com.aftership.filmlocations.domain.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.aftership.filmlocations.domain.entities.Actor;
import com.aftership.filmlocations.domain.entities.Director;
import com.aftership.filmlocations.domain.entities.Distributor;
import com.aftership.filmlocations.domain.entities.Film;
import com.aftership.filmlocations.domain.entities.Location;
import com.aftership.filmlocations.domain.entities.ProductionCompany;
import com.aftership.filmlocations.domain.entities.SuggestTree;
import com.aftership.filmlocations.domain.entities.Writer;
import com.aftership.filmlocations.domain.entities.SuggestTree.Node;
import com.aftership.filmlocations.domain.exceptions.FilmsFileReadingException;
import com.aftership.filmlocations.domain.repositories.ActorRepository;
import com.aftership.filmlocations.domain.repositories.DirectorRepository;
import com.aftership.filmlocations.domain.repositories.DistributorRepository;
import com.aftership.filmlocations.domain.repositories.FilmRepository;
import com.aftership.filmlocations.domain.repositories.LocationRepository;
import com.aftership.filmlocations.domain.repositories.ProductionCompanyRepository;
import com.aftership.filmlocations.domain.repositories.WriterRepository;
import com.aftership.filmlocations.domain.responses.FileUploadResponse;
import com.aftership.filmlocations.domain.responses.SearchResponse;

import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService {
  private final ActorRepository actorRepository;
  private final DirectorRepository directorRepository;
  private final DistributorRepository distributorRepository;
  private final FilmRepository filmRepository;
  private final LocationRepository locationRepository;
  private final ProductionCompanyRepository productionCompanyRepository;
  private final WriterRepository writerRepository;
  private final SuggestTree tree;

  public FilmServiceImpl(ActorRepository actorRepository,
      DirectorRepository directorRepository,
      DistributorRepository distributorRepository,
      FilmRepository filmRepository,
      LocationRepository locationRepository,
      ProductionCompanyRepository productionCompanyRepository,
      WriterRepository writerRepository,
      SuggestTree tree) {
    this.actorRepository = actorRepository;
    this.directorRepository = directorRepository;
    this.distributorRepository = distributorRepository;
    this.filmRepository = filmRepository;
    this.locationRepository = locationRepository;
    this.productionCompanyRepository = productionCompanyRepository;
    this.writerRepository = writerRepository;
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
        line = bufferedReader.readLine();

        if (parsedFilm == null) {
          continue;
        }

        Film existingFilm = filmRepository.getFilm(parsedFilm.getTitle());

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
    try {
      String[] fields = line.split(",");
      String title = fields[0].trim();
      String releaseYear = fields[1].trim();
      Location location = createLocation(fields[2].trim());
      ProductionCompany productionCompany = createProductionCompany(fields[4].trim());
      Distributor distributor = createDistributor(fields[5].trim());
      Director director = createDirector(fields[6].trim());
      Writer writer = createWriter(fields[7].trim());
      List<Actor> actors = new ArrayList<>();
      actors.add(createActor(fields[8].trim()));
      actors.add(createActor(fields[9].trim()));
      return new Film(title, releaseYear, location, productionCompany, distributor, director, writer, actors);
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
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

  private Actor createActor(String name) {
    Actor savedActor = actorRepository.getActor(name);

    if (savedActor == null) {
      savedActor = new Actor(name);
      actorRepository.saveActor(savedActor);
    }

    return savedActor;
  }

  private Writer createWriter(String name) {
    Writer savedWriter = writerRepository.getWriter(name);

    if (savedWriter == null) {
      savedWriter = new Writer(name);
      writerRepository.saveWriter(savedWriter);
    }

    return savedWriter;
  }

  private Director createDirector(String name) {
    Director savedDirector = directorRepository.getDirector(name);

    if (savedDirector == null) {
      savedDirector = new Director(name);
      directorRepository.saveDirector(savedDirector);
    }

    return savedDirector;
  }

  private Distributor createDistributor(String name) {
    Distributor savedDistributor = distributorRepository.getDistributor(name);

    if (savedDistributor == null) {
      savedDistributor = new Distributor(name);
      distributorRepository.saveDistributor(savedDistributor);
    }

    return savedDistributor;
  }

  private ProductionCompany createProductionCompany(String name) {
    ProductionCompany savedProductionCompany = productionCompanyRepository.getProductionCompany(name);

    if (savedProductionCompany == null) {
      savedProductionCompany = new ProductionCompany(name);
      productionCompanyRepository.saveProductionCompany(savedProductionCompany);
    }

    return savedProductionCompany;
  }

  private Location createLocation(String name) {
    Location savedLocation = locationRepository.getLocation(name);

    if (savedLocation == null) {
      savedLocation = new Location(name);
      locationRepository.saveLocation(savedLocation);
    }

    return savedLocation;
  }

}
