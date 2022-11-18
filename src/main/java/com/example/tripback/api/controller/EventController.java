package com.example.tripback.api.controller;

import com.example.tripback.common.utils.ApiUtils.ApiResult;
import com.example.tripback.api.dto.EventDto.PatchRequestDto;
import com.example.tripback.api.dto.EventDto.responseList;
import com.example.tripback.api.dto.EventDto.saveRequestDto;
import com.example.tripback.api.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDate;

import static com.example.tripback.common.utils.ApiUtils.success;

@Tag(name = "events", description = "일정 API")
@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "일정 등록")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResult<Long> createEvent(@Valid @ModelAttribute saveRequestDto saveRequestDto){
        return success(eventService.createEvent(saveRequestDto));
    }

    @Operation(summary = "일정 조회")
    @GetMapping
    public ApiResult<responseList> eventList(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                             @RequestParam Long teamId){
        return success(new responseList(eventService.getOneDayCalendar(date, teamId)));
    }

    @Operation(summary = "일정 수정")
    @PatchMapping
    public ApiResult<Long> patchEvent(@Valid @RequestBody PatchRequestDto patchRequestDto){
        return success(eventService.patchEvent(patchRequestDto));
    }

    @Operation(summary = "일정 삭제")
    @DeleteMapping
    public ApiResult<Long> deleteEvent(@RequestParam Long eventId){
        return success(eventService.deleteEvent(eventId));
    }
}
