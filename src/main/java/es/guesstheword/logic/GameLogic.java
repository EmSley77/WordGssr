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

@Service
public class GameLogic {

    private LeaderboardRepo leaderboardRepo;

    private WordsRepo wordsRepo;

    private ExplanationRepo explanationRepo;

    private ClueRepo clueRepo;

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

    //play game

    //save results in leaderboard
    public void saveResults(int userId, String username, Words word, int nGuess, int nClues, int time) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setUserId(userId);
        leaderboard.setUsername(username);
        leaderboard.setSecretWord(String.valueOf(word));
        leaderboard.setNumberOfGuesses(nGuess);
        leaderboard.setNumberOfClues(nClues);

        leaderboard.setTime(time);

    }


}
