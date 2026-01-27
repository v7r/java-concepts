package org.example.algomonster.backtracking;

import junit.framework.TestCase;
import org.example.algomonster.backtracking.B_backgrackingwithpruning.PartitioningAStringIntoPalindrome;

import java.util.*;

/**
 * Tests for PartitioningAStringIntoPalindrome.
 * These tests assert the correct partitions (by problem statement) and are intended
 * to expose incorrect backtracking/indexing logic in the implementation.
 * Do NOT modify production logic; tests expect correct behavior and will fail if
 * the implementation is buggy.
 */
public class PartitioningAStringIntoPalindromeTest extends TestCase {

    public void testEmptyStringReturnsOneEmptyPartition() {
        List<List<String>> result = PartitioningAStringIntoPalindrome.partition("");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(0, result.get(0).size());
    }

    public void testSingleCharacter() {
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a")
        );
        List<List<String>> result = PartitioningAStringIntoPalindrome.partition("a");
        assertPartitionsEqualIgnoringOrder(expected, result);
    }

    public void testExampleAab() {
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a","a","b"),
                Arrays.asList("aa","b")
        );
        List<List<String>> result = PartitioningAStringIntoPalindrome.partition("aab");
        // The canonical expected result for the problem is the two partitions above.
        assertPartitionsEqualIgnoringOrder(expected, result);
    }

    public void testAllSameCharacters() {
        // For "aaa" valid partitions are:
        // ["a","a","a"], ["a","aa"], ["aa","a"], ["aaa"]
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a","a","a"),
                Arrays.asList("a","aa"),
                Arrays.asList("aa","a"),
                Arrays.asList("aaa")
        );
        List<List<String>> result = PartitioningAStringIntoPalindrome.partition("aaa");
        assertPartitionsEqualIgnoringOrder(expected, result);
    }

    public void testEvenLengthPalindrome() {
        // "abba" partitions include ["a","b","b","a"], ["a","bb","a"], ["abba"]
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("a","b","b","a"),
                Arrays.asList("a","bb","a"),
                Arrays.asList("abba")
        );
        List<List<String>> result = PartitioningAStringIntoPalindrome.partition("abba");
        assertPartitionsEqualIgnoringOrder(expected, result);
    }

    public void testCaseSolution1() {
        // "cacac"
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("c","a","c","a","c"),
                Arrays.asList("c","aca","c"),
                Arrays.asList("cac","a","c"),
                Arrays.asList("c","a","cac"),
                Arrays.asList("cacac")
        );
        List<List<String>> result = PartitioningAStringIntoPalindrome.partition("cacac");
        assertPartitionsEqualIgnoringOrder(expected, result);
    }

    // Helper: compare partitions ignoring ordering of the outer list, but preserving each partition order
    private void assertPartitionsEqualIgnoringOrder(List<List<String>> expected, List<List<String>> actual) {
        assertNotNull(actual);
        // Convert each partition to a single string token so we can compare as sets
        Set<String> expectedSet = new HashSet<>();
        for (List<String> p : expected) expectedSet.add(String.join("|", p));

        Set<String> actualSet = new HashSet<>();
        for (List<String> p : actual) actualSet.add(String.join("|", p));

        // Provide helpful diagnostics on mismatch
        if (!expectedSet.equals(actualSet)) {
            fail("Partitions do not match.\nExpected: " + sortedSetString(expectedSet) + "\nActual: " + sortedSetString(actualSet));
        }
    }

    private String sortedSetString(Set<String> s) {
        List<String> l = new ArrayList<>(s);
        Collections.sort(l);
        return l.toString();
    }
}

