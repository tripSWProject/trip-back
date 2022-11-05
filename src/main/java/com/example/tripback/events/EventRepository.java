package com.example.tripback.events;

import com.example.tripback.teams.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Events, Long> {
    List<Events> findByTeamsAndStartDateOrEndDate(Teams teams, LocalDate startDate, LocalDate endDate);
}
