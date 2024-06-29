package es.guesstheword.logic;
/*
Emanuel sleyman
2024-06-29
service class containing all logic for login
*/
import es.guesstheword.repository.UserRepo;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class LoginLogic {

    private UserRepo userRepo;

    @Getter
    private int userId;

    public LoginLogic(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //create account

    //delete account

    //edit account

    //reset sessionId after signing out

}
