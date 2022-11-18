package com.example.tripback.api.entity;

import com.example.tripback.api.entity.Members;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teams {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamsId;

    private String teamName;
    private String teamCodes;
    private String teamImgUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "memberId", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Members> membersList;

    public Teams(String name, String teamImgUrl, String code){
        this.teamName = name;
        this.teamImgUrl = teamImgUrl;
        this.teamCodes = code;
    }
}
