package org.example.algomonster.dp.practice;

import junit.framework.TestCase;

/**
 * Unit tests for LargestSquareOfOnes.
 * These tests cover the examples from the problem statement, rectangular and square all-ones matrices,
 * empty/null inputs, jagged inputs (should throw), and single-cell cases. They are designed to
 * challenge the implementation without modifying production logic.
 */
public class LargestSquareOfOnesTest extends TestCase {

    public void testExampleFromComment() {
        int[][] grid = new int[][]{
                {1,0,1,0,0},
                {1,0,1,1,1},
                {1,1,1,1,1},
                {1,0,0,1,0}
        };
        // Example from the class comment: largest square side = 2, area = 4
        assertEquals(2, LargestSquareOfOnes.largestSquareSide(grid));
        assertEquals(4, LargestSquareOfOnes.largestSquareArea(grid));
    }

    public void testSingleCellZero() {
        int[][] grid = new int[][]{{0}};
        assertEquals(0, LargestSquareOfOnes.largestSquareSide(grid));
        assertEquals(0, LargestSquareOfOnes.largestSquareArea(grid));
    }

    public void testSingleCellOne() {
        int[][] grid = new int[][]{{1}};
        assertEquals(1, LargestSquareOfOnes.largestSquareSide(grid));
        assertEquals(1, LargestSquareOfOnes.largestSquareArea(grid));
    }

    public void testAllOnesSquareMatrix() {
        int n = 5;
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) grid[i][j] = 1;
        assertEquals(n, LargestSquareOfOnes.largestSquareSide(grid));
        assertEquals(n * n, LargestSquareOfOnes.largestSquareArea(grid));
    }

    public void testAllOnesRectangularMatrix() {
        int[][] grid = new int[][]{
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1}
        };
        // min(rows,cols) = 3
        assertEquals(3, LargestSquareOfOnes.largestSquareSide(grid));
        assertEquals(9, LargestSquareOfOnes.largestSquareArea(grid));
    }

    public void testEmptyAndNullInputsReturnZero() {
        assertEquals(0, LargestSquareOfOnes.largestSquareSide(null));
        assertEquals(0, LargestSquareOfOnes.largestSquareSide(new int[0][0]));
        assertEquals(0, LargestSquareOfOnes.largestSquareArea(new int[1][0]));
    }

    public void testJaggedMatrixThrows() {
        int[][] grid = new int[2][];
        grid[0] = new int[]{1,1};
        grid[1] = new int[]{1}; // jagged
        try {
            LargestSquareOfOnes.largestSquareSide(grid);
            fail("Expected IllegalArgumentException for jagged (non-rectangular) input");
        } catch (IllegalArgumentException expected) {
            // expected
        }
    }

    public void testRowNullThrows() {
        int[][] grid = new int[2][];
        grid[0] = new int[]{1,1};
        grid[1] = null;
        try {
            LargestSquareOfOnes.largestSquareSide(grid);
            fail("Expected IllegalArgumentException when a row is null");
        } catch (IllegalArgumentException expected) {
            // expected
        }
    }

    public void testComplexPattern() {
        int[][] grid = {
                {1,1,1,0,1},
                {1,1,1,1,1},
                {1,1,1,1,1},
                {0,1,1,1,1}
        };
        // The largest filled square is side 3 (a 3x3 block of ones)
        assertEquals(3, LargestSquareOfOnes.largestSquareSide(grid));
        assertEquals(9, LargestSquareOfOnes.largestSquareArea(grid));
    }
}

