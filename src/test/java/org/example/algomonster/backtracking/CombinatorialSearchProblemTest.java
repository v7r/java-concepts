package org.example.algomonster.backtracking;

import junit.framework.TestCase;
import org.example.algomonster.backtracking.A_combinatorialsearch.CombinatorialSearchProblem;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Tests for CombinatorialSearchProblem.letterCombination
 * These tests cover n=0, small values, moderate values, and properties for larger results.
 * Do not modify production logic.
 */
public class CombinatorialSearchProblemTest extends TestCase {

    public void testZeroReturnsSingleEmptyString() {
        List<String> result = CombinatorialSearchProblem.letterCombination(0);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("", result.get(0));
    }

    public void testOneReturnsAandBInOrder() {
        List<String> result = CombinatorialSearchProblem.letterCombination(1);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("a", result.get(0));
        assertEquals("b", result.get(1));
        // ensure lexicographic order
        List<String> sorted = new ArrayList<>(result);
        Collections.sort(sorted);
        assertEquals(sorted, result);
    }

    public void testTwoMatchesExample() {
        List<String> expected = Arrays.asList("aa", "ab", "ba", "bb");
        List<String> result = CombinatorialSearchProblem.letterCombination(2);
        assertEquals(expected, result);
    }

    public void testFourHasSixteenAndProperBounds() {
        List<String> result = CombinatorialSearchProblem.letterCombination(4);
        assertNotNull(result);
        assertEquals(16, result.size());
        // first and last in lexicographic order
        assertEquals("aaaa", result.get(0));
        assertEquals("bbbb", result.get(result.size() - 1));
        // ensure sorted
        List<String> sorted = new ArrayList<>(result);
        Collections.sort(sorted);
        assertEquals(sorted, result);
    }

    public void testTenProducesCorrectCountAndValidStrings() {
        List<String> result = CombinatorialSearchProblem.letterCombination(10);
        assertNotNull(result);
        assertEquals(1 << 10, result.size()); // 1024
        for (String s : result) {
            assertEquals(10, s.length());
            assertTrue(s.matches("^[ab]+$"));
        }
    }

    public void testRepeatedCallsReturnSameResults() {
        List<String> first = CombinatorialSearchProblem.letterCombination(3);
        List<String> second = CombinatorialSearchProblem.letterCombination(3);
        assertEquals(first, second);
        // modifying returned list should not affect subsequent calls (calls are independent)
        first.add("zzz");
        List<String> third = CombinatorialSearchProblem.letterCombination(3);
        assertEquals(second, third);
    }
}

