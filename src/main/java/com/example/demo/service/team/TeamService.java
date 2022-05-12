package com.example.demo.service.team;

import com.example.demo.domain.Team;
import com.example.demo.model.RequestTeamByNameModel;
import com.example.demo.model.ResponseTeamModel;
import com.example.demo.model.ResponseUserModel;
import com.example.demo.model.createTeam.ResponseCreateTeamModel;

import java.util.Set;

public interface TeamService {

    ResponseCreateTeamModel create(RequestTeamByNameModel requestTeamByNameModel, long id);

    Long fetchTeamRank(Team team);

    Set<ResponseTeamModel> fetchTeams();

    ResponseTeamModel getTeamByName(RequestTeamByNameModel requestTeamByNameModel);

    ResponseTeamModel fetchTeamById(Long teamId);

    ResponseTeamModel fetchTeamByUserId(Long userId);

    Set<ResponseTeamModel> fetchRanking();

    ResponseUserModel fetchTeamLeader(Long teamId);

    Team getTeamById(Long teamId);

    Team getTeamByLeaderId(Long leaderId);

    void save(Team team);
}
