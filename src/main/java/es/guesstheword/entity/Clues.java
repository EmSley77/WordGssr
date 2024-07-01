package es.guesstheword.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Data
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
}
