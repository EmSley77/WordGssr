package es.guesstheword.logic;
/*
Emanuel sleyman
2024-06-29
service class containing all logic for login
*/

import es.guesstheword.entity.Users;
import es.guesstheword.repository.UserRepo;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class UserLogic {

    private UserRepo userRepo;

    @Getter
    protected int userId;

    @Getter
    protected String userUsername;

    // TODO: close scanner after done with login methods
    private Scanner input = new Scanner(System.in);

    public UserLogic(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //create account, need a scanner to make this work
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

        if (userRepo.findByUsername(username) != null) {
            System.out.println("USER WITH USERNAME: " + username + "ALREADY EXISTS");
            return;
        }

        Users user = new Users();
        user.setName(name);
        user.setLastname(lastname);
        user.setUsername(username);
        user.setPassword(password);
        user.setXp(0);
        user.setGameLevel(1);
        user.setRole(0);
        user.setRegistrationDate(Date.valueOf(LocalDate.now()));
        if (rPassword.equals(password)) {
            userRepo.save(user);
            System.out.println("ACCOUNT WAS CREATED");
        } else return;

    }

    //view these stats after signing in
    public void userStats() {

        Users user = userRepo.findUsersByUserId(userId);
        if (user == null) {
            return;
        }

        System.out.println("_____________________");
        System.out.println("USERNAME: " + user.getUsername());
        System.out.println("LEVEL: " + user.getGameLevel());
        System.out.println("XP: " + user.getXp());
        System.out.println("REGISTERED: " + user.getRegistrationDate());
        System.out.println("_____________________");
    }


    //delete account
    public void deleteAccount() {
        System.out.print("Write Username: ");
        String username = input.nextLine();

        System.out.print("Write Password: ");
        String password = input.nextLine();

        System.out.print("Repeat Password: ");
        String repeatPassword = input.nextLine();

        Users user = userRepo.findByUsernameAndPassword(username, password);
        if (user == null) {
            System.out.println("could not find user with these credentials, try again");
            return;
        }

        userRepo.deleteById(user.getUserId());
        System.out.println("User with username: " + username + " has been deleted");
        //make sure to direct back to login page after deleting user account
    }

    //edit username
    public void editUsername() {
        System.out.println("2 TO EXIT EDIT PAGE");
        System.out.print("ENTER NEW USERNAME: ");
        String username = "";
        while (username.isEmpty()) {
            username = input.nextLine().trim();
            if (username.equals("2")) {
                System.out.println("EXITING EDIT USERNAME PAGE");
                return;
            }
        }

        Users user = userRepo.findUsersByUserId(userId);
        if (user == null) {
            System.out.println("COULD NOT FIND USER TO EDIT");
            return;
        }

        if (userRepo.findByUsername(username) != null) {
            System.out.println("USERNAME ALREADY IN USE");
            return;
        }

        user.setUsername(username);
        userRepo.save(user);
        System.out.println("SUCCESSFULLY UPDATED USERNAME");

    }

    //edit PASSWORD
    public void editPassword() {
        System.out.println("2 TO EXIT EDIT PAGE");
        System.out.print("ENTER NEW PASSWORD: ");
        String password = "";
        String repeatPassword = "";
        while (password.isEmpty()) {
            password = input.nextLine().trim();
            if (password.equals("2")) {
                System.out.println("EXITING EDIT PASSWORD PAGE");
                return;
            }
        }
        System.out.println();
        System.out.print("REPEAT NEW PASSWORD: ");
        while (repeatPassword.isEmpty()) {
            repeatPassword = input.nextLine().trim();
            if (repeatPassword.equals("2")) {
                System.out.println("EXITING EDIT PASSWORD PAGE");
                return;
            }
        }

        Users user = userRepo.findUsersByUserId(userId);
        if (user == null) {
            System.out.println("COULD NOT FIND USER TO EDIT");
            return;
        }

        if (repeatPassword.equals(password)) {
            user.setPassword(password);
            userRepo.save(user);
            System.out.println("SUCCESSFULLY UPDATED USERNAME");
        } else {
            System.out.println("COULD NOT UPDATE PASSWORD, TRY AGAIN");
        }


    }

    //reset sessionId after signing out
    public void resetSessionId() {
        userId = 0;
    }

    //use when user or admin signs in for diff menus
    public int getUserRole() {
        return userRepo.findUsersByUserId(getUserId()).getRole();
    }
}
