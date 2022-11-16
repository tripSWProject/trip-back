package com.example.tripback.api.controller;

import com.example.tripback.api.dto.LocationDto;
import com.example.tripback.api.dto.LocationDto.SaveLocation;
import com.example.tripback.api.service.LocationService;
import com.example.tripback.common.utils.ApiUtils;
import com.example.tripback.common.utils.ApiUtils.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.tripback.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @Operation(summary = "장소 등록")
    @PostMapping
    private ApiResult<Long> saveLocation(@Valid @RequestBody SaveLocation saveLocation){
        return success(locationService.createLocation(saveLocation));
    }
}
