package org.example.algomonster.graphs_directed;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

/**
 * Tests for AlienDictionary.alienOrder
 * These tests exercise canonical examples and edge cases described in the problem statement
 * and are intended to challenge known pitfalls in implementations (prefix invalidation, tie-breaking,
 * coverage of all letters).
 *
 * Do NOT modify production logic; tests assert the correct behavior per the problem statement.
 */
public class AlienDictionaryTest extends TestCase {

    public void testExample1() {
        List<String> words = Arrays.asList("wrt","wrf","er","ett","rftt");
        String out = AlienDictionary.alienOrder(words);
        assertEquals("wertf", out);
    }

    public void testExample2() {
        List<String> words = Arrays.asList("z","x");
        String out = AlienDictionary.alienOrder(words);
        assertEquals("zx", out);
    }

    public void testPrefixInvalidReturnsEmpty() {
        // According to the problem statement, if a longer word appears before its prefix, it's invalid.
        List<String> words = Arrays.asList("ab", "abc");
        String out = AlienDictionary.alienOrder(words);
        assertEquals("", out);
    }

    public void testSingleWordReturnsCharactersInSmallestLexicalOrder() {
        // With a single word there are no ordering constraints; the smallest valid output
        // (as required by the problem) is the characters in normal lexical order of the letters present.
        List<String> words = Arrays.asList("bca");
        String out = AlienDictionary.alienOrder(words);
        // smallest lexical ordering of {'b','c','a'} is "abc"
        assertEquals("abc", out);
    }

    public void testTwoWordsImplyingReverseOrder() {
        // Input words are sorted according to alien order. If the list is ["b","a"] that implies b < a
        // The expected order is therefore "ba" (not the normal ascii-sorted order "ab").
        List<String> words = Arrays.asList("b","a");
        String out = AlienDictionary.alienOrder(words);
        assertEquals("ba", out);
    }
}

