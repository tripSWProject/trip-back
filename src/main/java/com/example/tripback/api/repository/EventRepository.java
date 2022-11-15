package com.example.tripback.api.repository;

import com.example.tripback.api.entity.Events;
import com.example.tripback.api.entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Events, Long> {
    Optional<Events> findByEventId(Long EventId);
    void deleteByEventId(Long eventId);
    List<Events> findByTeamsAndStartDateOrEndDate(Teams teams, LocalDate startDate, LocalDate endDate);
}
