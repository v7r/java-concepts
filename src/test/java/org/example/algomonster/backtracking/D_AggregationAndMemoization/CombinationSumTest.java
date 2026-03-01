package org.example.algomonster.backtracking.D_AggregationAndMemoization;

import junit.framework.TestCase;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Tests for CombinationSum.combinationSum
 * These tests follow the problem statement examples and additional edge cases to challenge
 * the implementation without modifying production code.
 */
public class CombinationSumTest extends TestCase {

    public void testExample1() {
        List<Integer> candidates = Arrays.asList(2,3,6,7);
        int target = 7;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(2,2,3),
                Arrays.asList(7)
        );
        List<List<Integer>> actual = CombinationSum.combinationSum(candidates, target);
        assertCombinationsEqualIgnoringOrder(expected, actual);
    }

    public void testExample2() {
        List<Integer> candidates = Arrays.asList(2,3,5);
        int target = 8;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(2,2,2,2),
                Arrays.asList(2,3,3),
                Arrays.asList(3,5)
        );
        List<List<Integer>> actual = CombinationSum.combinationSum(candidates, target);
        assertCombinationsEqualIgnoringOrder(expected, actual);
    }

    public void testExample3Empty() {
        List<Integer> candidates = Arrays.asList(2);
        int target = 1;
        List<List<Integer>> expected = Collections.emptyList();
        List<List<Integer>> actual = CombinationSum.combinationSum(candidates, target);
        assertCombinationsEqualIgnoringOrder(expected, actual);
    }

    public void testExample4Single1() {
        List<Integer> candidates = Arrays.asList(1);
        int target = 1;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1)
        );
        List<List<Integer>> actual = CombinationSum.combinationSum(candidates, target);
        assertCombinationsEqualIgnoringOrder(expected, actual);
    }

    public void testExample5Single1Repeat() {
        List<Integer> candidates = Arrays.asList(1);
        int target = 2;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1,1)
        );
        List<List<Integer>> actual = CombinationSum.combinationSum(candidates, target);
        assertCombinationsEqualIgnoringOrder(expected, actual);
    }

    public void testUnsortedCandidatesStillProducesCorrectCombos() {
        List<Integer> candidates = Arrays.asList(7,3,2,6);
        int target = 7;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(2,2,3),
                Arrays.asList(7)
        );
        List<List<Integer>> actual = CombinationSum.combinationSum(candidates, target);
        assertCombinationsEqualIgnoringOrder(expected, actual);
    }

    public void testAdditionalCase() {
        List<Integer> candidates = Arrays.asList(2,3,5);
        int target = 7;
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(2,2,3),
                Arrays.asList(2,5)
        );
        List<List<Integer>> actual = CombinationSum.combinationSum(candidates, target);
        assertCombinationsEqualIgnoringOrder(expected, actual);
    }

    public void testReturnedListIndependence() {
        List<Integer> candidates = Arrays.asList(2,3);
        int target = 5;
        List<List<Integer>> first = CombinationSum.combinationSum(candidates, target);
        // Modify the returned result and ensure subsequent calls are not affected
        if (!first.isEmpty()) first.get(0).add(999);
        List<List<Integer>> second = CombinationSum.combinationSum(candidates, target);
        // second should not contain the artificial 999 element
        for (List<Integer> p : second) {
            for (Integer v : p) {
                assertFalse("Returned list appears to be shared between calls", v == 999);
            }
        }
    }

    // Helper to compare combinations ignoring order of the outer list, and ignoring ordering inside each combination
    private void assertCombinationsEqualIgnoringOrder(List<List<Integer>> expected, List<List<Integer>> actual) {
        Set<String> es = expected.stream()
                .map(list -> list.stream().sorted().map(String::valueOf).collect(Collectors.joining(",")))
                .collect(Collectors.toSet());
        Set<String> as = actual.stream()
                .map(list -> list.stream().sorted().map(String::valueOf).collect(Collectors.joining(",")))
                .collect(Collectors.toSet());
        if (!es.equals(as)) {
            fail("Expected combinations: " + new TreeSet<>(es) + " but got: " + new TreeSet<>(as));
        }
    }
}

