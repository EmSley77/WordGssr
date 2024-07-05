package es.guesstheword.logic;
/*
Emanuel sleyman
2024-07-04
this class is for admin methods
 */

import es.guesstheword.entity.Users;
import es.guesstheword.repository.UserRepo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class AdminLogic {

    private UserRepo userRepo;

    private Scanner input = new Scanner(System.in);

    public AdminLogic(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //create account, need a scanner to make this work
    public void createAdminAccount() {

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


    // TODO: create word, delete word, view all words, search for words

    //TODO: search and get users
    public void getAllUsers() {
        List<Users> usersList =  userRepo.findAll();

        if (!usersList.isEmpty()) {
            for (Users users : usersList) {
                System.out.println(users);
            }
        } else {
            System.out.println("0 USERS FOUND");
            return;
        }
    }

    public void getBySearch() {
        System.out.println("2. EXIT SEARCH");
        System.out.print("SEARCH: ");
        String search = "";
        while (search.isEmpty()) {
            search = input.nextLine().trim();
            if (search.equals("2")) {
                System.out.println("EXITED OUT OF SEARCH PAGE");
                return;
            }
        }
        List<Users> usersList = userRepo.findUsersByUsernameContainingOrNameContaining(search, search);
        if (!usersList.isEmpty()) {
            for (Users user: usersList) {
                System.out.println(user);
            }
        } else {
            System.out.println("0 USERS FOUND");
            return;
        }

    }

    //delete user
    @Transactional
    public void deleteUser() {
        System.out.println("2. EXIT DELETE PAGE");
        System.out.print("ENTER ID OR USERNAME: ");
        String search = "";
        while (search.isEmpty()) {
            search = input.nextLine().trim();
            if (search.equals("2")) {
                System.out.println("EXITED OUT OF DELETE PAGE");
                return;
            }
        }

        userRepo.deleteByUsernameOrUserId(search, Integer.parseInt(search));
        System.out.println("SUCCESSFULLY DELETED USER WITH ID OR USERNAME: " + search);
    }

}
