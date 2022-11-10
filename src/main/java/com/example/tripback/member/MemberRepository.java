package com.example.tripback.member;

import com.example.tripback.teams.Teams;
import com.example.tripback.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Members, String> {
    List<Members> findByTeams(Teams teams);
}
