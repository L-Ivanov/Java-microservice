package com.example.demo.service.invitation;

import com.example.demo.constants.Constants;
import com.example.demo.domain.Invitation;
import com.example.demo.domain.Status;
import com.example.demo.domain.Team;
import com.example.demo.domain.User;
import com.example.demo.exceptions.*;
import com.example.demo.model.ResponseInvitationModel;
import com.example.demo.model.ResponseTeamModel;
import com.example.demo.repository.InvitationRepository;
import com.example.demo.service.team.TeamService;
import com.example.demo.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InvitationServiceImpl implements InvitationService {
    private final InvitationRepository invitationRepository;
    private final TeamService teamService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public InvitationServiceImpl(InvitationRepository invitationRepository
            , TeamService teamService, UserService userService, ModelMapper modelMapper) {
        this.invitationRepository = invitationRepository;
        this.teamService = teamService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public Invitation fetchById(Long invitationId) {
        return this.invitationRepository.findById(invitationId).orElseThrow(() ->
                new MissingIdException("There is not such invitation."));
    }

    @Override
    public void clearRejectedInvitation() {
        this.invitationRepository
                .deleteAll(this.invitationRepository.findAllByProcessOfBefore(LocalDateTime.now().minusHours(Constants.LIMIT_ACCESS_TIME)));
    }

    @Override
    public String sendInvitation(Long teamId, Long userId) {
        Team team = this.teamService.getTeamById(teamId);
        User user = this.userService.getUserById(userId);
        Invitation invitation = new Invitation();
        invitation.setUser(user);
        invitation.setTeam(team);
        this.invitationRepository.saveAndFlush(invitation);

        return String.format("our application was successfully sent to %s, leader of the %s which you want to join. \" +\n" +
                "                \"Expect the leader`s decision.\"", team.getTeamCreator().getUsername(), team.getTeamName());
    }

    @Override
    public Set<ResponseInvitationModel> fetchInvitations(Long leaderId) {
        Team team = throwIfYouAreNotTeamCreator(leaderId);
        return this.invitationRepository.findAllByTeam(team).stream()
                .map(t -> this.modelMapper.map(t, ResponseInvitationModel.class)).collect(Collectors.toSet());

    }

    @Override
    public ResponseInvitationModel rejectInvitation(Long invitationId, Long leaderId) {
        throwIfYouAreNotTeamCreator(leaderId);
        Invitation invitation = this.invitationRepository.getOne(invitationId);
        invitation.setStatus(Status.REJECTED);
        invitation.setProcessOf(LocalDateTime.now());
        this.invitationRepository.saveAndFlush(invitation);
        return this.modelMapper.map(this.invitationRepository.saveAndFlush(invitation), ResponseInvitationModel.class);


    }

    @Override
    public ResponseTeamModel joinMember(Long memberId, Long leaderId) {
        Team team = throwIfYouAreNotTeamCreator(leaderId);
        User user = this.userService.getUserById(memberId);
        Invitation invitation = this.invitationRepository.findByUserAndTeam(user, team);
        throwIfMemberHasTeam(user.getId());
        throwIfTeamHasFullCapacity(team);
        throwIfMemberHasNoInvitation(user, team);
        throwIfUserIsRejected(invitation);
        team.getTeamMembers().add(user);
        this.invitationRepository.delete(invitation);
        this.teamService.save(team);
        this.userService.saveTeamToUser(user, team);
        ResponseTeamModel responseTeamModel = this.modelMapper.map(team, ResponseTeamModel.class);
        return responseTeamModel;

    }

    private void throwIfUserIsRejected(Invitation invitation) {
        if (invitation.getStatus().equals(Status.REJECTED)) {
            throw new UserIsRejectedException("You cannot apply for this team because you have been rejected or left recently. After a while, try again.");
        }
    }

    private void throwIfMemberHasNoInvitation(User user, Team team) {
        if (this.invitationRepository.findByUserAndTeam(user, team) == null) {
            throw new IllegalUserException("You cannot join or create a team because you are involved in another.");
        }

    }

    private void throwIfTeamHasFullCapacity(Team team) {
        if (team.getTeamMembers().size() == Constants.MAX_CAPACITY_TEAM) {
            throw new IllegalTeamException("You cannot join on this team because there is no free member space.");
        }
    }

    private void throwIfMemberHasTeam(long id) {
        if (this.userService.isPlayerHasTeam(id)) {
            throw new IllegalUserException("You cannot join or create a team because you are involved in another.");
        }
    }

    @Override
    public String leave(Long memberId, Long leaderId) {
        throwIfMemberHasNotTeam(memberId);
        throwIfMemberHasNotTeam(leaderId);
        User member = this.userService.getUserById(memberId);
        Team teamByMember = this.teamService.getTeamById(member.getTeam().getId());
        if (teamByMember.getTeamCreator().getId() == leaderId || memberId.equals(leaderId)) {
            teamByMember.getTeamMembers().remove(member);
            createCustomRejectedInvitation(member, teamByMember);
            this.teamService.save(teamByMember);

        }
        return String.format("Member %s was successfully removed from team %s."
                , member.getUsername(), teamByMember.getTeamName());
    }

    private void createCustomRejectedInvitation(User member, Team teamByMember) {
        Invitation invitation = new Invitation();
        invitation.setUser(member);
        invitation.setTeam(teamByMember);
        invitation.setProcessOf(LocalDateTime.now());
        invitation.setStatus(Status.REJECTED);
        this.invitationRepository.saveAndFlush(invitation);
    }

    private void throwIfMemberHasNotTeam(Long memberId) {
        if (this.userService.isPlayerHasTeam(memberId)) {
            throw new MemberHasNoTeamException("There is no team with this player.");

        }
    }

    private Team throwIfYouAreNotTeamCreator(Long leaderId) {
        return this.teamService.getTeamByLeaderId(leaderId);

    }
}
