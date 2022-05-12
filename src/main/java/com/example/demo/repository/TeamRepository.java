package com.example.demo.repository;

import com.example.demo.domain.Team;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existByTeamName(String teamName);

    Optional<Team> findByTeamName(String teamName);

    Optional<Team> findByTeamCreatorId(Long id);

    Optional<Team> findById(Long id);

    Optional<Team> findTeamMembers(User user);


}
