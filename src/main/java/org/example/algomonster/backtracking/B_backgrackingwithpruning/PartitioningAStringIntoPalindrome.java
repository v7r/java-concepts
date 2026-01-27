package org.example.algomonster.backtracking.B_backgrackingwithpruning;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, find all ways to partition it so that every substring is a palindrome. Return all possible
 * palindrome partitions of s.
 *
 * Examples
 * Example 1:
 * Input: aab
 * Output:
 *   [
 *   ["aa","b"],
 *   ["a","a","b"]
 *   ]
 *
 * Solution walkthrough for an example:
 *      <img src="../imgs/PartitioningAStringIntoPalindrome.png" />
 */
public class PartitioningAStringIntoPalindrome {

    private static boolean isPalindrome(String s) {
        int l=0,r=s.length()-1;
        while ( l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    private static void dfs(List<String> fragments, List<List<String>> parts, String s, int idx) {
        if (idx == s.length()) {
            parts.add(new ArrayList<>(fragments));
            return;
        }
        for (int i = idx; i < s.length(); i++) {
            String fragment = s.substring(idx,i+1);
            if (isPalindrome(fragment)) {
                fragments.add(fragment);
                dfs(fragments,parts,s,i+1);
                fragments.remove(fragments.size()-1);
            }
        }
    }


    public static List<List<String>> partition(String s) {
        List<List<String>> partitions = new ArrayList<>();
        dfs(new ArrayList<>(), partitions, s, 0);
        return partitions;
    }
}
