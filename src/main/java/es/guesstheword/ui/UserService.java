package es.guesstheword.ui;
/*
Emanuel sleyman
2024-07-01
a service class for all login logic and account managing
*/

import es.guesstheword.logic.UserLogic;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserService {

    private UserLogic userLogic;

    private Scanner input = new Scanner(System.in);

    public UserService(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    public void createAccount() {
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Lastname: ");
        String lastname = input.nextLine();
        System.out.print("Username: ");
        String username = input.nextLine();
        System.out.print("Password: ");
        String password = input.nextLine();
        System.out.print("Rewrite Password: ");
        String rPassword = input.nextLine();
        userLogic.createAccount(name, lastname, username ,password,rPassword);
    }

    public void deleteAccount() {
        System.out.println("ENTER '2' to exit page");

        System.out.print("Write Username: ");
        String username = input.nextLine();

        System.out.print("Write Password: ");
        String password = input.nextLine();

        System.out.print("Repeat Password: ");
        String repeatPassword = input.nextLine();
        userLogic.deleteAccount(username,password,repeatPassword);
    }

    public void editUsername() {
        System.out.println("2 TO EXIT EDIT PAGE");
        System.out.print("ENTER NEW USERNAME: ");
        String username = input.nextLine().trim();
        userLogic.editUsername(username);
    }

    public void editPassword() {
        System.out.println("2 TO EXIT EDIT PAGE");
        System.out.print("ENTER NEW PASSWORD: ");
        String password = input.nextLine().trim();
        String repeatPassword = input.nextLine().trim();
        userLogic.editPassword(password,repeatPassword);
    }
    //after signing out to reset 'sessionId'
    public void resetSession() {
        userLogic.resetSessionId();
    }

    public int getUserRole() {
        return userLogic.getUserRole();
    }
}
