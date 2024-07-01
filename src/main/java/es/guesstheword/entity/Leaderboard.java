package es.guesstheword.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@NoArgsConstructor
@Entity
public class Leaderboard {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "leaderboard_id", nullable = false)
    private int leaderboardId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(name = "secret_word", nullable = false, length = 50)
    private String secretWord;
    @Basic
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Basic
    @Column(name = "number_of_guesses", nullable = false)
    private int numberOfGuesses;
    @Basic
    @Column(name = "number_of_clues", nullable = false)
    private int numberOfClues;
    @Basic
    @Column(name = "time", nullable = false)
    private double time;

    public Leaderboard(int userId, String secretWord, String username, int numberOfGuesses, int numberOfClues, int time) {
        this.userId = userId;
        this.secretWord = secretWord;
        this.username = username;
        this.numberOfGuesses = numberOfGuesses;
        this.numberOfClues = numberOfClues;
        this.time = time;
    }

}
