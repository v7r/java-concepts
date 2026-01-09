package org.example.algomonster.twopointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer
 * in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 *
 * Constraints:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s and p consist of lowercase English letters.
 */
public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (p.length() > s.length()) return result;
        Map<Character, Integer> checkSet = new HashMap<>();
        Map<Character, Integer> windowSet = new HashMap<>();
        for (int i=0; i<p.length(); i++) {
            checkSet.compute(p.charAt(i),(c,v) -> v == null ? 1 : ++v);
            windowSet.compute(s.charAt(i),(c, v) -> v == null ? 1 : ++v);
        }

        if (windowSet.equals(checkSet)) result.add(0);
        for (int i=p.length(); i<s.length(); i++) {
            int j = i - p.length();
            windowSet.compute(s.charAt(j), (c,v) -> v != null && --v > 0 ? v : null);
            windowSet.compute(s.charAt(i), (c, v) -> v == null ? 1 : ++v);
            if (windowSet.equals(checkSet)) result.add(++j);
        }

        return result;
    }
}
