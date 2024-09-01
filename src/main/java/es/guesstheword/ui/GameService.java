package es.guesstheword.ui;
/*
Emanuel sleyman
2024-07-01
a service class for all game logic
*/
import es.guesstheword.logic.GameLogic;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class GameService {

    private Scanner input = new Scanner(System.in);

    private GameLogic gameLogic;

    public GameService(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void playGame() {
        System.out.println("'END' to exit game");
        System.out.print("Guess the word: ");
        String guess = input.nextLine().trim();
        gameLogic.guessGame(guess);

    }
}
