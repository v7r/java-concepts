package org.example.hackerrank;

import java.util.HashMap;
import java.util.Map;

/**
 * HackerRank problem
 * Ref: https://www.hackerrank.com/contests/clash-of-coders-3/challenges/palindrome-substring/problem
 * User: vamsidhar.my@gmail.com ( google oauth )
 *
 */
/**
 * Given a string of lowercase ASCII characters, find the number of distinct continuous palindromic sub-strings of it.
 *
 * Input: abacab
 *
 * Output: 6
 *
 * Explaination:
 * The substring palindromes of abacab are:
 * aba
 * aca
 * bacab
 * a
 * b
 * c
 */
public class PalindromeSubstring1 {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        //if (args.length == 0) return;
        //String s = args[0];
        //if (s == null || s.isEmpty()) return;
        String s = "abacab";
        Map<String,String> pset = new HashMap<>();
        int i = 0;
        while(i < s.length()) {
            int j = i + 1;
            while (j <= s.length()) {
                String sub = s.substring(i, j);
                // check if palindrome
                int x = 0;
                boolean isPalindrome = true;
                while (x <= sub.length() / 2) {
                    if (sub.charAt(x) != sub.charAt(sub.length() - 1 - x)) {
                        isPalindrome = false;
                        break;
                    }
                    x++;
                }
                if (isPalindrome) {
                    pset.computeIfAbsent(sub, s1 -> {
                        System.out.println(s1);
                        return s1;
                    });
                }
                j++;
            }
            i++;
        }
    }
}
