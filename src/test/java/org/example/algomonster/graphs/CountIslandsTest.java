package org.example.algomonster.graphs;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests for CountIslands.countNumberOfIslands
 * These tests cover canonical examples and edge cases to challenge the BFS implementation.
 * Do NOT modify production logic.
 */
public class CountIslandsTest extends TestCase {

    public void testAllOnesSingleIsland() {
        List<List<Integer>> grid = arrayToList(new int[][]{
                {1,1,1},
                {1,1,1},
                {1,1,1}
        });
        assertEquals(1, CountIslands.countNumberOfIslands(grid));
    }

    public void testAllZerosNoIslands() {
        List<List<Integer>> grid = arrayToList(new int[][]{
                {0,0,0},
                {0,0,0}
        });
        assertEquals(0, CountIslands.countNumberOfIslands(grid));
    }

    public void testMultipleIslandsTypical() {
        List<List<Integer>> grid = arrayToList(new int[][]{
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1}
        });
        assertEquals(3, CountIslands.countNumberOfIslands(grid));
    }

    public void testCheckerboardIsolatedOnes() {
        List<List<Integer>> grid = arrayToList(new int[][]{
                {1,0,1},
                {0,1,0},
                {1,0,1}
        });
        // 5 isolated ones (no horizontal/vertical connection)
        assertEquals(5, CountIslands.countNumberOfIslands(grid));
    }

    public void testDiagonalTouchDoesNotConnect() {
        List<List<Integer>> grid = arrayToList(new int[][]{
                {1,0},
                {0,1}
        });
        // diagonally adjacent should count as two separate islands
        assertEquals(2, CountIslands.countNumberOfIslands(grid));
    }

    public void testThinStripAndSparse() {
        List<List<Integer>> grid = arrayToList(new int[][]{
                {1,0,0,1,0},
                {0,0,1,0,0},
                {1,0,0,0,1}
        });
        // manual count: positions (0,0),(0,3),(1,2),(2,0),(2,4) => 5 islands
        assertEquals(5, CountIslands.countNumberOfIslands(grid));
    }

    // helper to convert primitive int[][] to List<List<Integer>>
    private List<List<Integer>> arrayToList(int[][] arr) {
        List<List<Integer>> out = new ArrayList<>();
        for (int[] row : arr) {
            List<Integer> r = new ArrayList<>();
            for (int v : row) r.add(v);
            out.add(r);
        }
        return out;
    }
}

