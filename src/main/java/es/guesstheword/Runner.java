package es.guesstheword;

import es.guesstheword.logic.GameLogic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    GameLogic gameLogic;

    public Runner(GameLogic gameLogic) {
        this.gameLogic  = gameLogic;
    }

    public void run(String... args) throws Exception {
        gameLogic.GuessGame();

    }
}
