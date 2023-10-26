package com.betrybe.agrix.ebytr.staff.controllers.dto;


import com.betrybe.agrix.ebytr.staff.models.entities.Fertilizer;
/**
 * record.
 */


public record FertilizersDto(
    Long id,
    String name,
    String brand,
    String composition) {


  /**
   * constructor.
   */


  public Fertilizer toFertilizer() {
    return new Fertilizer(id, name, brand, composition, null);
  }


  /**
   * constructor static.
   */

  public static FertilizersDto fertilizersDto(Fertilizer fertilizer) {
    return new FertilizersDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }
}


