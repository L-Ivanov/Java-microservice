package com.example.demo.repository;

import com.example.demo.domain.Invitation;
import com.example.demo.domain.Team;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    Optional<Invitation> findUserById(Long userId);

    List<Invitation> findAllByProcessOfBefore(LocalDateTime hour);

    List<Invitation> findAllByTeam(Team team);

    Invitation findByUserAndTeam(User user, Team team);

}
