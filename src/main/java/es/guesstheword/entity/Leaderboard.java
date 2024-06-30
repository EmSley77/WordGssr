package es.guesstheword.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Objects;
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
    private int time;

    public Leaderboard(int userId, String secretWord, String username, int numberOfGuesses, int numberOfClues, int time) {
        this.userId = userId;
        this.secretWord = secretWord;
        this.username = username;
        this.numberOfGuesses = numberOfGuesses;
        this.numberOfClues = numberOfClues;
        this.time = time;
    }

    public int getLeaderboardId() {
        return leaderboardId;
    }

    public void setLeaderboardId(int leaderboardId) {
        this.leaderboardId = leaderboardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }

    public void setNumberOfGuesses(int numberOfGuesses) {
        this.numberOfGuesses = numberOfGuesses;
    }

    public int getNumberOfClues() {
        return numberOfClues;
    }

    public void setNumberOfClues(int numberOfClues) {
        this.numberOfClues = numberOfClues;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
