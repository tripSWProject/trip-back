package com.example.tripback.api.entity;

import com.example.tripback.oauth2.entity.ProviderType;
import com.example.tripback.oauth2.entity.RoleType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    private String userId;
    private String userName;
    private String password;
    private String userEmail;
    private String emailVerifiedYn;

    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "memberId", fetch = FetchType.LAZY)
    private List<Members> membersList;

    public User(
            @NotNull String userId,
            @NotNull String username,
            @NotNull String userEmail,
            @NotNull @Size(max = 1) String emailVerifiedYn,
            @NotNull String profileImageUrl,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType,
            @NotNull LocalDateTime createdAt
    ) {
        this.userId = userId;
        this.userName = username;
        this.password = "NO_PASS";
        this.userEmail = userEmail != null ? userEmail : "NO_EMAIL";
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
        this.createdAt = createdAt;
    }

}
