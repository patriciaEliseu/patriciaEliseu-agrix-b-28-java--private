package com.betrybe.agrix.ebytr.staff.controllers;


/**
 * class.
 */


public class CustomError extends RuntimeException {
  int status;

  public CustomError(String message, int status) {
    super(message);
    this.status = status;
  }

  public CustomError(String message) {
    super(message);
  }

  public int getStatus() {
    return this.status;
  }
}
