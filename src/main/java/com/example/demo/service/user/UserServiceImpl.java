package com.example.demo.service.user;

import com.example.demo.domain.Team;
import com.example.demo.domain.User;
import com.example.demo.model.messageBrokerModel.UserRankModel;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public boolean isPlayerHasTeam(long id) {
        return false;
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public void saveTeamToUser(User user, Team team) {

    }

    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public void updateRank(UserRankModel m) {

    }
}
