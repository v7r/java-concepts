package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LongestSubstringNonRepeatTest extends TestCase {

    private int bruteForce(String s) {
        int n = s.length();
        int best = 0;
        for (int i = 0; i < n; i++) {
            Set<Character> seen = new HashSet<>();
            int len = 0;
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                if (seen.contains(c)) break;
                seen.add(c);
                len++;
            }
            best = Math.max(best, len);
        }
        return best;
    }

    public void testExamplesFromComment() {
        assertEquals(3, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters("abccabcabcc"));
        assertEquals(2, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters("aaaabaaa"));
    }

    public void testClassicLeetCodeExamples() {
        assertEquals(3, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters("abcabcbb"));
        assertEquals(1, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters("bbbbb"));
        assertEquals(3, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters("pwwkew"));
    }

    public void testEdgeCases() {
        assertEquals(0, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters(""));
        assertEquals(1, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters(" ")); // single space
        assertEquals(2, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters("au"));
        assertEquals(3, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters("dvdf"));
        assertEquals(2, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters("abba"));
        assertEquals(5, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters("tmmzuxt"));
    }

    public void testLongRepeatingAndUniquePatterns() {
        assertEquals(1, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters("aaaaaaaa"));
        assertEquals(4, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters("abcd"));
        assertEquals(3, LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters("abca"));
    }

    public void testRandomizedBruteForceComparison() {
        Random rnd = new Random(12345);
        final int trials = 200;
        final int maxLen = 20;
        final String alphabet = "abcde"; // small alphabet to create many repeats
        for (int t = 0; t < trials; t++) {
            int len = rnd.nextInt(maxLen + 1);
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) sb.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
            String s = sb.toString();
            int expected = bruteForce(s);
            int actual = LongestSubstringNonRepeat.longestSubstringWithoutRepeatingCharacters(s);
            assertEquals("Mismatch for s='" + s + "'", expected, actual);
        }
    }
}

