package com.example.tripback.api.repository;

import com.example.tripback.api.entity.Members;
import com.example.tripback.api.entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Members, String> {
    List<Members> findByTeams(Teams teams);
}
