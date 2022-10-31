package com.example.tripback.users;

import com.example.tripback.member.Members;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userEmail;
    private String accessToken;

    @OneToMany(mappedBy = "memberId", fetch = FetchType.LAZY)
    private List<Members> membersList = new ArrayList<>();

    public Users(String email, String token) {
        this.userEmail = email;
        this.accessToken = token;
    }

}
