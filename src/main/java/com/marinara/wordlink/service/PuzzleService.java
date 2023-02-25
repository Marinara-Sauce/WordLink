package com.marinara.wordlink.service;

import com.marinara.wordlink.puzzle.Puzzle;
import com.marinara.wordlink.repository.WordlistRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Stores and controls the current puzzle
 */
@Service
@Getter
public class PuzzleService {

    Puzzle currentPuzzle;

    WordlistRepository wordlistRepository;

    public PuzzleService(WordlistRepository wordlistRepository) {
        this.wordlistRepository = wordlistRepository;

        // TODO: Find some way to randomly generate puzzles
        currentPuzzle = new Puzzle("fast", "slow", wordlistRepository);
    }
}
