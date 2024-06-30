package es.guesstheword.repository;

import es.guesstheword.entity.Clues;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClueRepo extends JpaRepository<Clues, Integer> {
    List<Clues> findCluesByWord(String word);
}
