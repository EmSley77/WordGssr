package es.guesstheword.logic;
/*
Emanuel sleyman
2024-06-29
service class containing all logic for game
*/
import es.guesstheword.repository.ClueRepo;
import es.guesstheword.repository.ExplanationRepo;
import es.guesstheword.repository.LeaderboardRepo;
import es.guesstheword.repository.WordsRepo;
import org.springframework.stereotype.Service;

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



}
