package es.guesstheword.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
public class Explanation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "explaination_id", nullable = false)
    private int explainationId;
    @Basic
    @Column(name = "word_id", nullable = false)
    private int wordId;
    @Basic
    @Column(name = "word_explanation", nullable = false, length = 100)
    private String wordExplanation;

}
