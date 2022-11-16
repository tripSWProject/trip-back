package com.example.tripback.api.controller;

import com.example.tripback.common.utils.ApiUtils.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.example.tripback.common.utils.ApiUtils.success;

@Tag(name = "travels", description = "여행 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/travels")
public class travelController {

    private final com.example.tripback.api.service.travelService travelService;

    @Operation(summary = "교통 조회")
    @GetMapping
    public ApiResult<String> getRoute(@RequestParam double sx, @RequestParam double sy, @RequestParam double ex, @RequestParam double ey) throws IOException {
        return success(travelService.getPath(sx, sy, ex, ey));
    }

}
