package es.guesstheword.logic;
/*
Emanuel sleyman
2024-07-04
this class is for admin methods
 */

import es.guesstheword.entity.Users;
import es.guesstheword.repository.UserRepo;
import org.apache.catalina.User;
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
    public void createAdminAccount(String name, String lastname, String username, String password, String rPassword) {


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
        }

    }


    public void getAllUsers() {
        List<Users> usersList = userRepo.findAll();

        if (!usersList.isEmpty()) {
            for (Users users : usersList) {
                if (users.getRole() == 0) {
                    System.out.println(users);
                }
            }
        } else {
            System.out.println("0 USERS FOUND");
        }
    }

    //get all admins
    public void getAdmins() {
        List<Users> usersList = userRepo.findAll();

        if (!usersList.isEmpty()) {
            for (Users users : usersList) {
                if (users.getRole() == 1) {
                    System.out.println(users);
                }
            }
        } else {
            System.out.println("0 USERS FOUND");
        }
    }

    public void getBySearch(String search) {

        while (search.isEmpty()) {
            search = input.nextLine().trim();
            if (search.equals("2")) {
                System.out.println("EXITED OUT OF SEARCH PAGE");
                return;
            }
        }
        List<Users> usersList = userRepo.findUsersByUsernameContainingOrNameContaining(search, search);
        if (!usersList.isEmpty()) {
            for (Users user : usersList) {
                System.out.println(user);
            }
        } else {
            System.out.println("0 USERS FOUND");
        }

    }

    //delete user
    @Transactional
    public void deleteUser(String search) {
        try {

            while (search.isEmpty()) {
                search = input.nextLine().trim();
                if (search.equalsIgnoreCase("EXIT")) {
                    System.out.println("EXITED OUT OF DELETE PAGE");
                    return;
                }
            }

            userRepo.deleteByUsernameOrUserId(search, Integer.parseInt(search));
            System.out.println("SUCCESSFULLY DELETED USER WITH ID OR USERNAME: " + search);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //view these stats after signing in
    public void userStats(String id) {


        Users user = userRepo.findUsersByUserId(Integer.parseInt(id));

        if (user == null) {
            System.out.println("COULD NOT FIND ANY USER WITH ID: " + id);
            return;
        }

        System.out.println("_____________________");
        System.out.println("NAME: " + user.getName());
        System.out.println("LASTNAME: " + user.getLastname());
        if (user.getRole() == 1) {
            System.out.println("ROLE: 'ADMIN'");

        } else if (user.getRole() == 0) {
            System.out.println("ROLE: 'USER'");
        }
        System.out.println("USERNAME: " + user.getUsername());
        System.out.println("LEVEL: " + user.getGameLevel());
        System.out.println("XP: " + user.getXp());
        System.out.println("REGISTERED: " + user.getRegistrationDate());
        System.out.println("_____________________");
    }


    // edit account

    //make a user an admin
    public void makeUserAdmin(String s) {


        Users user = userRepo.findUsersByUserId(Integer.parseInt(s));
        if (user.getRole() != 0) {
            System.out.println("USER MUST BE A USER WITH ROLE 0");
            return;
        }

        user.setRole(1);
        userRepo.save(user);
        System.out.println("CHANGED USER ROLE TO ADMIN ON USERNAME: " + user.getUsername());
    }

    // view game stats, how many players, total games played, globally stats...
}
