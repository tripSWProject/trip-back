package com.example.tripback.api.controller;

import com.example.tripback.api.service.TeamService;
import com.example.tripback.common.utils.ApiUtils.ApiResult;
import com.example.tripback.api.request.PostUserReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.tripback.common.utils.ApiUtils.success;

@Tag(name = "teams", description = "그룹 API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/teams")
public class TeamController {

    private final TeamService teamService;

    @Operation(summary = "그룹 생성")
    @PostMapping
    public ApiResult<Long> createTeam(@Valid @RequestBody PostUserReq postUserReq){
        return success(teamService.createTeam(postUserReq));
    }


}
