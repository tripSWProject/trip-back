package com.example.tripback.api.repository;

import com.example.tripback.api.entity.Locations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Locations, Long> {

}
