package com.marinara.wordlink.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stores the starting and target word for a puzzle
 */
public class PuzzleUtils {
    /**
     * Converts the solution in the record (comma seperated) to a list to be stored in the puzzle object
     *
     * @param recordSolution Comma seperated string as stored in the DB
     * @return The list
     */
    public static List<String> recordSolutionToList(String recordSolution) {
        return Arrays.stream(recordSolution.split(",")).collect(Collectors.toList());
    }

    public static String recordSolutionListToString(List<String> solution) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < solution.size() - 1 ; i++) {
            sb.append(solution.get(i));
            sb.append(",");
        }

        sb.append(solution.get(solution.size() - 1));
        return sb.toString();
    }
}
