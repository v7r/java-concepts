package org.example.algomonster.backtracking.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitions.
 *
 * Example
 * Input: "aab"
 *
 * Output:
 * [
 *   ["a", "a", "b"],
 *   ["aa", "b"]
 * ]
 */
public class PalindromPartitioning {

    // s = "aab"
    // - add a
    //    |-- add a
    //         |-- add b * [a, a, b]
    //    |-- add ab -- NOT a palindrome - stop;
    // - add aa
    //    |-- add b * [aa, b]
    // - add aab -- NOT a palindrome - stop;


    private static boolean isPalindrome(String s) {
        if (s.isEmpty()) return false;
        if (s.length() == 1) return true;
        int l = 0, r = s.length()-1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    // s = "aab"
    //
    // -- call s0,e0,P[]: add 'a';
    //     |-- call s1,e1,p[a]: add 'a';
    //          |-- call s2,e2,p[a,a]: add 'b';
    //               |-- call s3,e3,p[a,a,b]: end==s.length; record [a,a,b]; *
    //          |-- call s2,e3,p[a,a]: end==s.length; record [a,a]; x
    //     |-- call s1,e2,p[a]: non-palindrome 'ab';
    //     |-- call s1,e3,p[a]: end==s.length; record[a]; x
    // -- call s0,e1,P[]: add 'aa';
    //     |--call s2,e2,P[aa]: add 'b';
    //          |--call s3,e3,P[aa,b]: end==s.length; record[aa,b]; *
    private static void dfs(int start, int end, List<String> path, String s, List<List<String>> ans) {
        if (end == s.length()) {
            if (start == end ) {
                ans.add(new ArrayList<>(path));
            }
            return;
        }
        String partition = s.substring(start, end+1);
        if (isPalindrome((partition))) {
            path.add(partition);
            dfs(end+1, end+1, path, s, ans);
            path.remove(path.size() - 1);
        }
        dfs(start, end+1, path, s, ans);

        // THOUGHT PROCESS:
        //          a
        //    a ab     [aa b]*
        // [a  a  b]*

        //Return the palindromes partitioning at this idx in s
        // Partition A: 0,idx
        // Partition B: idx length

        // if A is palindrome
        //     then find palindromes partitioning at 0 in B
        //     if returned partitions are non empty then join A and return the list.
        // Return the palindromes partitioning at idx+1 in S;

        // In other words:
        // Each recursion frame represents the decision of whether the current substring
        // s[start..end] should be committed as a partition or extended further.
    }

    private static void dfsChatGpt(int idx, List<String> path, String s, List<List<String>> ans) {
        if (idx == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < s.length(); i++) {
            if (isPalindrome(s.substring(idx, i + 1))) {
                path.add(s.substring(idx, i + 1));
                dfsChatGpt(i + 1, path, s, ans);
                path.remove(path.size() - 1);
            }
        }

        // - call idx0;p[]: add 'a'
        //     |-- call idx1;p[a]: add 'a'
        //           |-- call idx2;p[a,a]: add 'b'
        //                 |-- call idx3;p[a,a,b]: idx==s.length; record [a,a,b]
        //           |-- loop end;
        //     |-- call idx1;p[a]: loop i=2; ab is not a palindrome
        //     |-- loop end;
        // - call idx0;p[]: loop i=1; add 'aa'
        //     |- call idx2;p[aa]: add 'b'
        //          |- call idx3;p[aa,b]: idx==s.length; record [aa,b]
        //     |-- loop end;
        // - call idx0;p[]: loop i=2; 'aab' is not a palindrome;
        // - Loop end;
    }

    public static List<List<String>> palindromicPartitions(String s) {
        List<List<String>> ans = new ArrayList<>();
        //dfs(0,0,new ArrayList<>(), s, ans);
        dfsChatGpt(0, new ArrayList<>(), s, ans);
        return ans;
    }
}
