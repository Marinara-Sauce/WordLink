package com.marinara.wordlink.service;

import com.marinara.wordlink.puzzle.Puzzle;
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * Stores and controls the current puzzle
 */
@Service
@Getter
public class PuzzleService {

    Puzzle currentPuzzle;

    public PuzzleService() {
        // TODO: Find some way to randomly generate puzzles
        currentPuzzle = new Puzzle("fast", "slow");
    }



}
