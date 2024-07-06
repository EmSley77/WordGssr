package es.guesstheword.repository;

import es.guesstheword.entity.Words;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordsRepo extends JpaRepository<Words, Integer> {

    Words findByWord(String word);
    Words findWordsByWordId(int id);
}
