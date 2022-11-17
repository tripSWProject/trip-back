package com.example.tripback.api.dto;

import com.example.tripback.api.entity.Members;
import com.example.tripback.api.entity.Teams;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

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
    @NoArgsConstructor
    public static class ListTeamResponse{
        private List<Teams> teamList;

        public ListTeamResponse(List<Teams> teamList) {
            this.teamList = teamList;
        }
    }
}
