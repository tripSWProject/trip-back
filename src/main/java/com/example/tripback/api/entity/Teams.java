package com.example.tripback.api.entity;

import com.example.tripback.api.entity.Members;
import lombok.*;

import javax.persistence.*;
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
    @ToString.Exclude
    private List<Members> membersList;

    public Teams(String name, String code){
        this.teamName = name;
        this.teamCodes = code;
    }
}
