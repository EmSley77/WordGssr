package es.guesstheword.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "lastname", nullable = false, length = 45)
    private String lastname;
    @Basic
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @Basic
    @Column(name = "xp", nullable = false)
    private int xp;
    @Basic
    @Column(name = "game_level", nullable = false)
    private int gameLevel;
    @Basic
    @Column(name = "role", nullable = false)
    private int role;
    @Basic
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    public Users(String name, String lastname, String username, String password, int xp, int gameLevel, int role, Date registrationDate) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.xp = xp;
        this.gameLevel = gameLevel;
        this.role = role;
        this.registrationDate = registrationDate;
    }
}
