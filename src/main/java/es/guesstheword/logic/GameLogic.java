package es.guesstheword.logic;
/*
Emanuel sleyman
2024-06-29
service class containing all logic for game
*/

import es.guesstheword.entity.Explanation;
import es.guesstheword.entity.Leaderboard;
import es.guesstheword.entity.Users;
import es.guesstheword.entity.Words;
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

    private String guess;

    private Words word;

    private Scanner input = new Scanner(System.in);

    private int nGuess = 0;

    public GameLogic(LeaderboardRepo leaderboardRepo, WordsRepo wordsRepo, ExplanationRepo explanationRepo, ClueRepo clueRepo) {
        this.leaderboardRepo = leaderboardRepo;
        this.wordsRepo = wordsRepo;
        this.explanationRepo = explanationRepo;
        this.clueRepo = clueRepo;
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

    //prepare game, word mm
    public void prepareGame() {
        word = getRandomWord();
        System.out.println(getWordExplanation(word));
    }

    //play game
    public void GuessGame() {
        prepareGame();

        do {
            System.out.print("Guess the word: ");
            guess = input.nextLine();
            nGuess++;

        } while (guess.equalsIgnoreCase(String.valueOf(word)));


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
