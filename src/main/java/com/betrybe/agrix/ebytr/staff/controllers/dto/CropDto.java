package com.betrybe.agrix.ebytr.staff.controllers.dto;


import com.betrybe.agrix.ebytr.staff.models.entities.Crop;
/**
 * record.
 */


public record CropDto(Long id, String name, Double plantedArea, Long farmId) {


  public Crop toCrop() {
    return new Crop(id, name, plantedArea);
  }

  public static CropDto toDto(Crop crop) {
    return new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getFarm().getId());
  }
}
