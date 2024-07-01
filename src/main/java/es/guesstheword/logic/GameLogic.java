package es.guesstheword.logic;
/*
Emanuel sleyman
2024-06-29
service class containing all logic for game
*/

import es.guesstheword.entity.*;
import es.guesstheword.repository.ClueRepo;
import es.guesstheword.repository.ExplanationRepo;
import es.guesstheword.repository.LeaderboardRepo;
import es.guesstheword.repository.WordsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
public class GameLogic {

    private LeaderboardRepo leaderboardRepo;

    private WordsRepo wordsRepo;

    private ExplanationRepo explanationRepo;

    private ClueRepo clueRepo;

    private LoginLogic loginLogic;

    private String guess;

    private Words word;

    private Scanner input = new Scanner(System.in);

    private int nGuess = 0;

    private int startTimer;

    private int endTimer;

    private int totalTime;

    private int nClues = 0;

    public GameLogic(LeaderboardRepo leaderboardRepo, WordsRepo wordsRepo, ExplanationRepo explanationRepo, ClueRepo clueRepo, LoginLogic loginLogic) {
        this.leaderboardRepo = leaderboardRepo;
        this.wordsRepo = wordsRepo;
        this.explanationRepo = explanationRepo;
        this.clueRepo = clueRepo;
        this.loginLogic = loginLogic;
    }


    //get word random
    public Words getRandomWord() {
        Random random = new Random();
        List<Words> allWordsList = wordsRepo.findAll();
        return allWordsList.get(random.nextInt(allWordsList.size()));
    }

    //get word explanation
    public Explanation getWordExplanation(Words word) {
        return explanationRepo.findExplanationByWordId(word.getWordId());
    }

    //get random clues
    public Clues getWordClue(Words word) {
        Random random = new Random();
        List<Clues> clues = clueRepo.findCluesByWord(String.valueOf(word));
        return clues.get(random.nextInt(clues.size()));
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
        System.out.println("THE WORD WAS: " + word);
        System.out.println("TIME: " + totalTime);
    }

    //play game
    public void GuessGame() {
        prepareGame();

        do {
            startTimer = (int) System.currentTimeMillis();
            System.out.print("Guess the word: ");
            guess = input.nextLine();
            nGuess++;

            if (nGuess == 3 || nGuess == 6 || nGuess == 9) {
                System.out.println(getWordClue(word));
                nClues++;
            }


            //continue game as while its not equal
        } while (!guess.equalsIgnoreCase(String.valueOf(word)));

        endTimer = (int) System.currentTimeMillis();
        totalTime = startTimer - endTimer;
        //get game results here
        getResults();

        //save results here?
        saveResults(loginLogic.getUserId(), loginLogic.getUserUsername(), word, nGuess, nClues, endTimer);


    }

    //save results in leaderboard
    public void saveResults(int userId, String username, Words word, int nGuess, int nClues, int time) {
        try {
            Leaderboard leaderboard = new Leaderboard();
            leaderboard.setUserId(userId);
            leaderboard.setUsername(username);
            leaderboard.setSecretWord(String.valueOf(word));
            leaderboard.setNumberOfGuesses(nGuess);
            leaderboard.setNumberOfClues(nClues);
            leaderboard.setTime(time);

            leaderboardRepo.save(leaderboard);
            System.out.println("Game was Successfully saved");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
