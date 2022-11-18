package com.example.tripback.api.controller;

import com.example.tripback.api.dto.TeamDto.ListTeamResponse;
import com.example.tripback.api.dto.TeamDto.SaveTeam;
import com.example.tripback.api.service.TeamService;
import com.example.tripback.common.utils.ApiUtils.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.tripback.common.utils.ApiUtils.success;

@Tag(name = "teams", description = "그룹 API")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/teams")
public class TeamController {

    private final TeamService teamService;

    @Operation(summary = "그룹 생성")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResult<Long> createTeam(@Valid @ModelAttribute SaveTeam saveTeam){
        return success(teamService.createTeam(saveTeam));
    }

    @Operation(summary = "그룹 조회", description = "sortId - 1: 최신 그룹순, 2: 이름순")
    @GetMapping
    public ApiResult<ListTeamResponse> getTeamList(@RequestParam Long userSeq, @RequestParam int sortId){
        return success(new ListTeamResponse(teamService.getTeamList(userSeq, sortId)));
    }
}
