package com.example.demo.model.joinPlayer;


import com.example.demo.model.UserModel;

public class RequestAddPlayerModel {

    private String teamName;

    private UserModel member;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public UserModel getMember() {
        return member;
    }

    public void setMember(UserModel member) {
        this.member = member;
    }
}
