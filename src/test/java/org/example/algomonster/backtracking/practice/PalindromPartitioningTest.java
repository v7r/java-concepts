package org.example.algomonster.backtracking.practice;

import junit.framework.TestCase;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Tests for PalindromPartitioning.palindromicPartitions
 * These tests follow the problem statement and include edge cases to challenge the implementation.
 * Do NOT modify production logic.
 */
public class PalindromPartitioningTest extends TestCase {

    public void testEmptyStringReturnsSingleEmptyPartition() {
        List<List<String>> res = PalindromPartitioning.palindromicPartitions("");
        assertNotNull(res);
        assertEquals(1, res.size());
        assertEquals(0, res.get(0).size());
    }

    public void testExampleAab() {
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a","a","b"),
                Arrays.asList("aa","b")
        );
        List<List<String>> actual = PalindromPartitioning.palindromicPartitions("aab");
        assertPartitionsEqualIgnoringOrder(expected, actual);
    }

    public void testAllSameCharactersAaa() {
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a","a","a"),
                Arrays.asList("a","aa"),
                Arrays.asList("aa","a"),
                Arrays.asList("aaa")
        );
        List<List<String>> actual = PalindromPartitioning.palindromicPartitions("aaa");
        assertPartitionsEqualIgnoringOrder(expected, actual);
    }

    public void testOddPalindromeAba() {
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a","b","a"),
                Arrays.asList("aba")
        );
        List<List<String>> actual = PalindromPartitioning.palindromicPartitions("aba");
        assertPartitionsEqualIgnoringOrder(expected, actual);
    }

    public void testEvenPalindromeAbba() {
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a","b","b","a"),
                Arrays.asList("a","bb","a"),
                Arrays.asList("abba")
        );
        List<List<String>> actual = PalindromPartitioning.palindromicPartitions("abba");
        assertPartitionsEqualIgnoringOrder(expected, actual);
    }

    public void testReturnedListIsIndependentAcrossCalls() {
        List<List<String>> first = PalindromPartitioning.palindromicPartitions("aab");
        // mutate first result
        if (!first.isEmpty() && !first.get(0).isEmpty()) {
            first.get(0).add("zzz");
        }
        List<List<String>> second = PalindromPartitioning.palindromicPartitions("aab");
        // second should not contain the mutation
        for (List<String> p : second) {
            assertFalse("Second call should not contain mutated marker", p.contains("zzz"));
        }
    }

    public void testNullInputThrowsNPE() {
        try {
            PalindromPartitioning.palindromicPartitions(null);
            fail("Expected NullPointerException for null input");
        } catch (NullPointerException expected) {
            // production code dereferences s and will throw NPE
        }
    }

    // Helper: compare partitions ignoring order of outer list, but preserve order inside each partition
    private void assertPartitionsEqualIgnoringOrder(List<List<String>> expected, List<List<String>> actual) {
        assertNotNull(actual);
        Set<String> es = expected.stream().map(list -> String.join("|", list)).collect(Collectors.toSet());
        Set<String> as = actual.stream().map(list -> String.join("|", list)).collect(Collectors.toSet());
        if (!es.equals(as)) {
            List<String> el = new ArrayList<>(es); Collections.sort(el);
            List<String> al = new ArrayList<>(as); Collections.sort(al);
            fail("Partitions differ.\nExpected: " + el + "\nActual:   " + al);
        }
    }
}

