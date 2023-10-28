package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.controllers.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.controllers.FertilizerNotFoundExeception;
import com.betrybe.agrix.ebytr.staff.models.entities.Crop;
import com.betrybe.agrix.ebytr.staff.models.entities.Fertilizer;
import com.betrybe.agrix.ebytr.staff.models.repositories.CropRepository;
import com.betrybe.agrix.ebytr.staff.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * class.
 */


@Service
public class CropService {
  private CropRepository cropRepository;
  private FertilizerRepository fertilizerRepository;


  /**
   * constructor.
   */
  @Autowired
  public CropService(
      CropRepository cropRepository,
      FertilizerRepository fertilizerRepository) {

    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public Crop insertCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  public Optional<Crop> getCropById(Long id) {
    return cropRepository.findById(id);
  }

  public List<Crop> getAllSearch(LocalDate start, LocalDate end) {
    return cropRepository.findAll();
  }


  /**
   * Metodo.
   */


  public void setFertilizer(
      Long cropId,
      Long fertilizerId
  ) throws CropNotFoundException, FertilizerNotFoundExeception {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);
    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }
    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(fertilizerId);
    if (optionalFertilizer.isEmpty()) {
      throw new FertilizerNotFoundExeception();
    }
    Crop crop = optionalCrop.get();
    Fertilizer fertilizer = optionalFertilizer.get();

    crop.getFertilizers().add(fertilizer);


    cropRepository.save(crop);


  }

  public Optional<Fertilizer> getAllFertilizerByCropId(Long cropId) {
    return fertilizerRepository.findById(cropId);
  }

}
