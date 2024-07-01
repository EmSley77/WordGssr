package es.guesstheword.logic;
/*
Emanuel sleyman
2024-06-29
service class containing all logic for login
*/

import es.guesstheword.entity.Users;
import es.guesstheword.repository.UserRepo;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

@Service
public class LoginLogic {

    private UserRepo userRepo;

    @Getter
    private int userId;

    @Getter
    private String userUsername;

    private Scanner input = new Scanner(System.in);

    public LoginLogic(UserRepo userRepo) {
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

    //LOGIN
    public void login() {
        System.out.print("Username: ");
        String username = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        Users user = userRepo.findByUsernameAndPassword(username, password);
        if (user == null) {
            System.out.println("COULD NOT FIND PERSON WITH THESE CREDENTIALS, TRY AGAIN");
            return;
        }

        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            userId = user.getUserId();
            userUsername = user.getUsername();
            System.out.println("SUCCESSFULLY LOGGED IN");
        }
    }

    //delete account

    //edit account

    //reset sessionId after signing out

}
