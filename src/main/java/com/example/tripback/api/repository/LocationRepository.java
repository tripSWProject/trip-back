package com.example.tripback.api.repository;

import com.example.tripback.api.entity.Locations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Locations, Long> {
    void deleteByLocationId(Long locationId);
    Optional<Locations> findByLocationId(Long locationId);
}
