package es.guesstheword.logic;
/*
Emanuel sleyman
2024-07-01
a class for all menu logic
*/

import es.guesstheword.service.GameService;
import es.guesstheword.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuLogic {

    private GameService gameService;

    private UserService userService;

    private LoginLogic loginLogic;

    public MenuLogic(GameService gameService, UserService userService, LoginLogic loginLogic) {
        this.gameService = gameService;
        this.userService = userService;
        this.loginLogic = loginLogic;
    }

    // TODO: close scanner after done with menu

    private Scanner input = new Scanner(System.in);

    private String option;
    // all service classes here

    //sign in menu, must pass login to get to start menu
    public void loginMenu() {
        do {
            System.out.println("0. END PROGRAM");
            System.out.println("1. SIGN IN");
            System.out.println("2. CREATE ACCOUNT");
            System.out.print("OPTION: ");

            option = input.nextLine().trim();
            switch (option) {
                case "0" -> System.exit(0);

                case "1" -> {

                    String login =  loginLogic.login();
                    if (login.equals("success")) {
                        startMenu();
                    }
                    loginMenu();
                }
                case "2" -> userService.createAccount();
                default -> System.out.println("INPUT A VALID NUMBER");

            }
        } while (true);
    }

    //startMenu after successful login
    public void startMenu() {
        do {
            userService.getUserInfo();
            System.out.println("0. SIGN OUT");
            System.out.println("1. PLAY GAME");
            System.out.println("2. MY STATISTICS");
            System.out.println("3. VIEW LEADERBOARD");
            System.out.println("4. DELETE ACCOUNT");
            System.out.print("OPTION: ");

            option = input.nextLine().trim();
            switch (option) {

                case "0" -> {
                    userService.resetSession();
                    loginMenu();
                }
                case "1" -> gameService.playGame();
//                case 2 -> ;
//                case 3 -> ;
//                case 4 -> ;
                case "5" -> {
                    userService.deleteAccount();
                    userService.resetSession();
                    loginMenu();
                }

                default -> System.out.println("INPUT A VALID NUMBER");
            }
        } while (!option.equals("0"));
    }

}
