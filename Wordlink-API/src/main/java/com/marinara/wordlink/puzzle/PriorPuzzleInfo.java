package com.marinara.wordlink.puzzle;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Used to send information related to the previous puzzle
 */
@Getter
@Builder
public class PriorPuzzleInfo {
    private List<String> solution;
    private int bestSolve;
}
