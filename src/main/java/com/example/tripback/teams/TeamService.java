package com.example.tripback.teams;

import com.example.tripback.common.exception.NotFoundException;
import com.example.tripback.member.MemberRepository;
import com.example.tripback.member.Members;
import com.example.tripback.teams.request.PostUserReq;
import com.example.tripback.users.UserRepository;
import com.example.tripback.users.Users;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    public String createTeam(PostUserReq postUserReq){
        String code = RandomStringUtils.randomAlphabetic(10);
        Teams teams = new Teams(postUserReq.getTeamName(), code);

        Users users = userRepository.findByUserEmail(postUserReq.getEmail()).orElseThrow(() ->
                new NotFoundException("회원을 찾지 못했습니다."));

        teamRepository.save(teams);
        memberRepository.save(
                new Members(teams, users)
        );
        return code;
    }


}
