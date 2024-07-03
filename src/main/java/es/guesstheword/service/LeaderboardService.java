package es.guesstheword.service;
/*
Emanuel sleyman
2024-07-02
a service class for leaderboard and player stats
*/

import es.guesstheword.logic.LeaderboardLogic;
import org.springframework.stereotype.Service;

@Service
public class LeaderboardService {

    private LeaderboardLogic leaderboardLogic;

    public LeaderboardService(LeaderboardLogic leaderboardLogic) {
        this.leaderboardLogic = leaderboardLogic;
    }

    public void getLeaderboard() {
        leaderboardLogic.getLeaderboard();
    }
}
