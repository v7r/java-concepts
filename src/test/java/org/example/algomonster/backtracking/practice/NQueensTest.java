package org.example.algomonster.backtracking.practice;

import junit.framework.TestCase;

import java.util.*;

/**
 * Tests for NQueens.arrangeQueens
 * These tests assert the problem-statement behavior and include checks that will
 * expose incorrect placement/backtracking logic (wrong marking of attack squares,
 * duplicate solutions, incorrect counts for n=2/n=3, etc.).
 * Do NOT modify production logic.
 */
public class NQueensTest extends TestCase {

    public void testNEqualsOne() {
        List<List<String>> sols = NQueens.arrangeQueens(1);
        assertNotNull(sols);
        assertEquals(1, sols.size());
        List<String> board = sols.get(0);
        assertEquals(1, board.size());
        assertEquals("Q", board.get(0));
        assertBoardIsValid(board);
    }

    public void testNEqualsTwoAndThree_NoSolutions() {
        List<List<String>> s2 = NQueens.arrangeQueens(2);
        assertNotNull(s2);
        assertEquals(0, s2.size());

        List<List<String>> s3 = NQueens.arrangeQueens(3);
        assertNotNull(s3);
        assertEquals(0, s3.size());
    }

    public void testNEqualsFour_TwoKnownSolutionsPresent() {
        List<List<String>> sols = NQueens.arrangeQueens(4);
        assertNotNull(sols);
        // Standard problem has 2 distinct solutions for n=4
        assertEquals(2, sols.size());

        Set<String> found = new HashSet<>();
        for (List<String> b : sols) found.add(String.join("\n", b));

        List<String> sol1 = Arrays.asList(
                ".Q..",
                "...Q",
                "Q...",
                "..Q."
        );
        List<String> sol2 = Arrays.asList(
                "..Q.",
                "Q...",
                "...Q",
                ".Q.."
        );
        Set<String> expected = new HashSet<>();
        expected.add(String.join("\n", sol1));
        expected.add(String.join("\n", sol2));

        assertEquals("Expected the two canonical 4-queen solutions", expected, found);

        // Also validate each returned board is structurally valid
        for (List<String> b : sols) {
            assertBoardIsValid(b);
        }
    }

    public void testReturnedListIndependence() {
        List<List<String>> first = NQueens.arrangeQueens(4);
        if (!first.isEmpty()) {
            // mutate the first solution's first row
            List<String> board = first.get(0);
            if (!board.isEmpty()) {
                board.set(0, "XXXX");
            }
        }
        List<List<String>> second = NQueens.arrangeQueens(4);
        // second should not contain the mutated marker
        for (List<String> b : second) {
            for (String row : b) {
                assertFalse("Returned boards should be independent between calls", row.contains("XXXX"));
            }
        }
    }

    // Helper that asserts a board is a valid n-queens placement: n rows of length n, exactly one Q per row,
    // exactly n Qs total, no two queens share the same column or diagonal.
    private void assertBoardIsValid(List<String> board) {
        assertNotNull(board);
        int n = board.size();
        assertTrue(n >= 1);
        int totalQs = 0;
        Set<Integer> cols = new HashSet<>();
        Set<Integer> diag1 = new HashSet<>(); // r - c
        Set<Integer> diag2 = new HashSet<>(); // r + c
        for (int r = 0; r < n; r++) {
            String row = board.get(r);
            assertEquals("Row length must be n", n, row.length());
            int rowQs = 0;
            for (int c = 0; c < n; c++) {
                char ch = row.charAt(c);
                assertTrue("Row must contain only 'Q' or '.'", ch == 'Q' || ch == '.');
                if (ch == 'Q') {
                    rowQs++;
                    totalQs++;
                    assertFalse("Two queens in same column", cols.contains(c));
                    cols.add(c);
                    int d1 = r - c;
                    int d2 = r + c;
                    assertFalse("Two queens share a diagonal d1", diag1.contains(d1));
                    assertFalse("Two queens share a diagonal d2", diag2.contains(d2));
                    diag1.add(d1);
                    diag2.add(d2);
                }
            }
            assertEquals("Each row must contain exactly one queen", 1, rowQs);
        }
        assertEquals("Total queens must equal n", n, totalQs);
    }
}

