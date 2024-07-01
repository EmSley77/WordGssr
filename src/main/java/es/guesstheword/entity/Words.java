package es.guesstheword.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
@Data
@Entity
public class Words {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "word_id", nullable = false)
    private int wordId;
    @Basic
    @Column(name = "word", nullable = false, length = 50)
    private String word;


}
