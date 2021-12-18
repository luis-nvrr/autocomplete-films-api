package com.aftership.filmlocations.infrastructure;

import java.util.HashMap;
import java.util.Map;

import com.aftership.filmlocations.domain.entities.Distributor;
import com.aftership.filmlocations.domain.repositories.DistributorRepository;

import org.springframework.stereotype.Repository;

@Repository
public class DistributorRepositoryImpl implements DistributorRepository {

  private Map<String, Distributor> repository;

  public DistributorRepositoryImpl() {
    this.repository = new HashMap<>();
  }

  @Override
  public Distributor getDistributor(String name) {
    return this.repository.get(name);
  }

  @Override
  public Distributor saveDistributor(Distributor distributor) {
    this.repository.remove(distributor.getName());
    this.repository.put(distributor.getName(), distributor);
    return distributor;
  }

}
