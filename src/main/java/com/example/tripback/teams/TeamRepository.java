package com.example.tripback.teams;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Teams, Long> {
    Optional<Teams> findByTeamCodes(String code);
}
