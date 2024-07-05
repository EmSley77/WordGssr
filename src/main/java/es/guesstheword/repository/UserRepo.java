package es.guesstheword.repository;

import es.guesstheword.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByUsernameAndPassword(String u, String p);
    Users findUsersByUserId(int userId);
    Users findByUsername(String username);
    List<Users> findUsersByUsernameContainingOrNameContaining(String s1, String s2);

    @Transactional
    void deleteByUsernameOrUserId(String s, int id);
}
