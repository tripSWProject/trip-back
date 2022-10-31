package com.example.tripback.teams;

import com.example.tripback.teams.request.PostUserReq;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    String createTeam(PostUserReq postUserReq){
        String code = RandomStringUtils.randomAlphabetic(10);

        teamRepository.save(new Teams(
                postUserReq.getTeamName(), code
        ));
        return "그룹이 생성되었습니다.";
    }


}
