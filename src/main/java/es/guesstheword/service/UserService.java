package es.guesstheword.service;
/*
Emanuel sleyman
2024-07-01
a service class for all login logic and account managing
*/

import es.guesstheword.logic.UserLogic;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserLogic userLogic;

    public UserService(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    //level, xp and username
    public void getUserInfo() {
        userLogic.userStats();
    }

    public void createAccount() {
        userLogic.createAccount();
    }

    public void deleteAccount() {
        userLogic.deleteAccount();
    }

    public void editUsername() {
        userLogic.editUsername();
    }

    public void editPassword() {
        userLogic.editPassword();
    }
    //after signing out to reset 'sessionId'
    public void resetSession() {
        userLogic.resetSessionId();
    }

    public int getUserRole() {
        return userLogic.getUserRole();
    }
}
