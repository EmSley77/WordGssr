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

    /*public void login() {
        userLogic.login();
    }*/

    public void createAccount() {
        userLogic.createAccount();
    }

    public void deleteAccount() {
        userLogic.deleteAccount();
    }

    //after signing out to reset 'sessionId'
    public void resetSession() {
        userLogic.resetSessionId();
    }
}
