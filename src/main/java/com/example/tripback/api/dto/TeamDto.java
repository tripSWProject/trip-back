package com.example.tripback.api.dto;

import com.example.tripback.api.dto.UserDto.simpleUser;
import com.example.tripback.api.entity.Members;
import com.example.tripback.api.entity.Teams;
import com.example.tripback.api.entity.User;
import com.example.tripback.api.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamDto {

    @Getter @Setter
    @NoArgsConstructor
    public static class SaveTeam{
        @NotNull
        private Long userId;
        @NotNull
        private String teamName;
        private MultipartFile teamImg;

        public Teams toEntity(){
            return Teams.builder()
                    .teamName(teamName).build();
        }
    }

    @Getter
    @Setter
    public static class simpleTeam{
        private Long teamsId;
        private String teamName;
        private String teamImgUrl;
        private List<simpleUser> membersList = new ArrayList<>();

        private MemberRepository memberRepository;
        public simpleTeam(Teams teams){

            this.teamsId = teams.getTeamsId();
            this.teamName = teams.getTeamName();
            this.teamImgUrl = teams.getTeamImgUrl();
            List<Members> membersList = teams.getMembersList();
            for(Members members : membersList){
                System.out.println("members.getMemberId() = " + members.getMemberId());
                this.membersList.add(new simpleUser(members.getUser().getUserName(), members.getUser().getProfileImageUrl()));
            }
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ListTeamResponse{
        private List<simpleTeam> teamList;

        public ListTeamResponse(List<Teams> teams){
            this.teamList = teams.stream()
                    .map(simpleTeam::new)
                    .collect(Collectors.toList());
        }
    }
}
