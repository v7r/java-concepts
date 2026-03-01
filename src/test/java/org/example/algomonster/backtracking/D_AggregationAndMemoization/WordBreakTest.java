package org.example.algomonster.backtracking.D_AggregationAndMemoization;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Tests for WordBreak.wordBreak to challenge the DFS+memoization logic.
 * Do not modify production code; tests assert current behavior (including thrown errors)
 * so regressions or defensive improvements will be caught.
 */
public class WordBreakTest extends TestCase {

    public void testAlgomonsterTrue() {
        List<String> dict = Arrays.asList("algo", "monster");
        assertTrue(WordBreak.wordBreak("algomonster", dict));
    }
    public void testAlgomonsterTrue01() {
        List<String> dict = Arrays.asList("a", "c");
        assertTrue(WordBreak.wordBreak("caa", dict));
    }

    public void testEmptyStringReturnsTrueEvenIfDictEmpty() {
        assertTrue(WordBreak.wordBreak("", Collections.<String>emptyList()));
    }

    public void testNullInputThrowsNPE() {
        try {
            WordBreak.wordBreak(null, Arrays.asList("a"));
            fail("Expected NullPointerException for null input");
        } catch (NullPointerException expected) {
            // expected - production code dereferences s
        }
    }

    public void testEmptyDictAndNonEmptyStringReturnsFalse() {
        assertFalse(WordBreak.wordBreak("a", Collections.<String>emptyList()));
    }

    public void testSimpleReusedWords() {
        List<String> dict = Arrays.asList("apple", "pen");
        assertTrue(WordBreak.wordBreak("applepenapple", dict));
    }

    public void testLeetCodeExample() {
        List<String> dict = Arrays.asList("leet", "code");
        assertTrue(WordBreak.wordBreak("leetcode", dict));
    }

    public void testCatsAndDogFalse() {
        List<String> dict = Arrays.asList("cats","dog","sand","and","cat");
        assertFalse(WordBreak.wordBreak("catsandog", dict));
    }

    public void testBacktrackingHeavyCaseReturnsFalse() {
        // This case forces many combinations; memoization should prune expensive branches.
        List<String> dict = Arrays.asList("a","aa","aaa","aaaa");
        assertFalse(WordBreak.wordBreak("aaaaab", dict));
    }

    public void testLargeCase() {
        // This case forces many combinations; memoization should prune expensive branches.
        List<String> dict = Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa","aaaaaaaa",
                                          "aaaaaaaaa", "aaaaaaaaaa");
        assertFalse(WordBreak.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", dict));
    }

    public void testEmptyStringInDictionaryCausesStackOverflow() {
        // If dictionary contains an empty string, production logic uses startsWith("") and will
        // recurse without advancing index leading to infinite recursion / StackOverflowError.
        try {
            WordBreak.wordBreak("a", Arrays.asList(""));
            fail("Expected StackOverflowError when dictionary contains an empty string");
        } catch (StackOverflowError expected) {
            // expected under current implementation; documents pathological input handling
        }
    }
}

