package com.example.tripback.api.service;

import com.example.tripback.api.entity.Members;
import com.example.tripback.api.repository.MemberRepository;
import com.example.tripback.common.exception.NotFoundException;
import com.example.tripback.api.request.PostMemberReq;
import com.example.tripback.api.repository.TeamRepository;
import com.example.tripback.api.entity.Teams;
import com.example.tripback.api.repository.UserRepository;
import com.example.tripback.api.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public MemberService(UserRepository userRepository, TeamRepository teamRepository, MemberRepository memberRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public String inviteMember(String email){
        userRepository.findByUserEmail(email).orElseThrow(()->
                new NotFoundException("회원이 존재하지 않습니다.")
        );
        return "회원이 존재합니다.";
    }

    @Transactional
    public String acceptMember(PostMemberReq postMemberReq){
        Teams teams = teamRepository.findByTeamCodes(postMemberReq.getCode()).orElseThrow(() ->
                new NotFoundException("그룹이 존재하지 않습니다."));
        User user = userRepository.findByUserEmail(postMemberReq.getEmail()).orElseThrow(() ->
                new NotFoundException("회원이 존재하지 않습니다."));
        memberRepository.save(new Members(teams, user));

        return "맴버가 등록되었습니다.";
    }

    @Transactional(readOnly = true)
    public List<String> memberList(Long teamId){
        Teams teams = teamRepository.findByTeamsId(teamId).orElseThrow(() ->
                new NotFoundException("그룹을 찾지 못했습니다."));
        List<Members> teamList = memberRepository.findByTeams(teams);

        List<String> membersList = new ArrayList<>();

        for(Members members : teamList){
            membersList.add(members.getUser().getUserName());
        }

        return membersList;
    }

    @Transactional
    public Long deleteMember(Long teamId, Long userId){
        teamRepository.findByTeamsId(teamId).orElseThrow(() ->
                new NotFoundException("그룹을 찾지 못했습니다."));
//        User user = userRepository.findByUserId(userId).orElseThrow(() ->
//                new NotFoundException("회원이 존재하지 않습니다."));
//        memberRepository.deleteByUsers(user);
        return userId;
    }
}
