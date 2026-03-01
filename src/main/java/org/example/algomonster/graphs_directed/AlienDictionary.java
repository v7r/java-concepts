package org.example.algomonster.graphs_directed;

import java.util.*;
import java.util.stream.Collectors;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 *
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of
 * this new language.
 *
 * Derive the order of letters in this language.
 *
 * Note:
 *
 * You may assume all letters are in lowercase.
 * Every letter that appears in the input must also appear in the output, and your output cannot have characters not in
 * the input.
 * If no ordering of letters makes the dictionary sorted lexicographically, return an empty string.
 * There may be multiple valid orders. If that's the case, return the smallest in normal lexicographical order.
 * Input
 * words: A list of strings of size n, representing the dictionary words sorted lexicographically in the alien language.
 * Output
 * A string representing the smallest possible lexicographical order, or an empty string if no valid order exists.
 *
 * Examples
 * Example 1:
 * Input:
 *
 * words = ["wrt","wrf","er","ett","rftt"]
 * Output: wertf
 *
 * Explanation:
 *
 * Example 2:
 * Input:
 *
 * words = ["z","x"]
 * Output: zx
 *
 * Explanation:
 *  <img src="imgs/AlienDictionary.png" />
 *
 * From z and x，we can get z < x. So return zx.
 *
 * Constraints
 * 2 <= n <= 10000
 * 1 <= words[i] <= 30
 *
 */
public class AlienDictionary {
    public static String alienOrder(List<String> words) {
        Map<Character, Set<Character>> outDegree = new HashMap<>();
        Map<Character, Set<Character>> inDegree = new HashMap<>();
        for(String s : words) {
            for (int i = 0; i < s.length(); i++) {
                outDegree.computeIfAbsent(s.charAt(i), k -> new HashSet<>());
                inDegree.computeIfAbsent(s.charAt(i), k -> new HashSet<>());
            }
        }
        //List<Character> ans = new ArrayList<>();
        StringJoiner ans = new StringJoiner("");
        String prev = words.get(0);
        for (int i = 1; i < words.size(); i++) {
            String curr = words.get(i);
            for (int j = 0; j < Math.min(prev.length(),curr.length()); j++) {
                if (prev.charAt(j) != curr.charAt(j)) {
                    outDegree.get(prev.charAt(j)).add(curr.charAt(j));
                    inDegree.get(curr.charAt(j)).add(prev.charAt(j));
                    break;
                }
            }
            prev = curr;
        }

        List<Character> edges = inDegree.entrySet().stream()
                .filter(e -> e.getValue().isEmpty())
                .map(e -> e.getKey())
                .collect(Collectors.toList());
        Queue<Character> q = new PriorityQueue<>();
        q.addAll(edges);

        while (!q.isEmpty()) {
            // In case of multiple characters at a level, we should consider the lowest in lexical order
            // that is the reason why we are using PriorityQueue.
            Character currentChar = q.poll();
            ans.add(String.valueOf(currentChar));
            Set<Character> outDegreeSet = outDegree.get(currentChar);
            for (Character outDegreeChar : outDegreeSet) {
                inDegree.get(outDegreeChar).remove(currentChar);
                if (inDegree.get(outDegreeChar).isEmpty()) {
                    q.add(outDegreeChar);
                }
            }
        }

        String finalString = ans.toString();
        return finalString.length() == outDegree.size() ?
                finalString
                : "";
    }
}
