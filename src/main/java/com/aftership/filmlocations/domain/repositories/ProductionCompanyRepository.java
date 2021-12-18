package com.aftership.filmlocations.domain.repositories;

import com.aftership.filmlocations.domain.entities.ProductionCompany;

public interface ProductionCompanyRepository {
  public ProductionCompany getProductionCompany(String name);

  public ProductionCompany saveProductionCompany(ProductionCompany productionCompany);
}
