package com.betrybe.agrix.ebytr.staff.controllers.dto;


import com.betrybe.agrix.ebytr.staff.models.entities.Crop;
import java.time.LocalDate;
/**
 * record.
 */


public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,
    Long farmId) {

  /**
   * constructor.
   */


  public Crop toCrop() {
    return new Crop(id, name, plantedArea, plantedDate, harvestDate, null);
  }


  /**
   * constructor static.
   */


  public static CropDto toDto(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId());
  }
}
