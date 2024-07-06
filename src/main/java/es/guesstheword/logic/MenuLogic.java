package es.guesstheword.logic;
/*
Emanuel sleyman
2024-07-01
a class for all menu logic
*/

import es.guesstheword.service.AdminService;
import es.guesstheword.service.GameService;
import es.guesstheword.service.LeaderboardService;
import es.guesstheword.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuLogic {

    private GameService gameService;

    private UserService userService;

    private LoginLogic loginLogic;

    private LeaderboardService leaderboardService;

    private AdminService adminService;

    private Scanner input = new Scanner(System.in);

    public MenuLogic(GameService gameService, UserService userService, LoginLogic loginLogic, LeaderboardService leaderboardService, AdminService adminService) {
        this.gameService = gameService;
        this.userService = userService;
        this.loginLogic = loginLogic;
        this.leaderboardService = leaderboardService;
        this.adminService = adminService;
    }

    // TODO: close scanner after done with menu

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

                    String login = loginLogic.login();
                    if (login.equals("success") && userService.getUserRole() == 0) {
                        userMenu();
                    } else if (login.equals("success") && userService.getUserRole() == 1) {
                        adminMenu();
                    }
                    loginMenu();
                }
                case "2" -> userService.createAccount();
                default -> System.out.println("INPUT A VALID NUMBER");

            }
        } while (true);
    }

    //startMenu after successful login
    public void userMenu() {
        do {
            System.out.println("0. SIGN OUT");
            System.out.println("1. PLAY GAME");
            System.out.println("2. MY STATISTICS");
            System.out.println("3. VIEW LEADERBOARD");
            System.out.println("4. EDIT ACCOUNT");
            System.out.println("4. DELETE ACCOUNT");
            System.out.print("OPTION: ");

            option = input.nextLine().trim();
            switch (option) {

                case "0" -> {
                    userService.resetSession();
                    loginMenu();
                }
                case "1" -> gameService.playGame();
                case "2" -> leaderboardService.getUserStatistics();
                case "3" -> leaderboardService.getLeaderboard();
                case "4" -> {
                    System.out.println("0. RETURN TO MAIN MENU");
                    System.out.println("1. EDIT USERNAME");
                    System.out.println("2. EDIT PASSWORD");
                    System.out.print("OPTION: ");

                    String in = input.nextLine().trim();
                    switch (in) {
                        case "1" -> userService.editUsername();
                        case "2" -> userService.editPassword();
                        case "0" -> {
                            userMenu();
                        }
                    }

                }
                case "5" -> {
                    userService.deleteAccount();
                    userService.resetSession();
                    loginMenu();
                }

                default -> System.out.println("INPUT A VALID NUMBER");
            }
        } while (!option.equals("0"));
    }


    //ADMIN MENU
    public void adminMenu() {
        do {
            System.out.println("0. SIGN OUT");
            System.out.println("1. CREATE ADMIN");
            System.out.println("2. ALL USERS");
            System.out.println("3. SEARCH FOR USERS");
            System.out.println("4. VIEW USER INFO");
            System.out.println("5. DELETE USER ACCOUNT");
            System.out.print("OPTION: ");

            option = input.nextLine().trim();
            switch (option) {

                case "0" -> {
                    userService.resetSession();
                    loginMenu();
                }
                case "1" -> adminService.createAdmin();
                case "2" -> adminService.getUsers();
                case "3" -> adminService.getUsersBySearch();
                case "4" -> adminService.getUserInfo();
                case "5" -> adminService.deleteUser();
                default -> System.out.println("INPUT A VALID NUMBER");
            }
        } while (!option.equals("0"));
    }
}
