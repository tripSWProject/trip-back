package com.example.tripback.events;

import com.example.tripback.common.exception.NotFoundException;
import com.example.tripback.events.EventDto.PatchRequestDto;
import com.example.tripback.events.EventDto.saveRequestDto;
import com.example.tripback.teams.TeamRepository;
import com.example.tripback.teams.Teams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final TeamRepository teamRepository;

    public EventService(EventRepository eventRepository, TeamRepository teamRepository) {
        this.eventRepository = eventRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long createEvent(saveRequestDto requestDto){
        Events events = requestDto.toEntity();

        Teams teams = teamRepository.findByTeamsId(requestDto.getTeamId())
                .orElseThrow(() -> new NotFoundException("Not Found Team"));
        events.setTeams(teams);

        return eventRepository.save(events).getEventId();
    }

    @Transactional(readOnly = true)
    public List<Events> getOneDayCalendar(LocalDate date, Long teamId){
        Teams teams = teamRepository.findByTeamsId(teamId)
                .orElseThrow(() -> new NotFoundException("Not Found Team"));
        return eventRepository.findByTeamsAndStartDateOrEndDate(teams, date, date);
    }

    @Transactional(rollbackFor = Exception.class)
    public Long patchEvent(PatchRequestDto patchRequestDto){
        Events events = eventRepository.findByEventId(patchRequestDto.getEventId()).orElseThrow(
                () -> new NotFoundException("Not Found Event")
        );
        events.update(patchRequestDto);
        eventRepository.save(events);
        return events.getEventId();
    }

    @Transactional(rollbackFor = Exception.class)
    public Long deleteEvent(Long eventId){
        Events events = eventRepository.findByEventId(eventId).orElseThrow(
                () -> new NotFoundException("Not Found Event")
        );
        eventRepository.deleteByEventId(eventId);
        return eventId;
    }
}
