package com.example.demo.domain;

import com.example.demo.constants.Constants;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    private String teamName;

    private Set<User> teamMembers;

    private User teamCreator;

    private Long rank;

    public Team() {
        this.rank = Constants.START_RANK;
        this.teamMembers = new HashSet<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    public Set<User> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Set<User> teamMembers) {
        this.teamMembers = teamMembers;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public User getTeamCreator() {
        return teamCreator;
    }

    public void setTeamCreator(User teamCreator) {
        this.teamCreator = teamCreator;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

}
