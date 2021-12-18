package com.aftership.filmlocations.domain.repositories;

import com.aftership.filmlocations.domain.entities.Distributor;

public interface DistributorRepository {
  public Distributor getDistributor(String name);

  public Distributor saveDistributor(Distributor distributor);
}
