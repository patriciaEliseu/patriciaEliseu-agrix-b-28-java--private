package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.models.entities.Crop;
import com.betrybe.agrix.ebytr.staff.models.entities.Farm;
import com.betrybe.agrix.ebytr.staff.models.repositories.CropRepository;
import com.betrybe.agrix.ebytr.staff.models.repositories.FarmRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * class.
 */

@Service
public class FarmService {
  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  Farm farm;

  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  public Optional<Farm> getFarmById(Long id) {
    return farmRepository.findById(id);
  }


  /**
   * getCropBetweenDate.
   */

  public List<Crop> getAllSearch(LocalDate start, LocalDate end) {
    List<Crop> allCrops = cropRepository.findAll();
    return allCrops.stream().filter(crop -> {
      LocalDate haverstDate = crop.getHarvestDate();
      return haverstDate != null && !haverstDate.isBefore(start) && !haverstDate.isAfter(end);
    }).collect(Collectors.toList());
  }
}
