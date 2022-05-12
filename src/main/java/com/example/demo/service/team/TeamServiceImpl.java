package com.example.demo.service.team;

import com.example.demo.domain.Team;
import com.example.demo.model.RequestTeamByNameModel;
import com.example.demo.model.ResponseTeamModel;
import com.example.demo.model.ResponseUserModel;
import com.example.demo.model.createTeam.ResponseCreateTeamModel;
import com.example.demo.repository.InvitationRepository;
import com.example.demo.repository.TeamRepository;
import com.example.demo.service.invitation.InvitationService;
import com.example.demo.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {
    private final InvitationRepository invitationRepository;
    private final InvitationService invitationService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;


    @Autowired
    public TeamServiceImpl(InvitationRepository invitationRepository,
                           InvitationService invitationService,
                           UserService userService, ModelMapper modelMapper, TeamRepository teamRepository) {
        this.invitationRepository = invitationRepository;
        this.invitationService = invitationService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
    }

    @Override
    public ResponseCreateTeamModel create(RequestTeamByNameModel requestTeamByNameModel, long id) {
        throwIfExistTeamByName(requestTeamByNameModel);
        throwIfMemberHasTeam(id);
        return null;
    }

    private void throwIfMemberHasTeam(long id) {
        if (this.userService.isPlayerHasTeam(id)) {
            throw new IllegalArgumentException("You cannot join or create a team because you are involved in another.");
        }
    }

    private void throwIfExistTeamByName(RequestTeamByNameModel requestTeamByNameModel) {
        if (this.teamRepository.existByTeamName(requestTeamByNameModel.getTeamName())) {
            throw new IllegalArgumentException("Team with this name already exist.");
        }
    }

    @Override
    public Long fetchTeamRank(Team team) {
        return null;
    }

    @Override
    public Set<ResponseTeamModel> fetchTeams() {
        return null;
    }

    @Override
    public ResponseTeamModel getTeamByName(RequestTeamByNameModel requestTeamByNameModel) {
        return null;
    }

    @Override
    public ResponseTeamModel fetchTeamById(Long teamId) {
        return null;
    }

    @Override
    public ResponseTeamModel fetchTeamByUserId(Long userId) {
        return null;
    }

    @Override
    public Set<ResponseTeamModel> fetchRanking() {
        return null;
    }

    @Override
    public ResponseUserModel fetchTeamLeader(Long teamId) {
        return null;
    }

    @Override
    public Team getTeamById(Long teamId) {
        return null;
    }

    @Override
    public Team getTeamByLeaderId(Long leaderId) {
        return null;
    }

    @Override
    public void save(Team team) {

    }
}
