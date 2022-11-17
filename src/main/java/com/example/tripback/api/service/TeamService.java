package com.example.tripback.api.service;

import com.example.tripback.api.dto.TeamDto;
import com.example.tripback.api.dto.TeamDto.SaveTeam;
import com.example.tripback.api.entity.Teams;
import com.example.tripback.api.repository.TeamRepository;
import com.example.tripback.common.exception.NotFoundException;
import com.example.tripback.api.repository.MemberRepository;
import com.example.tripback.api.entity.Members;
import com.example.tripback.api.request.PostUserReq;
import com.example.tripback.api.repository.UserRepository;
import com.example.tripback.api.entity.User;
import com.example.tripback.common.utils.S3Uploader;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;

    @Transactional(rollbackFor = Exception.class)
    public Long createTeam(SaveTeam saveTeam){
        Teams teams = saveTeam.toEntity();

        User user = userRepository.findByUserSeq(saveTeam.getUserId()).orElseThrow(() ->
                new NotFoundException("회원을 찾지 못했습니다."));

        String code = RandomStringUtils.randomAlphabetic(10);
        teams.setTeamImgUrl(saveFile(saveTeam.getTeamImg(), code, teams.getTeamsId()));
        teams.setTeamCodes(code);

        Teams realTeam = teamRepository.save(teams);

        memberRepository.save(
                new Members(realTeam, user)
        );
        return realTeam.getTeamsId();
    }

    @Transactional(readOnly = true)
    public List<Teams> getTeamList(Long userId, int sortId){
        User user = userRepository.findByUserSeq(userId).orElseThrow(
                () -> new NotFoundException("Not Found User")
        );
        List<Members> findUser = memberRepository.findByUser(user);

        ArrayList<Teams> teamList = new ArrayList<>();
        for(Members members : findUser){
            Teams teams = members.getTeams();
            List<Members> byTeams = memberRepository.findByTeams(teams);
            teams.setMembersList(byTeams);
            teamList.add(teams);
        }
        return teamList;
    }

    private String saveFile(MultipartFile img, String title, Long userIdx){
        String imgName = title + "_" + img.getOriginalFilename();
        String imgPath = "team/" + userIdx;
        try {
            String uploadName = s3Uploader.upload(img, imgPath, imgName);
            return uploadName;
        } catch (IOException e) {
            throw new NotFoundException("failed to save img");
        }
    }
}
