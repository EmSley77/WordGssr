package es.guesstheword.repository;

import es.guesstheword.entity.Clues;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClueRepo extends JpaRepository<Clues, Integer> {
}
