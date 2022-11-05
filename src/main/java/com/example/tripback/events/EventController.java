package com.example.tripback.events;

import com.example.tripback.common.utils.ApiUtils.ApiResult;
import com.example.tripback.events.EventDto.responseList;
import com.example.tripback.events.EventDto.saveRequestDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDate;
import java.util.List;
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

    @GetMapping
    public ApiResult<responseList> eventList(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                             @RequestParam Long teamId){
        System.out.println("date = " + date);
        return success(new responseList(eventService.getOneDayCalendar(date, teamId)));
    }
}
