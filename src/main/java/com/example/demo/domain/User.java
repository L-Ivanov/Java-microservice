package com.example.demo.domain;

import com.example.demo.constants.Constants;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    private Long id;

    private String username;

    private Long rank;

    private Team team;

    public User() {
        this.rank = Constants.START_RANK;
    }

    @Id
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
