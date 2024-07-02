package es.guesstheword.logic;
/*
Emanuel sleyman
2024-07-02
a class for login with a String output
*/

import es.guesstheword.entity.Users;
import es.guesstheword.repository.UserRepo;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class LoginLogic {

    private Scanner input = new Scanner(System.in);

    private UserRepo userRepo;


    @Getter
    private int userId;

    @Getter
    private String userUsername;

    public LoginLogic(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //LOGIN
    public String login() {
        System.out.print("Username: ");
        String username = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        Users user = userRepo.findByUsernameAndPassword(username, password);
        if (user == null) {
            System.out.println("COULD NOT FIND PERSON WITH THESE CREDENTIALS, TRY AGAIN");
            return "login declined";
        }

        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            userId = user.getUserId();
            userUsername = user.getUsername();
            System.out.println("SUCCESSFULLY LOGGED IN");
            return "success";
        }
        return "wrong credentials";
    }
}
