package com.marinara.wordlink.controller;

import com.marinara.wordlink.model.PriorPuzzle;
import com.marinara.wordlink.model.Puzzle;
import com.marinara.wordlink.utils.PuzzleUtils;
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
     * Fetches and returns the starting and target words
     *
     * @return Start and target words
     */
    @GetMapping("")
    public ResponseEntity<Puzzle> getPuzzleInfo() {
        return new ResponseEntity<>(puzzleService.getCurrentPuzzle(), HttpStatus.OK);
    }

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
        List<String> puzzleSol = PuzzleUtils.recordSolutionToList(puzzleService.getCurrentPuzzle().getSolution());

       return new ResponseEntity<>(puzzleSol, HttpStatus.OK);
    }

    /**
     * Fetches the solution for the previous puzzle
     *
     * @return List of the previous solution path
     */
    @GetMapping("/previous")
    public ResponseEntity<PriorPuzzle> getPreviousSolution() {
        return new ResponseEntity<>(puzzleService.getPriorStats(), HttpStatus.OK);
    }

    /**
     * Checks if a given solution is a valid solution. Does not check if it's the best solution
     * A solution is valid if every word in the proposed solution is valid.
     *
     * @return Is the solution valid
     */
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateSolution(@RequestBody List<String> solution) {

        if (solution.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ResponseEntity<Boolean> failedEntity = new ResponseEntity<>(false, HttpStatus.OK);

        // Check that the first and last items are the start and end of the puzzle
        if (!(solution.get(0).equalsIgnoreCase(puzzleService.getCurrentPuzzle().getStart()) &&
            solution.get(solution.size() - 1).equalsIgnoreCase(puzzleService.getCurrentPuzzle().getTarget()))) {
            return failedEntity;
        }

        // Check that each word is valid and only different by one character
        for (int i = 1 ; i < solution.size() ; i++) {
            if (!puzzleService.getWordlistRepository().isValidWord(solution.get(i)) ||
                    !isOneStepAway(solution.get(i), solution.get(i - 1))) {
                return failedEntity;
            }
        }

        for (String s : solution) {
            if (!puzzleService.getWordlistRepository().isValidWord(s)) {
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        }

        puzzleService.storeSolve(solution.size());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Checks if a given step is valid
     *
     * @param prevWord The previous word
     * @param nextWord The proposed next word
     * @return Is the current step valid?
     */
    @GetMapping("/validateStep")
    public ResponseEntity<String> validateStep(@RequestParam String prevWord, @RequestParam String nextWord) {
        prevWord = prevWord.toLowerCase();
        nextWord = nextWord.toLowerCase();

        if (nextWord.length() != prevWord.length()) {
            return new ResponseEntity<>("BAD_SIZE", HttpStatus.OK);
        }

        if (!puzzleService.getWordlistRepository().isValidWord(nextWord)) {
            return new ResponseEntity<>("NOT_A_WORD", HttpStatus.OK);
        }

        if (isOneStepAway(prevWord, nextWord)) {
            if (isOneStepAway(nextWord, puzzleService.getCurrentPuzzle().getTarget())) {
                return new ResponseEntity<>("SOLUTION", HttpStatus.OK);
            }

            return new ResponseEntity<>("VALID", HttpStatus.OK);
        }

        return new ResponseEntity<>("NOT_A_VALID_STEP", HttpStatus.OK);
    }

    /**
     * Checks if two words are one letter away from each other
     *
     * @return true if they're one letter away
     */
    public boolean isOneStepAway(String a, String b) {
        int identicalChars = 0;
        for (int i = 0 ; i < a.length() ; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                identicalChars++;
                if (identicalChars > a.length() - 1) {
                    return false;
                }
            }
        }
            return identicalChars == a.length() - 1;
    }
}
