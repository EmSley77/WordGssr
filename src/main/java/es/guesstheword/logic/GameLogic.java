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
    private UserRepo userRepo;

    Leaderboard leaderboard = new Leaderboard();
    private Random random = new Random();
    private UserLogic userLogic;

    private Words word;
    private int nGuess = 0;
    private int startTimer;
    private int endTimer;
    private int totalTime;
    private double elapsedTime;
    private int nClues = 0;


    public GameLogic(LeaderboardRepo leaderboardRepo, WordsRepo wordsRepo, ExplanationRepo explanationRepo, ClueRepo clueRepo, UserLogic userLogic, UserRepo userRepo) {
        this.leaderboardRepo = leaderboardRepo;
        this.wordsRepo = wordsRepo;
        this.explanationRepo = explanationRepo;
        this.clueRepo = clueRepo;
        this.userLogic = userLogic;
        this.userRepo = userRepo;
    }


    //get word random
    public Words getRandomWord() {
        List<Words> allWordsList = wordsRepo.findAll();
        return allWordsList.get(random.nextInt(allWordsList.size()));
    }

    //get word explanation
    public String getWordExplanation(Words word) {
        return explanationRepo.findExplanationByWordId(word.getWordId()).getWordExplanation();
    }

    //get random clues
    public String getWordClue(Words word) {
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
        Users user = userRepo.findByUsername(userLogic.getUserUsername());
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

    //save won results in leaderboard
    public void saveWonResults(Words word, int nGuess, int nClues, double time) {
        try {
            leaderboard.setUserId(userLogic.getUserId());
            leaderboard.setUsername(userLogic.getUserUsername());
            leaderboard.setSecretWord(String.valueOf(word.getWord()));
            leaderboard.setNumberOfGuesses(nGuess);
            leaderboard.setNumberOfClues(nClues);
            leaderboard.setTotalTime(time);
            leaderboard.setWinLoss("WON");
            leaderboardRepo.save(leaderboard);
            System.out.println("Game was Successfully saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //save lost results in leaderboard
    public void saveLostResults(Words word, int nGuess, int nClues, double time) {
        try {
            leaderboard.setUserId(userLogic.getUserId());
            leaderboard.setUsername(userLogic.getUserUsername());
            leaderboard.setSecretWord(String.valueOf(word.getWord()));
            leaderboard.setNumberOfGuesses(nGuess);
            leaderboard.setNumberOfClues(nClues);
            leaderboard.setTotalTime(time);
            leaderboard.setWinLoss("LOST");
            leaderboardRepo.save(leaderboard);
            System.out.println("Game was Successfully saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void lostGame() {
        Users user = userRepo.findByUsername(userLogic.getUserUsername());
        if (user == null) {
            System.out.println("could not find user");
            return;
        }

        if (user.getXp() == 0) {
            return;
        }

        user.setXp(user.getXp() - 10);
        userRepo.save(user);
    }

    //play game
    public void guessGame(String guess) {

        prepareGame();
        startTimer = (int) System.currentTimeMillis();

        do {

            nGuess++;

            if (nGuess == 3 || nGuess == 6 || nGuess == 9) {
                System.out.println(getWordClue(word));
                nClues++;
            }

            if (guess.equalsIgnoreCase("END")) {
                System.out.println("YOU LOST, THE WORD WAS: " + word.getWord());
                //remove 10 xp from user and log the user logs
                lostGame();
                saveLostResults(word, nGuess, nClues, elapsedTime);
                return;

            }

            //continue game as while its not equal
        } while (!guess.equalsIgnoreCase(String.valueOf(word.getWord())));
        System.out.println("YOU GUESSED CORRECT, THE WORD WAS: " + word.getWord());


        endTimer = (int) System.currentTimeMillis();
        totalTime = endTimer - startTimer;
        elapsedTime = totalTime / 1000.0;

        //update player stats
        victoryGame();

        //get game results here
        getResults();

        //save into leaderboard entity, maybe remove time from database
        saveWonResults(word, nGuess, nClues, elapsedTime);
    }


}
