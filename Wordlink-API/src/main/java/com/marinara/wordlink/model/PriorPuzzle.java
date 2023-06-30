package com.marinara.wordlink.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class PriorPuzzle {
    private List<String> solution;
    private int bestSolve;
    private double avgSteps;
    private int numSolves;
}
