package com.marinara.wordlink.service;

import com.marinara.wordlink.model.Puzzle;
import com.marinara.wordlink.model.Solve;
import com.marinara.wordlink.persistence.SolveRepository;
import com.marinara.wordlink.resources.PuzzleGenerator;
import com.marinara.wordlink.persistence.PuzzleRepository;
import com.marinara.wordlink.persistence.WordlistRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Stores and controls the current puzzle
 */
@Service
@Getter
public class PuzzleService {

    Puzzle currentPuzzle;
    Puzzle previousPuzzle;

    WordlistRepository wordlistRepository;
    PuzzleRepository puzzleRepository;
    SolveRepository solveRepository;

    public PuzzleService(WordlistRepository wordlistRepository, PuzzleRepository puzzleRepository, SolveRepository solveRepository) {
        this.wordlistRepository = wordlistRepository;
        this.puzzleRepository = puzzleRepository;
        this.solveRepository = solveRepository;

        generateNewPuzzle(false);
    }

    /**
     * Generates a new puzzle, sets the current puzzle to that puzzle
     * This code is going to be bad, will need some optimizing
     */
    public void generateNewPuzzle(boolean useFiveLetters) {
        this.previousPuzzle = currentPuzzle;

        // Keep generating puzzles until we find one with a good solution
        while (true) {
            int wordLength = useFiveLetters ? 5 : 4;

            String startingWord = wordlistRepository.getRandomWord(wordLength);
            String targetWord = wordlistRepository.getRandomWord(wordLength);

            System.out.println("Attempting to generate puzzle with length: " + wordLength);
            System.out.println("Starting Word: " + startingWord + " | Target Word: " + targetWord);

            PuzzleGenerator puzzle = new PuzzleGenerator(startingWord, targetWord, wordlistRepository);

            if (!puzzle.getSolution().isEmpty()) {
                this.currentPuzzle = puzzle.generateRecord();

                puzzleRepository.storePuzzle(currentPuzzle);
                return;
            }
        }
    }

    /**
     * Stores a solution to the database, assumes the solution is correct.
     *
     * @param numSteps List of the words
     */
    public void storeSolve(int numSteps) {
        Solve solve = Solve.builder()
                .p_id(currentPuzzle.getP_id())
                .numSteps(numSteps)
                .timestamp(LocalDateTime.now())
                .build();

        solveRepository.storeSolve(solve);
    }
}
