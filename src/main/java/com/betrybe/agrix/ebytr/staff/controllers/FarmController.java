package com.betrybe.agrix.ebytr.staff.controllers;

import com.betrybe.agrix.ebytr.staff.controllers.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controllers.dto.FarmDto;
import com.betrybe.agrix.ebytr.staff.models.entities.Crop;
import com.betrybe.agrix.ebytr.staff.models.entities.Farm;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
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
@RequestMapping(value = "/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * post.
   */

  @PostMapping()
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.insertFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * Get.
   */

  @GetMapping
  public List<FarmDto> getAllFarm() {
    List<Farm> allFarms = farmService.getAllFarms();
    return allFarms.stream()
        .map(farm -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
  }


  /**
   * GetId.
   */


  @GetMapping("/{id}")
  public Farm getFarmById(@PathVariable Long id) throws CustomError {
    Optional<Farm> optionalFarm = farmService.getFarmById(id);

    if (optionalFarm.isEmpty()) {
      throw new CustomError("Fazenda não encontrada!", 404);

    }

    return optionalFarm.get();
  }


  /**
   * post crops.
   */

  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> postCropsFarmId(
      @PathVariable Long farmId, @RequestBody CropDto cropDto) throws CustomError {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new CustomError("Fazenda não encontrada!", 404);
    }
    Crop crop = cropDto.toCrop();
    crop.setFarm(optionalFarm.get());

    cropService.insertCrop(crop);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new CropDto(
            crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getFarm().getId()));
  }


  /**
   * get crops.
   */

  @GetMapping("/{farmId}/crops")
  public List<CropDto> getCropsFarmId(
      @PathVariable Long farmId) throws CustomError {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    if (optionalFarm.isPresent()) {

      List<Crop> allCrops = optionalFarm.get().getCrops();
      return allCrops.stream()
          .map(crop -> CropDto.toDto(crop)).toList();
    } else {
      throw new CustomError("Fazenda não encontrada!");
    }
  }

}
