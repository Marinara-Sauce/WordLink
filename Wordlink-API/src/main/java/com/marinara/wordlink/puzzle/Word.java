package com.marinara.wordlink.puzzle;

import com.marinara.wordlink.repository.WordlistRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Represents a single word in the puzzle.
 */
@NoArgsConstructor
@Getter
public class Word {

    private String value; // Word in the list

    private List<Word> connectedWords; // Words that are connected to this node for BFS
    private Word parentWord; // Word this connects to, may be null
    private Puzzle puzzle; // Reference to the puzzle

    private WordlistRepository wordlistRepository;

    public Word(String value, Puzzle puzzle, WordlistRepository wordlistRepository) {
        this.value = value;
        this.puzzle = puzzle;
        this.wordlistRepository = wordlistRepository;

        this.parentWord = null;
        this.connectedWords = new ArrayList<>();
    }

    public Word(String value, Word parentWord, Puzzle puzzle, WordlistRepository wordlistRepository) {
        this.value = value;
        this.puzzle = puzzle;
        this.wordlistRepository = wordlistRepository;

        this.parentWord = parentWord;
        this.connectedWords = new ArrayList<>();
    }

    /**
     * Populates the children list with any possible moves for this word
     *
     * @param exclude List of words we already visited
     * @return True if the child is the target
     */
    public boolean computeConnectedNodes(HashSet<String> exclude) {
        final char[] LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        for (int i = 0 ; i < value.length() ; i++) {
            for (int j = 0 ; j < LETTERS.length ; j++) {
                String newWord = changeCharInPosition(i, LETTERS[j], value);
                // If we found the target, set that as the only child, then return true
                if (newWord.equalsIgnoreCase(puzzle.getTargetWord())) {
                    connectedWords.clear();
                    connectedWords.add(new Word(newWord, this, puzzle, this.wordlistRepository));
                    return true;
                }

                // Check that this new word isn't already visited, and is a valid word
                if (!exclude.contains(newWord) && wordlistRepository.isValidWord(newWord)) {
                    connectedWords.add(new Word(newWord, this, puzzle, this.wordlistRepository));
                }

            }
        }

        return false;
    }

    /**
     * Helper function to generate a new word with a single letter replaced
     *
     * @param pos Index of the word to replace
     * @param c Character to replace that with
     * @param str Target string
     * @return New String
     */
    public String changeCharInPosition(int pos, char c, String str) {
        char[] charArray = str.toCharArray();
        charArray[pos] = c;
        return new String(charArray);
    }

    @Override
    public String toString() {
        return value;
    }

}
