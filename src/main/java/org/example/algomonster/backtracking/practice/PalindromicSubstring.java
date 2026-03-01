package org.example.algomonster.backtracking.practice;
import java.util.*;

/**
 * Given a string s, return the number of palindromic substrings in it.
 *
 * A string is a palindrome when it reads the same backward as forward.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2:
 *
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 *
 *
 * NOTE: I know this is a two pointer problem, but this is attempted as a dfs.
 *
 */
public class PalindromicSubstring {

    private static boolean isPalindrome(String s) {
        int l = 0, r = s.length()-1;
         while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    private static void dfs(String s, List<String> palindromes, int idx, Map<Integer, Boolean> visitedNode) {
        if (idx == s.length()) {
            return;
        }
        if (visitedNode.get(idx) != null && visitedNode.get(idx)) {
            return;
        }
        visitedNode.put(idx,Boolean.TRUE);

        for (int i = idx; i < s.length(); i++) {
            String fragment = s.substring(idx, i+1);
            if (isPalindrome(fragment)) {
                palindromes.add(fragment);
            }
            dfs(s,palindromes,i+1, visitedNode);
        }
    }

    public int countSubstrings(String s) {
        List<String> palindromes = new ArrayList<>();
        dfs(s,palindromes,0, new HashMap<>());
        return palindromes.size();
    }
}
