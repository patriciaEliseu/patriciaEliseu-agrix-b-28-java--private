package com.betrybe.agrix.ebytr.staff.service;


import com.betrybe.agrix.ebytr.staff.controllers.dto.FertilizersDto;
import com.betrybe.agrix.ebytr.staff.models.entities.Fertilizer;
import com.betrybe.agrix.ebytr.staff.models.repositories.CropRepository;
import com.betrybe.agrix.ebytr.staff.models.repositories.FertilizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * class.
 */

@Service
public class FertilizerService {
  private final FertilizerRepository fertilizerRepository;
  private final CropRepository cropRepository;

  @Autowired
  public FertilizerService(
      FertilizerRepository fertilizerRepository,
      CropRepository cropRepository
  ) {
    this.fertilizerRepository = fertilizerRepository;
    this.cropRepository = cropRepository;
  }


  public Fertilizer insertFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAllFertilizer() {
    return fertilizerRepository.findAll();
  }
}
