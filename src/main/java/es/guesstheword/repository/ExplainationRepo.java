package es.guesstheword.repository;

import es.guesstheword.entity.Explanation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExplainationRepo extends JpaRepository<Explanation, Integer> {
}
