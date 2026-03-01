package org.example.algomonster.backtracking.practice;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Tests for GenerateParantheses.generateParantheses
 * These tests follow the problem statement (generate all well-formed parentheses for n pairs)
 * and include checks for correctness (valid parentheses), expected counts (Catalan numbers),
 * uniqueness, and basic properties to challenge incorrect backtracking/pruning logic.
 *
 * Do NOT modify production logic; tests assert the problem statement expectations.
 */
public class GenerateParanthesesTest extends TestCase {

    public void testN1() {
        List<String> res = GenerateParantheses.generateParantheses(1);
        assertNotNull(res);
        assertEquals(1, res.size());
        assertEquals("()", res.get(0));
        assertAllValid(res, 1);
        assertNoDuplicates(res);
    }

    public void testN2ContainsBoth() {
        List<String> res = GenerateParantheses.generateParantheses(2);
        assertNotNull(res);
        assertEquals(2, res.size());
        // both valid well-formed strings should be present, order is not important
        Set<String> expected = new HashSet<>();
        expected.add("(())");
        expected.add("()()");
        assertEquals(expected, new HashSet<>(res));
        assertAllValid(res, 2);
        assertNoDuplicates(res);
    }

    public void testN3CountAndContents() {
        List<String> res = GenerateParantheses.generateParantheses(3);
        assertNotNull(res);
        assertEquals(5, res.size()); // Catalan(3) = 5
        // Ensure all results are valid and unique
        assertAllValid(res, 3);
        assertNoDuplicates(res);
        // spot-check known values are present
        Set<String> set = new HashSet<>(res);
        assertTrue(set.contains("((()))"));
        assertTrue(set.contains("(()())"));
        assertTrue(set.contains("(())()"));
        assertTrue(set.contains("()(())"));
        assertTrue(set.contains("()()()"));
    }

    public void testN4CatalanCount() {
        List<String> res = GenerateParantheses.generateParantheses(4);
        assertNotNull(res);
        assertEquals(14, res.size()); // Catalan(4) = 14
        assertAllValid(res, 4);
        assertNoDuplicates(res);
    }

    // Helper validations
    private void assertAllValid(List<String> list, int n) {
        for (String s : list) {
            assertEquals("Each string must have length 2*n", 2 * n, s.length());
            int balance = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                assertTrue("Only '(' or ')' expected", c == '(' || c == ')');
                if (c == '(') balance++;
                else balance--;
                assertTrue("Balance went negative for string: " + s, balance >= 0);
            }
            assertEquals("Final balance should be zero for string: " + s, 0, balance);
        }
    }

    private void assertNoDuplicates(List<String> list) {
        Set<String> set = new HashSet<>(list);
        assertEquals("List contains duplicates", set.size(), list.size());
    }
}

