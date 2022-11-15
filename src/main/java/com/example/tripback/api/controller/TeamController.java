package com.example.tripback.api.controller;

import com.example.tripback.api.service.TeamService;
import com.example.tripback.common.utils.ApiUtils.ApiResult;
import com.example.tripback.api.request.PostUserReq;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.tripback.common.utils.ApiUtils.success;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/teams")
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ApiResult<Long> createTeam(@Valid @RequestBody PostUserReq postUserReq){
        return success(teamService.createTeam(postUserReq));
    }


}
