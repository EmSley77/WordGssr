package es.guesstheword.logic;
/*
Emanuel sleyman
2024-07-06
logic for creating a word and clues and explanations
*/

import es.guesstheword.entity.Clues;
import es.guesstheword.entity.Explanation;
import es.guesstheword.entity.Words;
import es.guesstheword.repository.ClueRepo;
import es.guesstheword.repository.ExplanationRepo;
import es.guesstheword.repository.WordsRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class WordLogic {

    private WordsRepo wordsRepo;

    private ExplanationRepo explanationRepo;

    private ClueRepo clueRepo;

    private Scanner input = new Scanner(System.in);

    public WordLogic(WordsRepo wordsRepo, ExplanationRepo explanationRepo, ClueRepo clueRepo) {
        this.wordsRepo = wordsRepo;
        this.explanationRepo = explanationRepo;
        this.clueRepo = clueRepo;
    }

    public void createNewWord() {
        System.out.println("REMEMBER TO GO THROUGH ALL QUESTIONS FOR A SUCCESSFUL WORD, EXPLANATION AND CLUES");
        System.out.println("ENTER 2 TO EXIT");
        System.out.print("ENTER A NEW WORD: ");
        String newWord = input.nextLine().trim();
        if (newWord.equals("2")) {
            System.out.println("EXITED FROM ADD NEW WORD");
            return;
        }
        if (wordsRepo.findByWord(newWord) != null) {
            System.out.println("WORD IS ALREADY REGISTERED, TRY ANOTHER WORD");
            return;
        }
        Words word = new Words();
        word.setWord(newWord);
        wordsRepo.save(word);
        System.out.println("NEW WORD ADDED");

        System.out.println("ADD AN EXPLANATION FOR YOUR NEW WORD");
        System.out.print("EXPLANATION: ");
        String explanation = input.nextLine().trim();

        addExplanation(explanation, word);



    }

    public void addClues() {

        System.out.print("ENTER WORD ID: ");
        String wordId = input.nextLine().trim();

        Words word = wordsRepo.findWordsByWordId(Integer.parseInt(wordId));
        if (word == null) {
            System.out.println("COULD NOT FIND WORD");
            return;
        }
        if (clueRepo.findCluesByWordId(word.getWordId()).size() == 3) {
            System.out.println("YOU CAN ONLY HAVE 3 CLUES PER WORD");
            return;
        }

        System.out.print("ENTER CLUE FOR WORD: ");
        String clue1 = input.nextLine().trim();

        Clues clue = new Clues();
        clue.setWordId(word.getWordId());
        clue.setClue(clue1);
        clueRepo.save(clue);
        System.out.println("SAVED CLUE FOR WORD: " + word.getWord());

    }

    public void addExplanation(String explanation, Words word) {

        Explanation explanation1 = new Explanation();
        explanation1.setWordExplanation(explanation);
        explanation1.setWordId(word.getWordId());
        explanationRepo.save(explanation1);
        System.out.println("SAVED EXPLANATION FOR WORD: " + word.getWord());

    }

    //TODO: create a service class for this class, impl all these methods and test

    //get clues by inputting wordId
    public void getCluesByWordId() {
        System.out.println("ENTER 2 TO EXIT SEARCH");
        System.out.println("ENTER WORD ID TO FIND CLUES");
        System.out.print("ENTER WORD ID: ");

        String findClues = input.nextLine().trim();

        List<Clues> clues = clueRepo.findCluesByWordId(Integer.parseInt(findClues));
        if (clues.isEmpty()) {
            System.out.println("NO CLUES FOUND FOR THIS WORD");
            return;
        }

        for (Clues c : clues) {
            System.out.println(c);
        }
    }


    //get all words
    public void getAllWords() {
        List<Words> words = wordsRepo.findAll();
        if (words.isEmpty()) {
            System.out.println("NO WORDS FOUND");
            return;
        }

        for (Words word : words) {
            System.out.println(word);
        }
    }

    //search for words and get information
    public void getWordsBySearch() {
        System.out.println("ENTER 2 TO EXIT SEARCH");
        System.out.println("ENTER WORD TO FIND");
        System.out.print("ENTER SEARCH: ");
        String findWord = input.nextLine().trim();
        List<Words> words = wordsRepo.findWordsByWordContaining(findWord);
        if (words.isEmpty()) {
            System.out.println("NO WORDS FOUND, TRY AGAIN");
            return;
        }

        for (Words w : words) {
            System.out.println(w);
        }
    }


}


