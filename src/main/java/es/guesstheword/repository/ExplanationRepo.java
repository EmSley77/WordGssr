package es.guesstheword.repository;

import es.guesstheword.entity.Explanation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExplanationRepo extends JpaRepository<Explanation, Integer> {
    Explanation findExplanationByWordId(int id);
}
