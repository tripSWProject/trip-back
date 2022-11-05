package com.example.tripback.events;

import com.example.tripback.teams.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Events, Long> {
    Optional<Events> findByEventId(Long EventId);
    void deleteByEventId(Long eventId);
    List<Events> findByTeamsAndStartDateOrEndDate(Teams teams, LocalDate startDate, LocalDate endDate);
}
