package es.guesstheword.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Explanation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "explaination_id", nullable = false)
    private int explainationId;
    @Basic
    @Column(name = "word_id", nullable = false)
    private int wordId;

    public int getExplainationId() {
        return explainationId;
    }

    public void setExplainationId(int explainationId) {
        this.explainationId = explainationId;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Explanation that = (Explanation) o;
        return explainationId == that.explainationId && wordId == that.wordId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(explainationId, wordId);
    }
}
