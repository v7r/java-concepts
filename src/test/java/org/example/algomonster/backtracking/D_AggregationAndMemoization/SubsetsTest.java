package org.example.algomonster.backtracking.D_AggregationAndMemoization;

import junit.framework.TestCase;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Unit tests for Subsets.subsets
 * These tests exercise canonical examples and edge-cases to challenge the implementation.
 * Do NOT modify production logic.
 */
public class SubsetsTest extends TestCase {

    public void testEmptyInputReturnsOnlyEmptySubset() {
        List<List<Integer>> res = Subsets.subsets(Collections.emptyList());
        assertNotNull(res);
        assertEquals(1, res.size());
        assertTrue(res.get(0).isEmpty());
    }

    public void testSingleElementProducesTwoSubsets() {
        List<Integer> nums = Collections.singletonList(1);
        List<List<Integer>> res = Subsets.subsets(nums);
        assertNotNull(res);
        assertEquals(2, res.size());
        Set<String> set = toNormalizedSet(res);
        Set<String> expected = new HashSet<>(Arrays.asList("","1"));
        assertEquals(expected, set);
    }

    public void testExample123ProducesAllEightSubsets() {
        List<Integer> nums = Arrays.asList(1,2,3);
        List<List<Integer>> res = Subsets.subsets(nums);
        assertNotNull(res);
        assertEquals(8, res.size());
        Set<String> set = toNormalizedSet(res);
        Set<String> expected = new HashSet<>(Arrays.asList(
                "","1","2","3","1,2","1,3","2,3","1,2,3"
        ));
        assertEquals(expected, set);
    }

    public void testOrderOfInputDoesNotLoseSubsets() {
        List<Integer> a = Arrays.asList(1,2,3);
        List<Integer> b = Arrays.asList(3,2,1);
        Set<String> sa = toNormalizedSet(Subsets.subsets(a));
        Set<String> sb = toNormalizedSet(Subsets.subsets(b));
        // When normalized by sorting elements inside each subset, the produced sets should match
        assertEquals(sa, sb);
    }

    public void testNoDuplicateSubsets() {
        List<Integer> nums = Arrays.asList(1,2,3);
        List<List<Integer>> res = Subsets.subsets(nums);
        Set<String> set = toNormalizedSet(res);
        assertEquals("Expected no duplicate subsets", set.size(), res.size());
    }

    public void testReturnedListIndependenceBetweenCalls() {
        List<Integer> nums = Arrays.asList(1,2);
        List<List<Integer>> first = Subsets.subsets(nums);
        // mutate first result
        if (!first.isEmpty() && !first.get(0).isEmpty()) first.get(0).add(999);
        List<List<Integer>> second = Subsets.subsets(nums);
        Set<String> s2 = toNormalizedSet(second);
        assertFalse("Subsequent call affected by mutation to previous return", s2.contains("999"));
    }

    // Helper: normalize each subset by sorting its elements and joining by comma; empty subset -> ""
    private Set<String> toNormalizedSet(List<List<Integer>> list) {
        if (list == null) return Collections.emptySet();
        Set<String> out = new HashSet<>();
        for (List<Integer> l : list) {
            List<Integer> copy = new ArrayList<>(l);
            Collections.sort(copy);
            String key = copy.stream().map(String::valueOf).collect(Collectors.joining(","));
            out.add(key);
        }
        return out;
    }
}

