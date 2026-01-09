package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LongestSubstringWithTwoDistinctCharsTest extends TestCase {

    // Brute-force correct implementation for comparison
    private int bruteForce(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int best = 0;
        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(s.charAt(j));
                if (set.size() > 2) break;
                best = Math.max(best, j - i + 1);
            }
        }
        return best;
    }

    public void testExample1() {
        LongestSubstringWithTwoDistinctChars solver = new LongestSubstringWithTwoDistinctChars();
        assertEquals(3, solver.longestSubstring("eceba"));
    }

    public void testExample2() {
        LongestSubstringWithTwoDistinctChars solver = new LongestSubstringWithTwoDistinctChars();
        assertEquals(5, solver.longestSubstring("ccaabbb"));
    }

    public void testAllSame() {
        LongestSubstringWithTwoDistinctChars solver = new LongestSubstringWithTwoDistinctChars();
        assertEquals(4, solver.longestSubstring("aaaa"));
    }

    public void testEmptyAndSingle() {
        LongestSubstringWithTwoDistinctChars solver = new LongestSubstringWithTwoDistinctChars();
        assertEquals(0, solver.longestSubstring(""));
        assertEquals(1, solver.longestSubstring("x"));
    }

    public void testAbaccc() {
        LongestSubstringWithTwoDistinctChars solver = new LongestSubstringWithTwoDistinctChars();
        assertEquals(4, solver.longestSubstring("abaccc"));
    }

    public void testClusters() {
        LongestSubstringWithTwoDistinctChars solver = new LongestSubstringWithTwoDistinctChars();
        assertEquals(7, solver.longestSubstring("aaabbbcccc"));
    }

    public void testManyTransitions() {
        LongestSubstringWithTwoDistinctChars solver = new LongestSubstringWithTwoDistinctChars();
        assertEquals(2, solver.longestSubstring("abcabc"));
    }

    public void testRandomizedAgainstBruteForce() {
        LongestSubstringWithTwoDistinctChars solver = new LongestSubstringWithTwoDistinctChars();
        Random rnd = new Random(123456);
        final String alphabet = "abcde";
        for (int t = 0; t < 500; t++) {
            int len = rnd.nextInt(30);
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) sb.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
            String s = sb.toString();
            int expected = bruteForce(s);
            int actual = solver.longestSubstring(s);
            assertEquals("Mismatch for s='" + s + "'", expected, actual);
        }
    }
}

