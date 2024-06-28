package es.guesstheword.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Words {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "word_id", nullable = false)
    private int wordId;
    @Basic
    @Column(name = "word", nullable = false, length = 50)
    private String word;

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Words words = (Words) o;
        return wordId == words.wordId && Objects.equals(word, words.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordId, word);
    }
}
