package org.example.algomonster.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Word Ladder is "A puzzle begins with two words, and to solve the puzzle one must find a chain of other words to
 * link the two, in which two adjacent words (that is, words in successive steps) differ by one letter."
 *
 * For example: COLD → CORD → CARD → WARD → WARM
 *
 *
 *
 * Given a start word, an end word, and a list of dictionary words, determine the minimum number of steps to go from
 * the start word to the end word using only words from the dictionary.
 *
 * Input:
 *
 * start = "COLD"
 * end = "WARM"
 * word_list = ["COLD", "GOLD", "CORD", "SOLD", "CARD", "WARD", "WARM", "TARD"]
 * Output: 4
 *
 * Explanation: We can go from COLD to WARM by going through COLD → CORD → CARD → WARD → WARM
 *
 */
public class WordLadder {
    private static int diff(String a, String b) {
        if (a.length() != b.length()) return -1;
        int c = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) c++;
        }
        return c;
    }

    public static int wordLadder(String begin, String end, List<String> wordList) {
        List<String> visitedSet = new ArrayList<>();
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.add(begin);
        visitedSet.add(begin);
        int minSteps = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                String currentWord = queue.pop();
                if (currentWord.equals(end)) return minSteps;
                for (String w : wordList) {
                    if (!visitedSet.contains(w) && diff(currentWord, w) == 1) {
                        visitedSet.add(w);
                        queue.add(w);
                    }
                }
            }
            minSteps++;
        }
        return -1;
    }
}
