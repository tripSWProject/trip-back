package com.example.tripback.member;

import com.example.tripback.teams.Teams;
import com.example.tripback.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Members, String> {
    List<Members> findByTeams(Teams teams);
    void deleteByUsers(Users users);
}
