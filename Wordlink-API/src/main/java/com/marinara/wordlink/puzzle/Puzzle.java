package com.marinara.wordlink.puzzle;

import com.marinara.wordlink.repository.WordlistRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Stores a single puzzle, which has a starting word and a target word.
 * This can also be used to generate a puzzle with a random starting and ending word with a certain parameter
 * for the word size, or go random.
 */
@Getter
@Setter
public class Puzzle {

    private String startingWord;
    private String targetWord;

    private WordlistRepository wordlistRepository;

    private Word baseWord; // The start of our graph
    private List<String> solution; // The solution to the puzzle (if there is one)

    private int bestSolve; // The best solver of this puzzle

    /**
     * Base constructor, given starting word and target word. Generates the solution
     * after construction
     *
     * @param startingWord Starting word
     * @param targetWord Target word
     */
    public Puzzle(String startingWord, String targetWord, WordlistRepository wordlistRepository) {
        this.startingWord = startingWord;
        this.targetWord = targetWord;

        this.wordlistRepository = wordlistRepository;

        this.bestSolve = Integer.MAX_VALUE;

        baseWord = new Word(startingWord, this, this.wordlistRepository);
        generateSolution();
    }

    /**
     * Generates the solution to the puzzle. Will set the solution list to each
     * step in order, including the starting and target word.
     */
    public void generateSolution() {
        solution = new ArrayList<>();
        Word targetNode = null; // Solution, will stay null if there isn't one

        HashSet<String> visited = new HashSet<>();
        LinkedList<Word> queue = new LinkedList<>();

        queue.add(baseWord);
        visited.add(baseWord.getValue());

        while (queue.size() != 0) {
            // Compute the children of the next word on the queue
            Word current = queue.poll();
            current.computeConnectedNodes(visited);

            // Check if we found the solution
            if (current.getConnectedWords().size() == 1
                    && current.getConnectedWords().get(0).getValue().equalsIgnoreCase(targetWord)) {
                targetNode = current.getConnectedWords().get(0);
                break;
            }

            // Add each child to the queue
            for (Word w : current.getConnectedWords()) {
                visited.add(w.getValue());
                queue.add(w);
            }
        }

        // Check if we found a solution, otherwise simply return
        if (targetNode == null)
            return;

        // Iterate backwards through the children, adding them to the solutions list
        Word current = targetNode;

        while (current != baseWord) {
            solution.add(0, current.getValue());
            current = current.getParentWord();
        }

        solution.add(0, baseWord.getValue());
    }

}
