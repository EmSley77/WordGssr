package es.guesstheword.repository;

import es.guesstheword.entity.Words;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordsRepo extends JpaRepository<Words, Integer> {
    List<Words> findWordsByWordContaining(String s);
    Words findByWord(String word);
    Words findWordsByWordId(int id);
}
