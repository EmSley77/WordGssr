package es.guesstheword;

import es.guesstheword.service.GameService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    GameService gameService;

    public Runner(GameService gameService) {
        this.gameService  = gameService;
    }

    public void run(String... args) throws Exception {
        gameService.playGame();

    }
}
