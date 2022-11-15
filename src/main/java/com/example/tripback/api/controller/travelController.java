package com.example.tripback.api.controller;

import com.example.tripback.common.utils.ApiUtils.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.example.tripback.common.utils.ApiUtils.success;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/travels")
public class travelController {

    private final com.example.tripback.api.service.travelService travelService;

    @GetMapping("/api/v1/travels")
    public ApiResult<String> getRoute(@RequestParam double sx, @RequestParam double sy, @RequestParam double ex, @RequestParam double ey) throws IOException {
        return success(travelService.getPath(sx, sy, ex, ey));
    }

}
