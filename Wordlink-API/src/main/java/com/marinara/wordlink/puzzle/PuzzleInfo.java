package com.marinara.wordlink.puzzle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Stores the starting and target word for a puzzle
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PuzzleInfo {
    private String startingWord;
    private String targetWord;
    private int bestSolve;
}
