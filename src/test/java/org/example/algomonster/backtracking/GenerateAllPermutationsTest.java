package org.example.algomonster.backtracking;

import junit.framework.TestCase;
import org.example.algomonster.backtracking.C_BackgrackingAdditionalStates.GenerateAllPermutations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Tests for GenerateAllPermutations.permutations
 * These tests cover typical inputs and edge-cases to challenge the implementation.
 * Do not modify production logic.
 */
public class GenerateAllPermutationsTest extends TestCase {

    public void testEmptyStringReturnsEmptyPermutation() {
        List<String> res = GenerateAllPermutations.permutations("");
        assertNotNull(res);
        assertEquals(1, res.size());
        assertEquals("", res.get(0));
    }

    public void testSingleCharacter() {
        List<String> res = GenerateAllPermutations.permutations("a");
        assertNotNull(res);
        assertEquals(1, res.size());
        assertEquals("a", res.get(0));
    }

    public void testABCProducesSixInExpectedOrder() {
        List<String> res = GenerateAllPermutations.permutations("abc");
        assertNotNull(res);
        assertEquals(6, res.size());
        String[] expected = new String[] {"abc","acb","bac","bca","cab","cba"};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], res.get(i));
        }
        // ensure uniqueness
        Set<String> set = new HashSet<>(res);
        assertEquals(res.size(), set.size());
    }

    public void testABCDCountAndUniqueness() {
        List<String> res = GenerateAllPermutations.permutations("abcd");
        assertNotNull(res);
        // 4! = 24
        assertEquals(24, res.size());
        // all permutations should be length 4 and unique
        Set<String> set = new HashSet<>();
        for (String s : res) {
            assertEquals(4, s.length());
            set.add(s);
        }
        assertEquals(24, set.size());
    }

    public void testReturnedListIsIndependentBetweenCalls() {
        List<String> first = GenerateAllPermutations.permutations("ab");
        first.add("zz");
        List<String> second = GenerateAllPermutations.permutations("ab");
        assertFalse("Modification of previous returned list should not affect subsequent calls", second.contains("zz"));
    }
}

