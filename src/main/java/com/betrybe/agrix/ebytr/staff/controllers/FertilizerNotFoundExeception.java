package com.betrybe.agrix.ebytr.staff.controllers;

/**
 * Exception.
 */


public class FertilizerNotFoundExeception extends RuntimeException {
  public FertilizerNotFoundExeception() {
    super("Fertilizante não encontrado!");
  }
}