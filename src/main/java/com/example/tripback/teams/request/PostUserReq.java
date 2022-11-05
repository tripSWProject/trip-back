package com.example.tripback.teams.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter @Setter
@ToString
@AllArgsConstructor
public class PostUserReq {
    @NotNull(message = "팀 이름은 Null 일 수 없습니다.")
    private String teamName;
    private String email;
}
