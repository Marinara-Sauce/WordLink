package com.marinara.wordlink.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Handles the wordlist, can be used to query if words exist. This
 * repository works off a file, only reading, never writing.
 */
@Repository
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
            String word = wordScanner.nextLine().toLowerCase();
            if (!word.contains("'")) {
                wordlist.add(word);
            }
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

    /**
     * Gets a random word of length
     *
     * @param length Length to check
     * @return Random word
     */
    public String getRandomWord(int length) {
        // Iterate until we get a random word with the right length
        while (true) {
            int randomNumber = (int) (Math.random() * wordlist.size());

            Iterator<? extends String> iterator = wordlist.iterator();

            int currentIndex = 0;
            String randomElement = null;
            boolean hitRandomElement = false;

            while (iterator.hasNext()) {
                randomElement = iterator.next();
                currentIndex++;

                if (hitRandomElement && randomElement.length() == length) {
                    return randomElement;
                }

                if (currentIndex == randomNumber) {
                    if (randomElement.length() == length) {
                        return randomElement;
                    }

                    hitRandomElement = true;
                }
            }
        }
    }

}
