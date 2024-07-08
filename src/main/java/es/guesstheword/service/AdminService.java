package es.guesstheword.service;
/*
Emanuel sleyman
2024-07-05
this class is for admin methods and a service class
 */

import es.guesstheword.logic.AdminLogic;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private AdminLogic adminLogic;

    public AdminService(AdminLogic adminLogic) {
        this.adminLogic = adminLogic;
    }

    public void getUsers() {
        adminLogic.getAllUsers();
    }

    public void getUsersBySearch() {
        adminLogic.getBySearch();
    }

    public void createAdmin() {
        adminLogic.createAdminAccount();
    }

    public void getAdmins() {
        adminLogic.getAdmins();
    }

    //level, xp and username
    public void getUserInfo() {
        adminLogic.userStats();
    }

    public void makeUserAdmin() {
        adminLogic.makeUserAdmin();
    }

    //delete user, test first if working
    public void deleteUser() {
        adminLogic.deleteUser();
    }
}
