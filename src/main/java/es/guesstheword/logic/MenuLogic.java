package es.guesstheword.logic;
/*
Emanuel sleyman
2024-07-01
a class for all menu logic
*/

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class MenuLogic {

    private Scanner input = new Scanner(System.in);
    // all service classes here

    //sign in menu, must pass login to get to start menu
    public void LoginMenu() {
        System.out.println("0. END PROGRAM");
        System.out.println("1. SIGN IN");
        System.out.println("2. CREATE ACCOUNT");

    }

    //startMenu after successful login
    public void startMenu() {

    }

}
