package com.betrybe.agrix.ebytr.staff.controllers;

import static org.springframework.web.servlet.function.ServerResponse.status;

import com.betrybe.agrix.ebytr.staff.controllers.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controllers.dto.FertilizersDto;
import com.betrybe.agrix.ebytr.staff.models.entities.Crop;
import com.betrybe.agrix.ebytr.staff.models.entities.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * class.
 */

@RestController
@RequestMapping(value = "/crops")
public class CropController {
  private CropService cropService;
  private FarmService farmService;

  @Autowired
  public CropController(CropService cropService, FarmService farmService) {
    this.cropService = cropService;
    this.farmService = farmService;
  }


  /**
   * getCrop.
   */


  @GetMapping()
  public List<CropDto> getAllCrops() {
    List<Crop> listCrops = cropService.getAllCrops();
    Stream<CropDto> cropDtoStream = listCrops.stream()
        .map(crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId()));
    return cropDtoStream.toList();
  }


  /**
   * getById.
   */

  @GetMapping("/{id}")
  public CropDto getCropById(@PathVariable Long id) throws CustomError {
    Optional<Crop> optionalCrop = cropService.getCropById(id);

    if (optionalCrop.isEmpty()) {
      throw new CustomError("Plantação não encontrada!", 404);

    }

    return optionalCrop.map(crop -> CropDto.toDto(crop)).get();
  }


  /**
   * getSearch.
   */


  @GetMapping("/search")
  public List<CropDto> getAllSearch(
      @RequestParam("start") LocalDate startDate,
      @RequestParam("end") LocalDate endDate) {
    List<Crop> listSearch = farmService.getAllSearch(startDate, endDate);
    return listSearch.stream().map(crop -> new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId())).toList();
  }


  /**
   * PostCropIdFertId.
   */

  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> setFertilizer(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId
  ) {
    cropService.setFertilizer(cropId, fertilizerId);

    return ResponseEntity.status(HttpStatus.CREATED
    ).body("Fertilizante e plantação associados com sucesso!");


  }


  /**
   * Get.
   */


  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<?> getAllFertilizerbyCropId(
      @PathVariable Long cropId
  ) throws CropNotFoundException {
    Optional<Crop> crop = cropService.getCropById(cropId);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }
    List<Fertilizer> listaFertilizantes = crop.get().getFertilizers();
    return ResponseEntity.status(HttpStatus.OK).body(listaFertilizantes);


  }


}
