package com.example.demo.service.invitation;

import com.example.demo.domain.Invitation;
import com.example.demo.model.ResponseInvitationModel;
import com.example.demo.model.ResponseTeamModel;

import java.util.Set;

public interface InvitationService {

    Invitation fetchById(Long invitationId);

    void clearRejectedInvitation();

    String sendInvitation(Long teamId, Long userId);

    Set<ResponseInvitationModel> fetchInvitations(Long leaderId);

    ResponseInvitationModel rejectInvitation(Long invitationId, Long leaderId);

    public ResponseTeamModel joinMember(Long memberId, Long leaderId);

    String leave(Long memberId, Long leaderId);


}
