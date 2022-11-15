package com.example.tripback.api.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostUserReq {
    @NotNull(message = "팀 이름은 Null 일 수 없습니다.")
    private String teamName;
    private String email;
}
