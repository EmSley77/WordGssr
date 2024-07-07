package es.guesstheword.service;
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
}
