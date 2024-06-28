package es.guesstheword.repository;

import es.guesstheword.entity.Explaination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExplainationRepo extends JpaRepository<Explaination, Integer> {
}
