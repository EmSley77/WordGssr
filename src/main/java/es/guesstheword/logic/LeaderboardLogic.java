package es.guesstheword.logic;
/*
Emanuel sleyman
2024-07-02
a logic class for leaderboard and player stats
*/

import es.guesstheword.entity.Users;
import es.guesstheword.repository.UserRepo;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
public class LeaderboardLogic {

    private UserRepo userRepo;

    public LeaderboardLogic(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public void getLeaderboard() {
        List<Users> usersList = userRepo.findAll();
        if (usersList.isEmpty()) {
            return;
        }

        for (Users user : usersList) {
            int level;
            int xp;
            String username;

        }
    }
}
