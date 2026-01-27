package org.example.algomonster.backtracking;

import junit.framework.TestCase;
import org.example.algomonster.backtracking.C_BackgrackingAdditionalStates.GenerateAllValidParentheses;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Tests for GenerateAllValidParentheses.generateParentheses
 * These tests exercise canonical examples and properties (counts = Catalan numbers, validity, uniqueness)
 * to challenge the implementation without modifying production code.
 */
public class GenerateAllValidParenthesesTest extends TestCase {

    public void testZeroReturnsEmptyString() {
        List<String> res = GenerateAllValidParentheses.generateParentheses(0);
        assertNotNull(res);
        assertEquals(1, res.size());
        assertEquals("", res.get(0));
    }

    public void testOneReturnsSinglePair() {
        List<String> res = GenerateAllValidParentheses.generateParentheses(1);
        assertNotNull(res);
        assertEquals(1, res.size());
        assertEquals("()", res.get(0));
        assertAllValid(res, 1);
    }

    public void testTwoMatchesExampleAndOrder() {
        List<String> res = GenerateAllValidParentheses.generateParentheses(2);
        assertNotNull(res);
        assertEquals(2, res.size());
        // typical generation order from DFS: (()) then ()()
        assertEquals("(())", res.get(0));
        assertEquals("()()", res.get(1));
        assertAllValid(res, 2);
    }

    public void testThreeMatchesExampleAndNoDuplicates() {
        List<String> res = GenerateAllValidParentheses.generateParentheses(3);
        assertNotNull(res);
        assertEquals(5, res.size());
        assertEquals("((()))", res.get(0));
        assertEquals("(()())", res.get(1));
        assertEquals("(())()", res.get(2));
        assertEquals("()(())", res.get(3));
        assertEquals("()()()", res.get(4));
        assertAllValid(res, 3);
        assertNoDuplicates(res);
    }

    public void testFourCatalanCountAndAllValid() {
        List<String> res = GenerateAllValidParentheses.generateParentheses(4);
        assertNotNull(res);
        // Catalan(4) = 14
        assertEquals(14, res.size());
        assertAllValid(res, 4);
        assertNoDuplicates(res);
    }

    // helper: validate each string is a balanced parentheses string of length 2*n
    private void assertAllValid(List<String> list, int n) {
        for (String s : list) {
            assertEquals(2 * n, s.length());
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
        Set<String> set = new HashSet<>();
        for (String s : list) set.add(s);
        assertEquals("List contains duplicates", set.size(), list.size());
    }
}

