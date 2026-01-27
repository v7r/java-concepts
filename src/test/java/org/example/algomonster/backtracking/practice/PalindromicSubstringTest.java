package org.example.algomonster.backtracking.practice;

import junit.framework.TestCase;

/**
 * Unit tests for PalindromicSubstring.countSubstrings
 * These tests follow the problem statement and assert expected counts for several inputs,
 * including overlapping palindromes and larger palindromic spans to challenge the implementation.
 * Do not modify production code.
 */
public class PalindromicSubstringTest extends TestCase {

    public void testExample1_abc() {
        PalindromicSubstring solver = new PalindromicSubstring();
        int result = solver.countSubstrings("abc");
        // "a","b","c" => 3
        assertEquals(3, result);
    }

    public void testExample2_aaa() {
        PalindromicSubstring solver = new PalindromicSubstring();
        int result = solver.countSubstrings("aaa");
        // "a","a","a","aa","aa","aaa" => 6
        assertEquals(6, result);
    }

    public void testAba() {
        PalindromicSubstring solver = new PalindromicSubstring();
        int result = solver.countSubstrings("aba");
        // "a","b","a","aba" => 4
        assertEquals(4, result);
    }

    public void testAabaa_overlappingAndLarge() {
        PalindromicSubstring solver = new PalindromicSubstring();
        int result = solver.countSubstrings("aabaa");
        // palindromic substrings: a a b a a (5 singles) + aa (0-1) + aabaa (0-4) + aba (1-3) + aa (3-4) => 9
        assertEquals(9, result);
    }

    public void testSingleCharacter() {
        PalindromicSubstring solver = new PalindromicSubstring();
        assertEquals(1, solver.countSubstrings("x"));
    }

    public void testLongerMixedString() {
        PalindromicSubstring solver = new PalindromicSubstring();
        // "abccba" has: 6 singles + "cc" + "bccb" + "abccba" = 6 + 3 = 9
        int result = solver.countSubstrings("abccba");
        assertEquals(9, result);
    }
}

