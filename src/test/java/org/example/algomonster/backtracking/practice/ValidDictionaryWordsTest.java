package org.example.algomonster.backtracking.practice;

import junit.framework.TestCase;

import java.util.*;

/**
 * Tests for ValidDictionaryWords.validWords
 * These tests follow the problem statement examples and include edge cases intended to
 * challenge the implementation (ordering, reuse of words, empty input, null input, and returned-list independence).
 * Do NOT modify production logic.
 */
public class ValidDictionaryWordsTest extends TestCase {

    public void testExampleCatsAndDog() {
        String s = "catsanddog";
        List<String> dict = Arrays.asList("cat","cats","and","sand","dog");
        List<String> expected = Arrays.asList("cats and dog","cat sand dog");
        List<String> actual = ValidDictionaryWords.validWords(s, dict);
        assertListEqualsIgnoringOrder(expected, actual);
    }

    public void testExamplePineapple() {
        String s = "pineapplepenapple";
        List<String> dict = Arrays.asList("apple","pen","applepen","pine","pineapple");
        List<String> expected = Arrays.asList(
                "pine apple pen apple",
                "pineapple pen apple",
                "pine applepen apple"
        );
        List<String> actual = ValidDictionaryWords.validWords(s, dict);
        assertListEqualsIgnoringOrder(expected, actual);
    }

    public void testNoSolutionReturnsEmptyList() {
        String s = "catsandog";
        List<String> dict = Arrays.asList("cats","dog","sand","and","cat");
        List<String> actual = ValidDictionaryWords.validWords(s, dict);
        assertNotNull(actual);
        assertEquals(0, actual.size());
    }

    public void testEmptyStringReturnsSingleEmptySentence() {
        String s = "";
        List<String> dict = Arrays.asList("a","b");
        List<String> actual = ValidDictionaryWords.validWords(s, dict);
        assertNotNull(actual);
        // Implementation records path when idx==s.length; with empty input it will add an empty string
        assertEquals(1, actual.size());
        assertEquals("", actual.get(0));
    }

    public void testReuseWordsProducesAllCombinations() {
        String s = "aaa";
        List<String> dict = Arrays.asList("a","aa");
        List<String> expected = Arrays.asList("a a a", "a aa", "aa a");
        List<String> actual = ValidDictionaryWords.validWords(s, dict);
        assertListEqualsIgnoringOrder(expected, actual);
    }

    public void testReturnedListIndependenceBetweenCalls() {
        String s = "catsanddog";
        List<String> dict = Arrays.asList("cat","cats","and","sand","dog");
        List<String> first = ValidDictionaryWords.validWords(s, dict);
        // mutate first
        if (!first.isEmpty()) first.add("__mutated__");
        List<String> second = ValidDictionaryWords.validWords(s, dict);
        for (String r : second) {
            assertFalse("Subsequent call affected by mutation of previous result", r.equals("__mutated__"));
        }
    }

    public void testNullInputThrowsNPE() {
        try {
            ValidDictionaryWords.validWords(null, Arrays.asList("a"));
            fail("Expected NullPointerException for null input");
        } catch (NullPointerException expected) {
            // expected - production code dereferences s.length()
        }
    }

    // helper to compare lists ignoring order
    private void assertListEqualsIgnoringOrder(List<String> expected, List<String> actual) {
        assertNotNull(actual);
        Set<String> es = new HashSet<>(expected);
        Set<String> as = new HashSet<>(actual);
        if (!es.equals(as)) {
            List<String> el = new ArrayList<>(es); Collections.sort(el);
            List<String> al = new ArrayList<>(as); Collections.sort(al);
            fail("Expected: " + el + "\nActual:   " + al);
        }
    }
}

