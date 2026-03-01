package org.example.algomonster.backtracking.practice;

import junit.framework.TestCase;

import java.util.*;

/**
 * Tests for NumbersWithSameConseqDifference.findNumbers
 * These tests follow the problem statement examples and include edge cases to challenge the implementation
 * (k = 0, k = 9, no leading zeros, returned list independence, and digit length checks).
 * Do NOT modify production logic.
 */
public class NumbersWithSameConseqDifferenceTest extends TestCase {

    public void testExampleN3K7() {
        List<Integer> actual = NumbersWithSameConseqDifference.findNumbers(3, 7);
        Set<Integer> expected = new HashSet<>(Arrays.asList(181,292,707,818,929));
        assertNotNull(actual);
        assertEquals(expected, new HashSet<>(actual));
        // verify each number has length 3 and doesn't start with 0
        for (Integer v : actual) {
            String s = String.valueOf(v);
            assertEquals(3, s.length());
            assertFalse(s.startsWith("0"));
        }
    }

    public void testExampleN2K1() {
        List<Integer> actual = NumbersWithSameConseqDifference.findNumbers(2, 1);
        Set<Integer> expected = new HashSet<>(Arrays.asList(
                10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98
        ));
        assertNotNull(actual);
        assertEquals(expected, new HashSet<>(actual));
        for (Integer v : actual) {
            String s = String.valueOf(v);
            assertEquals(2, s.length());
            assertFalse(s.startsWith("0"));
        }
    }

    public void testKZeroProducesRepeats() {
        // For k=0 and n=2 we expect 11,22,...,99
        List<Integer> actual = NumbersWithSameConseqDifference.findNumbers(2, 0);
        Set<Integer> expected = new HashSet<>();
        for (int d = 1; d <= 9; d++) expected.add(d*10 + d);
        assertEquals(expected, new HashSet<>(actual));
    }

    public void testKNineEdgeCase() {
        // For n=2, k=9 only 90 is valid (first digit cannot be 0)
        List<Integer> actual = NumbersWithSameConseqDifference.findNumbers(2, 9);
        Set<Integer> expected = new HashSet<>(Collections.singletonList(90));
        assertEquals(expected, new HashSet<>(actual));
    }

    public void testNoLeadingZeroConstraint() {
        // Ensure none of the returned numbers start with zero for various inputs
        int[][] cases = new int[][]{{2, 7},{3, 2},{4, 3}};
        for (int[] c : cases) {
            int n = c[0], k = c[1];
            List<Integer> actual = NumbersWithSameConseqDifference.findNumbers(n, k);
            assertNotNull(actual);
            for (Integer v : actual) {
                String s = String.valueOf(v);
                assertEquals(n, s.length());
                assertFalse("Leading zero found in " + s, s.startsWith("0"));
            }
        }
    }

    public void testReturnedListIndependenceBetweenCalls() {
        List<Integer> first = NumbersWithSameConseqDifference.findNumbers(2, 1);
        if (!first.isEmpty()) first.add(999);
        List<Integer> second = NumbersWithSameConseqDifference.findNumbers(2, 1);
        assertFalse("Subsequent call affected by mutation of previous result", second.contains(999));
    }
}

