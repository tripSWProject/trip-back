package com.example.tripback.member;

import com.example.tripback.teams.Teams;
import com.example.tripback.users.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Members {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Teams teams;
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users users;

    public Members(Teams teams, Users users){
        this.teams = teams;
        this.users = users;
    }
}
