package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Random;

public class PalindromeCheckByDeletingAtmostOneCharTest extends TestCase {

    private boolean bruteForce(String s) {
        if (s == null) return true;
        int n = s.length();
        // If original is palindrome -> true
        if (isPal(s, 0, n - 1)) return true;
        // try deleting one char at each position
        for (int i = 0; i < n; i++) {
            if (isPal(s, 0, i - 1) && isPal(s, i + 1, n - 1)) return true;
            // alternatively check substring removal
            if (isPal(s, 0, i - 1) || isPal(s, i + 1, n - 1)) return true;
        }
        return false;
    }

    private boolean isPal(String s, int l, int r) {
        while (l < r) {
            if (l < 0 || r >= s.length()) return true; // empty substring is palindrome
            if (s.charAt(l) != s.charAt(r)) return false;
            l++; r--;
        }
        return true;
    }

    public void testBasicPalindromes() {
        PalindromeCheckByDeletingAtmostOneChar solver = new PalindromeCheckByDeletingAtmostOneChar();
        assertTrue(solver.validPalindrome("aba"));
        assertTrue(solver.validPalindrome("a"));
        assertTrue(solver.validPalindrome(""));
        assertTrue(solver.validPalindrome("aa"));
    }

    public void testDeletionNeeded() {
        PalindromeCheckByDeletingAtmostOneChar solver = new PalindromeCheckByDeletingAtmostOneChar();
        assertTrue(solver.validPalindrome("caba")); // delete 'c'
        assertTrue(solver.validPalindrome("ab"));   // delete either char
        assertTrue(solver.validPalindrome("abbaa")); // delete one of the 'a' at start
    }

    public void testNotPossible() {
        PalindromeCheckByDeletingAtmostOneChar solver = new PalindromeCheckByDeletingAtmostOneChar();
        assertFalse(solver.validPalindrome("abc"));
        assertFalse(solver.validPalindrome("abcdef"));
    }

    public void testTrickyPatterns() {
        PalindromeCheckByDeletingAtmostOneChar solver = new PalindromeCheckByDeletingAtmostOneChar();
        assertTrue(solver.validPalindrome("eedede"));
        assertTrue(solver.validPalindrome("deeee"));
        assertTrue(solver.validPalindrome("cbbcc"));
    }

    public void testRandomAgainstBruteForce() {
        PalindromeCheckByDeletingAtmostOneChar solver = new PalindromeCheckByDeletingAtmostOneChar();
        Random rnd = new Random(1234);
        final String alphabet = "abac"; // small alphabet to create repeats and conflicts
        for (int t = 0; t < 500; t++) {
            int len = rnd.nextInt(20);
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) sb.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
            String s = sb.toString();
            boolean expected = bruteForce(s);
            boolean actual = solver.validPalindrome(s);
            assertEquals("Mismatch for s='" + s + "'", expected, actual);
        }
    }
}

