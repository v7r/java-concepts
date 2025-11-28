package org.example.hackerrank;

import java.util.Arrays;

/**
 *
 */
public class PalindromeLogics {

    public static void main(String[] args) {
        logicOne("aababaabce");
    }

    /**
     * Dynamic Programming method one:
     * This logic fills a stringLenght x stringLenght matrix of
     * boolean values, where each pair i,j is true when substring at i,j is a palindrome.
     *
     * The logic begins by filling the matrix with true values then
     * iterates i from the end and j from i+1 till end to take advantage of already computed substring palindrome state.
     * Each iteration determines whether substring i,j is palindrome or not. It does so just by comparing
     * s.charAt(i) == s.charAt(j) && element[i+1,j-1] is a palindrome.
     *
     * Checks if any substring of a given string is a palindrome.
     * All the substrings are indexed in a 2D matrix.
     *
     * Ref: https://algo.monster/liteproblems/2472
     *
     * eg: s = "aababaabce"
     */
    public static void logicOne(String s) {
        String inputString = s;
        int stringLength = s.length();
        boolean isPalindrome[][] = new boolean[stringLength][stringLength];

        // initialize all the elements with yes
        for (int i=0; i < stringLength; i++) {
            Arrays.fill(isPalindrome[i], true);
        }

        // forward iteration
        /*for (int i = 0; i < stringLength; i++) {
            for (int j = i - 1; j >= 0; j--) {
                isPalindrome[i][j] = inputString.charAt(i) == inputString.charAt(j) && isPalindrome[i-1][j+1];
                if (isPalindrome[i][j]) {
                    System.out.println(inputString.substring(j,i+1));
                }
            }
        }*/

        for (int i = stringLength - 1; i >= 0; i--) {
            for (int j = i + 1; j < stringLength; j++) {
                isPalindrome[i][j] = inputString.charAt(i) == inputString.charAt(j) && isPalindrome[i+1][j-1];
                if (isPalindrome[i][j]) {
                    System.out.println(inputString.substring(i,j+1));
                }
            }
        }

        // aababaabce
        // [9][9] = true;

        // ce: [8][9] & [9][8] = false

        // bc: [7][8] & [8][7] = false
        // bce: [7][9] & [8][8] = false

        // ab: [6][7] & [7][6] = false
        // abc: [6][8] & [7][7] = false
        // abce: [6][9] & [7][8] = false

        // aa: [5][6] & [6][5] = true
        // aab: [5][7] & [6][6] = false
        // aabc: [5][8] & [6][7] = false
        // aabce: [5][9] & [6][8] = false

        // ba: [4][5] & [5][4] = false
        // baa: [4][6] & [5][5] = false
        // baab: [4][7] & [5][6] = true
        // baabc: [4][8] & [5][7] = false
        // baabce: [4][9] & [5][8] = false

        // ab: [3][4] & [4][3] = false
        // aba: [3][5] & [4][4] = true
        // abaa: [3][6] & [4][5] = false
        // abaab: [3][7] & [4][6] = false
        // abaabc: [3][8] & [4][7] = false
        // abaabce: [3][9] & [4][8] = false

        // aababaabce

        // ba: [2][3] & [3][2] = false
        // bab: [2][4] & [3][3] = true
        // baba: [2][5] & [3][4] = false
        // babaa: [2][6] & [3][5] = false
        // babaab: [2][7] & [3][6] = false
        // babaabc: [2][8] & [3][7] = false
        // babaabce: [2][9] & [3][8] = false

        // ab: [1][2] & [2][1] = false
        // aba: [1][3] & [2][2] = true
        // abab: [1][4] & [2][3] = false
        // ababa: [1][5] & [2][4] = true
        // ababaa: [1][6] & [2][5] = false
        // ababaab: [1][7] & [2][6] = false
        // ababaabc: [1][8] & [2][7] = false
        // ababaabce: [1][9] & [2][8] = false

        // aa: [0][1] & [1][0] = true
        // aab: [0][2] & [1][1] = true
        // aaba: [0][3] & [1][2] = false
        // aabab: [0][4] & [1][3] = false
        // aababa: [0][5] & [1][4] = false
        // aababaa: [0][6] & [1][5] = true
        // aababaab: [0][7] & [1][6] = false
        // aababaabc: [0][8] & [1][7] = false
        // aababaabce: [0][9] & [1][8] = false


    }
}
