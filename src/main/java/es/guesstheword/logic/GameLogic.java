package es.guesstheword.logic;
/*
Emanuel sleyman
2024-06-29
service class containing all logic for game
*/

import es.guesstheword.entity.*;
import es.guesstheword.repository.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class GameLogic {

    private LeaderboardRepo leaderboardRepo;

    private WordsRepo wordsRepo;

    private ExplanationRepo explanationRepo;

    private ClueRepo clueRepo;

    private LoginLogic loginLogic;

    private String guess;

    private Words word;

    // TODO: close scanner after game ends
    private Scanner input = new Scanner(System.in);

    private int nGuess = 0;

    private int startTimer;

    private int endTimer;

    private int totalTime;

    private double elapsedTime;

    private int nClues = 0;

    private UserRepo userRepo;

    public GameLogic(LeaderboardRepo leaderboardRepo, WordsRepo wordsRepo, ExplanationRepo explanationRepo, ClueRepo clueRepo, LoginLogic loginLogic, UserRepo userRepo) {
        this.leaderboardRepo = leaderboardRepo;
        this.wordsRepo = wordsRepo;
        this.explanationRepo = explanationRepo;
        this.clueRepo = clueRepo;
        this.loginLogic = loginLogic;
        this.userRepo = userRepo;
    }


    //get word random
    public Words getRandomWord() {
        Random random = new Random();
        List<Words> allWordsList = wordsRepo.findAll();
        return allWordsList.get(random.nextInt(allWordsList.size()));
    }

    //get word explanation
    public String getWordExplanation(Words word) {
        return explanationRepo.findExplanationByWordId(word.getWordId()).getWordExplanation();
    }

    //get random clues
    public String getWordClue(Words word) {
        Random random = new Random();
        List<Clues> clues = clueRepo.findCluesByWordId(word.getWordId());
        return clues.get(random.nextInt(clues.size())).getClue();
    }

    //prepare game, word mm
    public void prepareGame() {
        word = getRandomWord();
        System.out.println(getWordExplanation(word));
    }

    // print out results  in the end after game ahs been finished
    public void getResults() {
        System.out.println("GUESSES: " + nGuess);
        System.out.println("CLUES: " + nClues);
        System.out.println("THE WORD WAS: " + word.getWord());
        System.out.println("TOTAL SECONDS: " + elapsedTime);
    }

    public void victoryGame() {
        Users user = userRepo.findByUsername(loginLogic.getUserUsername());
        if (user == null) {
            System.out.println("could not find user");
            return;
        }

        user.setXp(user.getXp() + 10);
        if (user.getXp() >= 100) {
            user.setGameLevel(user.getGameLevel() + 1);
        }
        userRepo.save(user);
    }

    public void lostGame() {
        Users user = userRepo.findByUsername(loginLogic.getUserUsername());
        if (user == null) {
            System.out.println("could not find user");
            return;
        }

        user.setXp(user.getXp() - 10);
        userRepo.save(user);
    }

    //play game
    public void guessGame() {

        prepareGame();

        do {
            startTimer = (int) System.currentTimeMillis();
            System.out.println("'END' to exit game");
            System.out.print("Guess the word: ");
            guess = input.nextLine().trim();
            nGuess++;

            if (nGuess == 3 || nGuess == 6 || nGuess == 9) {
                System.out.println(getWordClue(word));
                nClues++;
            }

            if (guess.equalsIgnoreCase("END")) {
                System.out.println("YOU LOST");
                //remove 10 xp from user and log the user logs
                lostGame();
                return;

            }

            //continue game as while its not equal
        } while (!guess.equalsIgnoreCase(String.valueOf(word.getWord())));
        System.out.println("YOU WON");


        endTimer = (int) System.currentTimeMillis();
        totalTime = endTimer - startTimer;
        elapsedTime = totalTime / 1000.0;

        //update player stats
        victoryGame();

        //get game results here
        getResults();

        // TODO:save results here?
        //saveResults(word, nGuess, nClues, endTimer);

        input.close();

    }

    //save results in leaderboard
    public void saveResults(Words word, int nGuess, int nClues, double time) {
        try {
            Leaderboard leaderboard = new Leaderboard();
            leaderboard.setUserId(loginLogic.getUserId());
            leaderboard.setUsername(loginLogic.getUserUsername());
            leaderboard.setSecretWord(String.valueOf(word.getWord()));
            leaderboard.setNumberOfGuesses(nGuess);
            leaderboard.setNumberOfClues(nClues);
            leaderboard.setTotalTime(time);

            leaderboardRepo.save(leaderboard);
            System.out.println("Game was Successfully saved");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
