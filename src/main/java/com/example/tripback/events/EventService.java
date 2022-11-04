package com.example.tripback.events;

import com.example.tripback.common.exception.NotFoundException;
import com.example.tripback.events.EventDto.saveRequestDto;
import com.example.tripback.teams.TeamRepository;
import com.example.tripback.teams.Teams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final TeamRepository teamRepository;

    public EventService(EventRepository eventRepository, TeamRepository teamRepository) {
        this.eventRepository = eventRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Long createEvent(saveRequestDto requestDto){
        Events events = requestDto.toEntity();

        Teams teams = teamRepository.findByTeamsId(requestDto.getTeamId())
                .orElseThrow(() -> new NotFoundException("Not Found Team"));
        events.setTeams(teams);

        return eventRepository.save(events).getEventId();
    }

}
