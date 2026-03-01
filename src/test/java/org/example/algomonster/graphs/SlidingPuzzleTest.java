package org.example.algomonster.graphs;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests for SlidingPuzzle.numSteps
 * These tests follow the problem statement and include edge cases designed to challenge the implementation:
 * - canonical example
 * - already solved board
 * - one-move-away board
 * - known unsolvable configuration
 * - null input (expect NullPointerException under current implementation)
 *
 * Do NOT modify production logic; tests assert the correct behavior per the problem statement so
 * any bugs in the implementation will be exposed by failures.
 */
public class SlidingPuzzleTest extends TestCase {

    public void testExample1() {
        int[][] in = new int[][]{{4,1,3},{2,0,5}};

        List<List<Integer>> init = toList(in);
        int res = SlidingPuzzle.numSteps(init);
        // expected from problem statement
        assertEquals(5, res);
    }

    public void testAlreadySolvedReturnsZero() {
        int[][] in = new int[][]{{1,2,3},{4,5,0}};
        List<List<Integer>> init = toList(in);
        assertEquals(0, SlidingPuzzle.numSteps(init));
    }

    public void testOneMoveAway() {
        int[][] in = new int[][]{{1,2,3},{4,0,5}};
        List<List<Integer>> init = toList(in);
        assertEquals(1, SlidingPuzzle.numSteps(init));
    }

    public void testKnownUnsolvableReturnsMinusOne() {
        // Known unsolvable arrangement for 2x3 (parity): swapping two tiles makes it unsolvable
        int[][] in = new int[][]{{1,2,3},{5,4,0}};
        List<List<Integer>> init = toList(in);
        assertEquals(-1, SlidingPuzzle.numSteps(init));
    }
    public void testCase101() {
        // Known unsolvable arrangement for 2x3 (parity): swapping two tiles makes it unsolvable
        int[][] in = new int[][]{{3, 2, 4},{1, 5, 0}};
        List<List<Integer>> init = toList(in);
        assertEquals(14, SlidingPuzzle.numSteps(init));
    }

    public void testNullInputThrowsNPE() {
        try {
            SlidingPuzzle.numSteps(null);
            fail("Expected NullPointerException for null input");
        } catch (NullPointerException expected) {
            // expected behavior under current implementation
        }
    }

    // Helper to convert primitive 2D array to List<List<Integer>>
    private List<List<Integer>> toList(int[][] arr) {
        List<List<Integer>> out = new ArrayList<>();
        for (int r = 0; r < arr.length; r++) {
            List<Integer> row = new ArrayList<>();
            for (int c = 0; c < arr[r].length; c++) row.add(arr[r][c]);
            out.add(row);
        }
        return out;
    }
}

