package org.example.hackerrank;

import java.util.Arrays;

/**
 * This was asked in Oracle Health Application Infrastructure interview
 * - 2024May11
 *
 * For a string s and an integer k, a selection of
 * substrings is valid if the following conditions
 * are met:
 *    • The length of every substring is greater than or equal to k
 *    • Each substring is a palindrome.
 *    • No two substrings overlap.
 *
 * Determine the maximum number of valid
 * substrings that can be formed from s.
 *
 * Notes:
 *    • A substring is a group of adjacent characters in a string.
 *    • A palindrome is a string that reads the same backward as forward.
 *
 * Example
 *    s= "aababaabce"
 *    k = 3
 *    a ababa abce
 *    a aba baab ce
 *
 * Any valid substring must be kor more
 * characters long. Either 1 or 2 non-overlapping
 * palindromes can be formed. Return 2, the
 * maximum number that can be formed.
 *
 * Ref: https://algo.monster/liteproblems/2472
 *
 */
public class NonOverlappingPalindromeSubString {
    // aababaabce

    public static void main(String[] args) {
        NonOverlappingPalindromeSubString sol = new NonOverlappingPalindromeSubString();
        String s = "aababaabce";
        int maxPalindromes = sol.maxPalindromes(s, 3);
        System.out.println("Max palindromes "+maxPalindromes);
    }


    private boolean[][] isPalindrome;
    private int[] memo;
    private String inputString;
    private int stringLength;
    private int minimumPalindromeLength;

    /**
     * Calculates the maximum number of palindromes that can be formed
     * from the input string, with each palindrome being at least 'k' characters long.
     *
     * @param s The input string from which palindromes are to be formed.
     * @param k The minimum length of each palindrome.
     * @return The maximum number of palindromes of length at least 'k'.
     */
    public int maxPalindromes(String s, int k) {
        stringLength = s.length();
        memo = new int[stringLength];
        inputString = s;
        minimumPalindromeLength = k;
        isPalindrome = new boolean[stringLength][stringLength];

        // Initialize the isPalindrome matrix and memo
        for (int i = 0; i < stringLength; ++i) {
            Arrays.fill(isPalindrome[i], true);
            memo[i] = -1;
        }

        // Populate the isPalindrome matrix by checking all substrings
        for (int i = stringLength - 1; i >= 0; --i) {
            for (int j = i + 1; j < stringLength; ++j) {
                isPalindrome[i][j] = inputString.charAt(i) == inputString.charAt(j) && isPalindrome[i + 1][j - 1];
            }
        }

        // Begin depth-first search from the first character
        return depthFirstSearch(0);
    }

    /**
     * Depth-first search to find the maximum number of palindromes
     * from a starting point within the string.
     *
     * @param start The starting index within the input string.
     * @return The maximum number of palindromes from this starting point.
     */
    private int depthFirstSearch(int start) {
        // If we've reached the end of the string, return 0 since no more palindromes can be formed
        if (start >= stringLength) {
            return 0;
        }

        // If we have already computed the value for this start index, return it
        if (memo[start] != -1) {
            return memo[start];
        }

        // Try skipping the current character and see what the result would be
        int maxPalindromes = depthFirstSearch(start + 1);

        // Try to find a palindrome starting at this index and see if we can
        // count more palindromes by including it
        for (int end = start + minimumPalindromeLength - 1; end < stringLength; ++end) {
            if (isPalindrome[start][end]) {
                maxPalindromes = Math.max(maxPalindromes, 1 + depthFirstSearch(end + 1));
            }
        }

        // Store the computed result in the memoization array
        memo[start] = maxPalindromes;
        return maxPalindromes;
    }
}






















