package es.guesstheword.service;
/*
Emanuel sleyman
2024-07-01
a service class for all game logic
*/
import es.guesstheword.logic.GameLogic;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private GameLogic gameLogic;

    public GameService(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void playGame() {
        gameLogic.guessGame();
    }
}
