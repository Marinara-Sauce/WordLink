package com.marinara.wordlink.controller;

import com.marinara.wordlink.puzzle.Puzzle;
import com.marinara.wordlink.service.PuzzleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puzzle")
@AllArgsConstructor
public class PuzzleController {

    private PuzzleService puzzleService;

    /**
     * Debug function to generate a new puzzle
     *
     * @return OK
     */
    @GetMapping("/generateNew")
    public ResponseEntity generateNewPuzzle() {
        puzzleService.generateNewPuzzle();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Gets the current puzzles solution
     * TODO: Delete this
     * @return The current puzzle
     */
    @GetMapping("/solution")
    public ResponseEntity<List<String>> getCurrentPuzzle() {
       return new ResponseEntity<>(puzzleService.getCurrentPuzzle().getSolution(), HttpStatus.OK);
    }

    /**
     * Fetches the solution for the previous puzzle
     *
     * @return List of the previous solution path
     */
    @GetMapping("/previous")
    public ResponseEntity<List<String>> getPreviousSolution() {
        Puzzle prevPuzzle = puzzleService.getPreviousPuzzle();

        if (prevPuzzle == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(prevPuzzle.getSolution(), HttpStatus.OK);
    }

    /**
     * Checks if a given solution is a valid solution. Does not check if it's the best solution
     * A solution is valid if every word in the proposed solution is valid.
     *
     * @return Is the solution valid
     */
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateSolution(@RequestBody List<String> solution) {
        // TODO: Confirm that each step is only one letter off from each other
        for (String s : solution) {
            if (!puzzleService.getWordlistRepository().isValidWord(s)) {
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Checks if a given step is valid
     *
     * @param prevWord The previous word
     * @param nextWord The proposed next word
     * @return Is the current step valid?
     */
    @PostMapping("/validateStep")
    public ResponseEntity<String> validateStep(@RequestParam String prevWord, @RequestParam String nextWord) {
        if (nextWord.length() != prevWord.length()) {
            return new ResponseEntity<>("BAD_SIZE", HttpStatus.OK);
        }

        if (!puzzleService.getWordlistRepository().isValidWord(nextWord)) {
            return new ResponseEntity<>("NOT_A_WORD", HttpStatus.OK);
        }

        int identicalChars = 0;
        for (int i = 0 ; i < prevWord.length() ; i++) {
            if (prevWord.charAt(i) == nextWord.charAt(i)) {
                identicalChars++;
                if (identicalChars > prevWord.length() - 1) {
                    return new ResponseEntity<>("NOT_A_VALID_STEP", HttpStatus.OK);
                }
            }
        }
        if (identicalChars == prevWord.length() - 1) {
            return new ResponseEntity<>("VALID", HttpStatus.OK);
        }

        return new ResponseEntity<>("NOT_A_VALID_STEP", HttpStatus.OK);
    }
}
