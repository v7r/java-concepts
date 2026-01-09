package org.example.algomonster.twopointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, return the length of the longest substring that contains at most two distinct characters.
 *
 * Example 1:
 *
 * Input: s = "eceba"
 * Output: 3
 * Explanation: The substring is "ece" which its length is 3.
 *
 * Example 2:
 *
 * Input: s = "ccaabbb"
 * Output: 5
 * Explanation: The substring is "aabbb" which its length is 5.
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of English letters.
 */
public class LongestSubstringWithTwoDistinctChars {
    //"ccaabbb"
    //
    public int longestSubstring(String s) {
        int maxLen = 0;
        Map<Character, Integer> window = new HashMap<>();
        int l = 0, r = 0;
        while (r < s.length()) {
            window.compute(s.charAt(r),(c,t)->t==null?1:++t);
            if (window.size()>2) {
                window.compute(s.charAt(l),(c,t)-> t!=null&&--t==0?null:t);
                l++;
            }
            maxLen = Math.max(maxLen, 1+r-l);
            r++;
        }
        return maxLen;
    }
}
