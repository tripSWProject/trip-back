package com.example.tripback.member;

import com.example.tripback.common.exception.NotFoundException;
import com.example.tripback.teams.TeamRepository;
import com.example.tripback.teams.Teams;
import com.example.tripback.users.UserRepository;
import com.example.tripback.users.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public String inviteMember(String email){
        userRepository.findByUserEmail(email).orElseThrow(()->
                new NotFoundException("회원이 존재하지 않습니다.")
        );

        return "회원이 존재합니다.";
    }

    public String acceptMember(String code, String userEmail){
        Teams teams = teamRepository.findByTeamCodes(code).orElseThrow(() ->
                new NotFoundException("그룹이 존재하지 않습니다."));
        Users users = userRepository.findByUserEmail(userEmail).orElseThrow(() ->
                new NotFoundException("회원이 존재하지 않습니다."));
        memberRepository.save(new Members(teams, users));
        return "맴버가 등록되었습니다.";
    }

}
