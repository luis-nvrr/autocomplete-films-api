package com.aftership.filmlocations.infrastructure;

import java.util.HashMap;
import java.util.Map;

import com.aftership.filmlocations.domain.entities.ProductionCompany;
import com.aftership.filmlocations.domain.repositories.ProductionCompanyRepository;

import org.springframework.stereotype.Repository;

@Repository
public class ProductionCompanyRepostioryImpl implements ProductionCompanyRepository {

  private Map<String, ProductionCompany> repository;

  public ProductionCompanyRepostioryImpl() {
    this.repository = new HashMap<>();
  }

  @Override
  public ProductionCompany getProductionCompany(String name) {
    return this.repository.get(name);
  }

  @Override
  public ProductionCompany saveProductionCompany(ProductionCompany productionCompany) {
    this.repository.remove(productionCompany.getName());
    this.repository.put(productionCompany.getName(), productionCompany);
    return productionCompany;
  }

}
