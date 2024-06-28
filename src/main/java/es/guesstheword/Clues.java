package es.guesstheword;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Clues {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "clue_id", nullable = false)
    private int clueId;
    @Basic
    @Column(name = "word_id", nullable = false)
    private int wordId;
    @Basic
    @Column(name = "clue", nullable = false, length = 200)
    private String clue;

    public int getClueId() {
        return clueId;
    }

    public void setClueId(int clueId) {
        this.clueId = clueId;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clues clues = (Clues) o;
        return clueId == clues.clueId && wordId == clues.wordId && Objects.equals(clue, clues.clue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clueId, wordId, clue);
    }
}
