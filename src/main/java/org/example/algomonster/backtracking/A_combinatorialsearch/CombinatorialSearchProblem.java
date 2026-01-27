package org.example.algomonster.backtracking.A_combinatorialsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a non-negative integer n, find all n-letter words composed by 'a' and 'b', return them in a list of strings in
 * lexicographical order.
 *
 * Input: 2
 * Output: ["aa", "ab", "ba", "bb"]
 *
 * Input: 4
 * Output: ["aaaa", "aaab", "aaba", "aabb", "abaa", "abab", "abba", "abbb", "baaa", "baab", "baba", "babb", "bbaa",
 * "bbab", "bbba", "bbbb"]
 */
public class CombinatorialSearchProblem {
    private static final Character[] CHAR_SET = new Character[]{'a','b'};


    private static void buildWords(int n, List<Character> path, List<String> words) {
        if (n == path.size()) {
            words.add(path.stream().map(String::valueOf).collect(Collectors.joining()));
            return;
        }
        for (Character c : CHAR_SET) {
            path.add(c);
            buildWords(n, path, words);
            path.remove(path.size()-1);
        }
    }


    public static List<String> letterCombination(int n) {
        List<String> words = new ArrayList<>();
        buildWords(n,new ArrayList<>(),words);
        Collections.sort(words);
        return words;
    }
}
