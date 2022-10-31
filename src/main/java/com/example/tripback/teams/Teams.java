package com.example.tripback.teams;

import com.example.tripback.member.Members;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teams {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamsId;

    private String teamName;
    private String teamCodes;

    @OneToMany(mappedBy = "memberId", fetch = FetchType.LAZY)
    private List<Members> membersList = new ArrayList<>();
}
