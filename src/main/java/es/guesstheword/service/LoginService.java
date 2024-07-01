package es.guesstheword.service;
/*
Emanuel sleyman
2024-07-01
a service class for all login logic and account managing
*/

import es.guesstheword.logic.LoginLogic;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private LoginLogic loginLogic;

    public LoginService(LoginLogic loginLogic) {
        this.loginLogic = loginLogic;
    }

    public void login() {
        loginLogic.login();
    }

    public void createAccount() {
        loginLogic.createAccount();
    }

    public void deleteAccount() {
        loginLogic.deleteAccount();
    }

    //after signing out to reset 'sessionId'
    public void resetSession() {
        loginLogic.resetSessionId();
    }
}
