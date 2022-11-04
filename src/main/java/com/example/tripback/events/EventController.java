package com.example.tripback.events;

import com.example.tripback.common.utils.ApiUtils;
import com.example.tripback.common.utils.ApiUtils.ApiResult;
import com.example.tripback.events.EventDto.saveRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.tripback.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ApiResult<Long> createEvent(@Valid @RequestBody saveRequestDto saveRequestDto){
        return success(eventService.createEvent(saveRequestDto));
    }


}
