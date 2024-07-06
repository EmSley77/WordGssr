package es.guesstheword.logic;
/*
Emanuel sleyman
2024-06-29
logic for creating a word and clues and explanations
*/

import es.guesstheword.entity.Clues;
import es.guesstheword.entity.Explanation;
import es.guesstheword.entity.Words;
import es.guesstheword.repository.ClueRepo;
import es.guesstheword.repository.ExplanationRepo;
import es.guesstheword.repository.WordsRepo;
import org.springframework.stereotype.Component;

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

        System.out.print("ADD CLUE 1 TO WORD: ");
        String newClue = input.nextLine().trim();
        if (clueRepo.findCluesByClue(newClue) != null) {
            System.out.println("CLUE IS ALREADY REGISTERED, TRY ANOTHER");
            return;
        }

        Clues clue = new Clues();
        clue.setClue(newClue);
        clue.setWordId(word.getWordId());
        clueRepo.save(clue);
        System.out.println("SAVED CLUE FOR WORD: " + word.getWord());

        System.out.print("ADD CLUE 2 TO WORD: ");
        String newClue2 = input.nextLine().trim();
        if (clueRepo.findCluesByClue(newClue2) != null) {
            System.out.println("CLUE IS ALREADY REGISTERED, TRY ANOTHER");
            return;
        }

        Clues clue2 = new Clues();
        clue2.setClue(newClue);
        clue2.setWordId(word.getWordId());
        clueRepo.save(clue2);
        System.out.println("SAVED CLUE FOR WORD: " + word.getWord());

        System.out.print("ADD CLUE 3 TO WORD: ");
        String newClue3 = input.nextLine().trim();
        if (clueRepo.findCluesByClue(newClue3) != null) {
            System.out.println("CLUE IS ALREADY REGISTERED, TRY ANOTHER");
            return;
        }

        Clues clue3 = new Clues();
        clue3.setClue(newClue);
        clue3.setWordId(word.getWordId());
        clueRepo.save(clue3);
        System.out.println("SAVED CLUE FOR WORD: " + word.getWord());
        System.out.println("THANK YOU FOR REGISTERING CLUES, HAVE A NICE DAY");


    }

    public void addClues() {

        System.out.print("ENTER WORD ID: ");
        String wordId = input.nextLine().trim();

        Words word = wordsRepo.findWordsByWordId(Integer.parseInt(wordId));
        if (word == null) {
            System.out.println("COULD NOT FIND WORD");
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
}


