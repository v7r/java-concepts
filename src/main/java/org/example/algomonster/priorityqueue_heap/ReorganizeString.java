package org.example.algomonster.priorityqueue_heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Reorganize String
 * Given a string s, check if the letters can be rearranged so that two characters that are adjacent to each other are
 * not the same.
 *
 * If possible, output any possible result. If not possible, return the empty string.
 *
 * Example 1:
 * Input:s = "aab"
 * Output: aba
 * Example 2:
 * Input:s = "aaab"
 * Output: ``
 * Note:
 * s will consist of lowercase letters and have length in the range [1, 500].
 */
public class ReorganizeString {
    public static String reorganizeString(String s) {
        //return reorganizeStringAlgoMonster(s);
        return reorganizeStringCanonical(s);
    }

    private static String reorganizeStringCanonical(String s) {
        if (s == null || s.isEmpty()) return "";
        int n = s.length();
        Map<Character, Integer> charCount = new HashMap<>();
        for (Character c : s.toCharArray()) {
            charCount.compute(c, (k,v) -> v == null ? 1 : ++v);
        }
        PriorityQueue<Map.Entry<Character, Integer>> q =
                new PriorityQueue<>((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));
        for (Map.Entry<Character, Integer> e : charCount.entrySet()) {
            q.offer(e);
        }
        Integer topCharCount = q.peek().getValue();
        // Top character should leave enough gaps inbetween to be occupied by others.
        // Otherwise, we can have same char sequence, therefore, impossible to arrange.
        if (topCharCount > (n + 1) / 2) return "";

        StringBuilder sb = new StringBuilder();
        Map.Entry<Character, Integer> prev = null;
        while (!q.isEmpty()) {
            Map.Entry<Character, Integer> curr = q.poll();
            sb.append(curr.getKey());
            curr.setValue(curr.getValue() - 1);
            if (prev != null && prev.getValue() > 0) {
                q.offer(prev);
            }
            prev = curr;
        }
        return sb.toString();
    }
}
