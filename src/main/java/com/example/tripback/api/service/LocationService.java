package com.example.tripback.api.service;

import com.example.tripback.api.dto.LocationDto.SaveLocation;
import com.example.tripback.api.entity.Events;
import com.example.tripback.api.entity.Locations;
import com.example.tripback.api.repository.EventRepository;
import com.example.tripback.api.repository.LocationRepository;
import com.example.tripback.common.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final EventRepository eventRepository;

    public LocationService(LocationRepository locationRepository, EventRepository eventRepository) {
        this.locationRepository = locationRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Long createLocation(SaveLocation saveLocation){
        Locations location = saveLocation.toEntity();
        Long eventId = saveLocation.getEventId();
        Events events = eventRepository.findByEventId(eventId).orElseThrow(
                () -> new NotFoundException("Not Found Event")
        );
        location.setEvents(events);
        locationRepository.save(location);
        return location.getLocationId();
    }

    @Transactional(rollbackFor = Exception.class)
    public Long deleteLocation(Long locationId){
        locationRepository.findByLocationId(locationId).orElseThrow(
                () -> new NotFoundException("Not Found Location")
        );
        locationRepository.deleteByLocationId(locationId);
        return locationId;
    }

    @Transactional(readOnly = true)
    public List<Locations> getLocation(Long eventId, String planName){
        Events events = eventRepository.findByEventId(eventId).orElseThrow(
                () -> new NotFoundException("Not Found Event")
        );

        return locationRepository.findByEventsAndPlanName(events, planName);
    }
}
