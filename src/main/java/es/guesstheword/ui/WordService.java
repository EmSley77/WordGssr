package es.guesstheword.ui;
/*
Emanuel sleyman
2024-07-07
this is a service class for creating words, clues and explanations
*/

import es.guesstheword.logic.WordLogic;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    private WordLogic wordLogic;

    public WordService(WordLogic wordLogic) {
        this.wordLogic = wordLogic;
    }

    public void createWord() {
        wordLogic.createNewWord();
    }

    public void addClue() {
        wordLogic.addClues();
    }

    public void getWords() {
        wordLogic.getAllWords();
    }

    public void getWordsBySearch() {
        wordLogic.getWordsBySearch();
    }

    public void getCluesByWordId() {
        wordLogic.getCluesByWordId();
    }


    public void deleteWordById() {
        wordLogic.deleteWorById();
    }
}
