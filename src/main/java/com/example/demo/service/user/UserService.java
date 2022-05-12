package com.example.demo.service.user;

import com.example.demo.domain.Team;
import com.example.demo.domain.User;
import com.example.demo.model.messageBrokerModel.UserRankModel;

public interface UserService {

    boolean isPlayerHasTeam(long id);

    User getUserById(long id);

    void saveTeamToUser(User user, Team team);

    User getUserByName(String username);

    void updateRank(UserRankModel m);

}
