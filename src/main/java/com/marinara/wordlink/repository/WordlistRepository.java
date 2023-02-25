package com.marinara.wordlink.persistence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Handles the wordlist, can be used to query if words exist. This
 * repository works off a file, only reading, never writing.
 */
@Component
public class WordlistRepository {

    private static HashSet<String> wordlist;

    public WordlistRepository(@Value("${wordlist.location}") String wordlistLocation) throws IOException {
        load(wordlistLocation);
    }

    /**
     * Loads the wordlist file into the wordlist hashset. Called upon init
     *
     * @param wordlistLocation Location to the wordlist file
     * @throws IOException File does not exist
     */
    private void load(String wordlistLocation) throws IOException {
        wordlist = new HashSet<>();

        File wordlistFile = new File(wordlistLocation);
        Scanner wordScanner = new Scanner(wordlistFile);

        while (wordScanner.hasNextLine()) {
            wordlist.add(wordScanner.nextLine().toLowerCase());
        }

        wordScanner.close();
    }

    /**
     * Checks if a given word is in the wordlist
     *
     * @param word Word to check
     * @return True if in the wordlist
     */
    public boolean isValidWord(String word) {
        return wordlist.contains(word.toLowerCase());
    }

}
