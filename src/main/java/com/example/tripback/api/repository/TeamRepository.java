package com.example.tripback.api.repository;

import com.example.tripback.api.entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Teams, Long> {
    Optional<Teams> findByTeamCodes(String code);
    Optional<Teams> findByTeamsId(Long teamId);
}
