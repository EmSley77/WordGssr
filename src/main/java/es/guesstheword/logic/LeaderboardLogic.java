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

import java.util.List;


@Component
public class LeaderboardLogic {

    private UserRepo userRepo;
    private LeaderboardRepo leaderboardRepo;
    private UserLogic userLogic;

    public LeaderboardLogic(UserRepo userRepo, LeaderboardRepo leaderboardRepo, UserLogic userLogic) {
        this.userRepo = userRepo;
        this.leaderboardRepo = leaderboardRepo;
        this.userLogic = userLogic;
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

    //get user single statistics
    public void getUserStats() {
        List<Leaderboard> leaderboards = leaderboardRepo.findAll();
        if (leaderboards.isEmpty()) {
            return;
        }

        Users user = userRepo.findUsersByUserId(userLogic.getUserId());
        if (user == null) {

        }

        int wins = 0;
        int losses = 0;
        int gamesPlayed = 0;
        int avgGuess = 0;
        for (Leaderboard leaderboard : leaderboards) {

            if (userLogic.getUserId() == leaderboard.getUserId()) {


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
        System.out.println("XP: " + user.getXp());
        System.out.println("REGISTERED: " + user.getRegistrationDate());
        System.out.println("GAMES WON: " + wins);
        System.out.println("GAMES LOST: " + losses);
        System.out.println("AVERAGE GUESSES PER GAME: " + avgGuess);
        System.out.println("TOTAL GAMES PLAYED: " + gamesPlayed);
        System.out.println("_____________________");


    }
}
