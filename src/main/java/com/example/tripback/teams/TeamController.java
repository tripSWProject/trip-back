package com.example.tripback.teams;

import com.example.tripback.common.utils.ApiUtils;
import com.example.tripback.teams.request.PostUserReq;
import com.example.tripback.users.Users;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.example.tripback.common.utils.ApiUtils.success;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ApiUtils.ApiResult<String> createTeam(PostUserReq postUserReq){
        return success(teamService.createTeam(postUserReq));
    }


}
