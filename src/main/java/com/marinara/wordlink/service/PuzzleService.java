package com.marinara.wordlink.service;

import com.marinara.wordlink.puzzle.Puzzle;
import com.marinara.wordlink.repository.WordlistRepository;
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * Stores and controls the current puzzle
 */
@Service
@Getter
public class PuzzleService {

    Puzzle currentPuzzle;
    Puzzle previousPuzzle;

    WordlistRepository wordlistRepository;

    public PuzzleService(WordlistRepository wordlistRepository) {
        this.wordlistRepository = wordlistRepository;

        generateNewPuzzle();
    }

    /**
     * Generates a new puzzle, sets the current puzzle to that puzzle
     * This code is going to be bad, will need some optimizing
     *
     * @return The new puzzle
     */
    public Puzzle generateNewPuzzle() {
        this.previousPuzzle = currentPuzzle;

        // Keep generating puzzles until we find one with a good solution
        while (true) {
            // Pick a random length between 6 and 4
            int wordLength = (int) (Math.random() * (6 - 4)) + 4;

            String startingWord = wordlistRepository.getRandomWord(wordLength);
            String targetWord = wordlistRepository.getRandomWord(wordLength);

            System.out.println("Attempting to generate puzzle with length: " + wordLength);
            System.out.println("Starting Word: " + startingWord + " | Target Word: " + targetWord);

            Puzzle puzzle = new Puzzle(startingWord, targetWord, wordlistRepository);
            if (!puzzle.getSolution().isEmpty()) {
                this.currentPuzzle = puzzle;
                return puzzle;
            }
        }
    }
}
