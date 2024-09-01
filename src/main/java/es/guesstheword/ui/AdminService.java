package es.guesstheword.ui;
/*
Emanuel sleyman
2024-07-05
this class is for admin methods and a service class
 */

import es.guesstheword.logic.AdminLogic;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class AdminService {

    private AdminLogic adminLogic;

    private Scanner input = new Scanner(System.in);

    public AdminService(AdminLogic adminLogic) {
        this.adminLogic = adminLogic;
    }

    public void getUsers() {
        adminLogic.getAllUsers();
    }

    public void getUsersBySearch() {
        System.out.println("2. EXIT SEARCH");
        System.out.print("SEARCH: ");
        String search = input.nextLine().trim();
        adminLogic.getBySearch(search);
    }

    public void createAdmin() {
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

        adminLogic.createAdminAccount(name,lastname,username,password,rPassword);
    }

    public void getAdmins() {
        adminLogic.getAdmins();
    }

    //level, xp and username
    public void getUserInfo() {
        System.out.print("ENTER USER ID: ");
        String id = input.nextLine().trim();
        adminLogic.userStats(id);
    }

    public void makeUserAdmin() {
        System.out.println("ENTER 2 TO EXIT");
        System.out.println("ENTER USER ID TO MAKE USER ADMIN: ");
        System.out.print("ENTER: ");
        String s = input.nextLine().trim();
        adminLogic.makeUserAdmin(s);
    }

    //delete user, test first if working
    public void deleteUser() {
        System.out.println("'EXIT' TO EXIT DELETE PAGE");
        System.out.print("ENTER ID OR USERNAME TO DELETE USER: ");
        String search = input.nextLine().trim();
        adminLogic.deleteUser(search);
    }
}
