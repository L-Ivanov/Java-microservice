package com.example.demo.model;

import com.example.demo.domain.Team;

public class ResponseUserModel extends BaseModel {

    private String username;

    private Long rank;

    private Team team;

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
