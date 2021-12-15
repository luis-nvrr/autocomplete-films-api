package com.aftership.filmlocations.domain.responses;

public class FileUploadResponse {
  private String message;

  public FileUploadResponse(String name) {
    this.setMessage(name);
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String name) {
    this.message = "File " + name + " uploaded successfully.";
  }
}
