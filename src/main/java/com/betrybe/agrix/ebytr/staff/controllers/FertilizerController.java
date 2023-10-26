package com.betrybe.agrix.ebytr.staff.controllers;


import com.betrybe.agrix.ebytr.staff.controllers.dto.FertilizersDto;
import com.betrybe.agrix.ebytr.staff.models.entities.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * class.
 */

@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {
  private FertilizerService fertilizerService;
  private CropService cropService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService, CropService cropService) {
    this.fertilizerService = fertilizerService;
    this.cropService = cropService;
  }


  /**
   * PostFertilizer.
   */


  @PostMapping()
  public ResponseEntity<Fertilizer> createFertilizer(
      @RequestBody FertilizersDto fertilizersDto) {
    Fertilizer fertilizerCreate = fertilizerService.insertFertilizer(
        fertilizersDto.toFertilizer());
    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizerCreate);
  }

  //
  //  /**
  //   * GetFertilizer.
  //   */


}
