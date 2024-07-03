package es.guesstheword.logic;
/*
Emanuel sleyman
2024-07-02
a logic class for leaderboard and player stats
*/

import es.guesstheword.entity.Leaderboard;
import es.guesstheword.entity.Users;
import es.guesstheword.repository.LeaderboardRepo;
import es.guesstheword.repository.UserRepo;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
public class LeaderboardLogic {

    private UserRepo userRepo;
    private LeaderboardRepo leaderboardRepo;

    public LeaderboardLogic(UserRepo userRepo, LeaderboardRepo leaderboardRepo) {
        this.userRepo = userRepo;
        this.leaderboardRepo = leaderboardRepo;
    }


    public void getLeaderboard() {
        List<Leaderboard> leaderboards = leaderboardRepo.findAll();
        if (leaderboards.isEmpty()) {
            return;
        }

        List<Users> usersList = userRepo.findAll();
        if (usersList.isEmpty()) {
            return;
        }

        System.out.println("----------LEADERBOARD----------");
        for (Users user : usersList) {
            int wins = 0;
            int losses = 0;
            int gamesPlayed = 0;
            int avgGuess = 0;
            for (Leaderboard leaderboard : leaderboards) {


                if (user.getUserId() == leaderboard.getUserId()) {

                    if (leaderboard.getWinLoss().equals("WON")) {
                        wins += 1;
                    }
                    if (leaderboard.getWinLoss().equals("LOST")) {
                        losses += 1;
                    }


                    gamesPlayed++;


                }
                avgGuess += leaderboard.getNumberOfGuesses() / gamesPlayed;


            }
            System.out.println("______________________________________");
            System.out.println("USERNAME: " + user.getUsername());
            System.out.println("LEVEL: " + user.getGameLevel());
            System.out.println("WINS: " + wins);
            System.out.println("GAMES LOST: " + losses);
            System.out.println("AVERAGE GUESS PER GAME: " + avgGuess);
            System.out.println("TOTAL GAMES PLAYED: " + gamesPlayed);


        }
    }
}
