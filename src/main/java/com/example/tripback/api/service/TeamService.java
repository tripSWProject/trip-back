package com.example.tripback.api.service;

import com.example.tripback.api.entity.Teams;
import com.example.tripback.api.repository.TeamRepository;
import com.example.tripback.common.exception.NotFoundException;
import com.example.tripback.api.repository.MemberRepository;
import com.example.tripback.api.entity.Members;
import com.example.tripback.api.request.PostUserReq;
import com.example.tripback.api.repository.UserRepository;
import com.example.tripback.api.entity.User;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createTeam(@RequestBody PostUserReq postUserReq){
        String code = RandomStringUtils.randomAlphabetic(10);
        Teams teams = new Teams(postUserReq.getTeamName(), code);

        User user = userRepository.findByUserEmail(postUserReq.getEmail()).orElseThrow(() ->
                new NotFoundException("회원을 찾지 못했습니다."));

        Teams realTeam = teamRepository.save(teams);
        memberRepository.save(
                new Members(teams, user)
        );
        return realTeam.getTeamsId();
    }


}
