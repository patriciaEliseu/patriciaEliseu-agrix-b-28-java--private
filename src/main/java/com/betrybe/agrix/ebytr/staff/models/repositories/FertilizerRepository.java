package com.betrybe.agrix.ebytr.staff.models.repositories;


import com.betrybe.agrix.ebytr.staff.models.entities.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * interface.
 */


@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
}
