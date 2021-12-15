package com.aftership.filmlocations.domain.responses;

import java.util.List;

public class SearchResponse {
  private String query;
  private List<String> suggestions;

  public SearchResponse(String query, List<String> suggestions) {
    this.query = query;
    this.suggestions = suggestions;
  }

  public String getQuery() {
    return this.query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public List<String> getSuggestions() {
    return this.suggestions;
  }

  public void setSuggestions(List<String> suggestions) {
    this.suggestions = suggestions;
  }
}
