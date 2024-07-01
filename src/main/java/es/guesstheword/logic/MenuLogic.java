package es.guesstheword.logic;
/*
Emanuel sleyman
2024-07-01
a class for all menu logic
*/

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuLogic {

    // TODO: close scanner after done with menu
    private Scanner input = new Scanner(System.in);
    private int option;
    // all service classes here

    //sign in menu, must pass login to get to start menu
    public void LoginMenu() {
        do {
            System.out.println("0. END PROGRAM");
            System.out.println("1. SIGN IN");
            System.out.println("2. CREATE ACCOUNT");
            System.out.print("OPTION: ");

            option = input.nextInt();
            switch (option) {
             /*
                case 1 -> ;
                case 2 -> ;
                case 3 -> ;
              */
            }
        } while (option != 0);
    }

    //startMenu after successful login
    public void startMenu() {
        do {
            System.out.println("0. SIGN OUT");
            System.out.println("1. PLAY GAME");
            System.out.println("2. MY STATISTICS");
            System.out.println("3. VIEW LEADERBOARD");
            System.out.println("4. DELETE ACCOUNT");
            System.out.print("OPTION: ");

            option = input.nextInt();
            switch (option) {
              /*
                case 1 -> ;
                case 2 -> ;
                case 3 -> ;
                case 4 -> ;
                case 5 -> ;
               */
            }
        } while (option != 0);
    }

}
