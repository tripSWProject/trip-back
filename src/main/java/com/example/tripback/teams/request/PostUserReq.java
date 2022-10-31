package com.example.tripback.teams.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@AllArgsConstructor
public class PostUserReq {
    private String teamName;
    private String email;
}
