package com.betrybe.agrix.ebytr.staff.controllers;

/**
 * Exception.
 */


public class CropNotFoundException extends RuntimeException {
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}

