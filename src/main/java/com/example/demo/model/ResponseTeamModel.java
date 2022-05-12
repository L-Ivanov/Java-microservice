package com.example.demo.model;

import com.example.demo.model.UserModel;
import com.fasterxml.jackson.databind.ser.Serializers;

import java.util.Set;

public class ResponseTeamModel extends BaseModel {

    private String teamName;

    private Set<UserModel> teamMembers;

    private UserModel teamCreator;

    private Long rank;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Set<UserModel> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(Set<UserModel> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public UserModel getTeamCreator() {
        return teamCreator;
    }

    public void setTeamCreator(UserModel teamCreator) {
        this.teamCreator = teamCreator;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }
}
