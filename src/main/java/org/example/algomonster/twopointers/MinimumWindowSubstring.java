package org.example.algomonster.twopointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings, original and check, return the minimum substring of original such that each character in check,
 * including duplicates, are included in this substring. By "minimum", I mean the shortest substring. If two substrings
 * that satisfy the condition have the same length, the one that comes lexicographically first is smaller.
 *
 * Parameters
 * original: The original string.
 * check: The string to check if a window contains it.
 * Result
 * The smallest substring of original that satisfies the condition.
 * Examples
 * Example 1
 * Input: original = "cdbaebaecd", check = "abc"
 *
 * Output: baec
 *
 * Explanation: baec is the shortest substring of original that contains all of a, b, and c.
 *
 * Constraints
 * 1 <= len(check), len(original) <= 10^5
 * original and check both contain only uppercase and lowercase characters in English. The characters are case
 * sensitive.
 */
public class MinimumWindowSubstring {
    public static String getMinimumWindow(String original, String check) {
        if (original.length() < check.length()) return "";
        Map<Character, Integer> checkSet = new HashMap<>();
        Map<Character, Integer> windowSet = new HashMap<>();
        String result = "";
        for (int i=0;i<check.length();i++) {
            checkSet.compute(check.charAt(i),(c,t)->t==null?1:++t);
            windowSet.compute(original.charAt(i),(c,t)->t==null?1:++t);
        }
        if (windowSet.equals(checkSet)) {
            result = original.substring(0,check.length());
        }
        int l = 0, r = check.length();
        while (r < original.length()) {
            Character cc = original.charAt(r);
            r++;
            windowSet.compute(cc, (c,t)->t==null?1:++t);
            while (isSubString(windowSet,checkSet) && l <= r) {
                //if (result.isEmpty()) result = original.substring(l,r);
                result = minimum(result,original.substring(l,r));
                windowSet.compute(original.charAt(l),(c,t)->--t==0?null:t);
                l++;
            }
        }
        return result;
    }

    private static boolean isSubString(Map<Character,Integer> ws, Map<Character,Integer> cs) {
        if (ws.size() < cs.size()) return false;
        return !cs.entrySet().stream().anyMatch(e -> {
            int wc = ws.get(e.getKey()) != null ? ws.get(e.getKey()) : 0;
            return (e.getValue() - wc) > 0;
        });
    }

    private static String minimum(String s1, String s2) {
        if (s1.length() < s2.length()) return s1;
        else if (s1.length() > s2.length()) return s2;
        else {
            return s1.compareTo(s2) > 0 ? s2 : s1;
        }
    }
}


/**
 The above is my solution and below is the recommended solution by Algo.monster



 */
