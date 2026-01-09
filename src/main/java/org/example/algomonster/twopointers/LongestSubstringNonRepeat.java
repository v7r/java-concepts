package org.example.algomonster.twopointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Find the length of the longest substring of a given string without repeating characters.
 *
 * Input: abccabcabcc
 *
 * Output: 3
 *
 * Explanation: The longest substrings are abc and cab, both of length 3.
 *
 * Input: aaaabaaa
 *
 * Output: 2
 *
 * Explanation: ab is the longest substring, with a length of 2.
 */
public class LongestSubstringNonRepeat {
    public static int longestSubstringWithoutRepeatingCharacters(String s) {
        int max = 0;
        int l = 0, r = 0;
        Map<Character, Integer> window = new HashMap<>();
        while (r<s.length()) {
            Character c = s.charAt(r);
            while (window.containsKey(c)) {
                window.remove(s.charAt(l));
                l++;
            }
            window.put(c,1);
            max = Math.max(max,1+r-l);
            r++;
        }
        return max;
    }
}
