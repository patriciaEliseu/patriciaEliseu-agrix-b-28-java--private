package com.betrybe.agrix.ebytr.staff.controllers;


import com.betrybe.agrix.ebytr.staff.controllers.dto.FertilizersDto;
import com.betrybe.agrix.ebytr.staff.models.entities.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


  /**
   * GetFertilizer.
   */


  @GetMapping()
  public List<FertilizersDto> getAllFertilizer() {
    List<Fertilizer> allFertilizer = fertilizerService.getAllFertilizer();
    return allFertilizer.stream().map(fert -> new FertilizersDto(
        fert.getId(),
        fert.getName(),
        fert.getBrand(),
        fert.getComposition())).collect(Collectors.toList());
  }


  /**
   * getIdFertilizer.
   */


  @GetMapping("/{id}")
  public Fertilizer getFertilizerById(@PathVariable Long id) throws CustomError {
    Optional<Fertilizer> optionalFertilizer = fertilizerService.getFertilizerById(id);

    if (optionalFertilizer.isEmpty()) {
      throw new CustomError("Fertilizante não encontrado!", 404);
    }
    return optionalFertilizer.get();
  }

}
