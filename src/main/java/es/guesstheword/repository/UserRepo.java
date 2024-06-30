package es.guesstheword.repository;

import es.guesstheword.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByUsernameAndPassword(String u, String p);

    Users findByUsername(String username);
}
