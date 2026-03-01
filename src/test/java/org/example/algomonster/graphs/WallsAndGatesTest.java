package org.example.algomonster.graphs;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests for WallsAndGates.mapGateDistances
 * These tests exercise the problem-statement behavior and edge cases:
 * - the example from the class comment
 * - grids with no gates (should remain INF)
 * - grids with all gates (all zeros)
 * - walls blocking propagation
 * - single-cell grids
 * - multiple gates picking the nearest distance
 *
 * Do NOT modify production logic.
 */
public class WallsAndGatesTest extends TestCase {

    private static final int INF = Integer.MAX_VALUE;

    public void testExampleFromComment() {
        List<List<Integer>> dungeon = arrayToList(new int[][]{
                {INF, -1, 0, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, -1},
                {0, -1, INF, INF}
        });

        List<List<Integer>> expected = arrayToList(new int[][]{
                {3, -1, 0, 1},
                {2, 2, 1, -1},
                {1, -1, 2, -1},
                {0, -1, 3, 4}
        });

        List<List<Integer>> out = WallsAndGates.mapGateDistances(dungeon);
        assertEquals(expected, out);
    }

    public void testNoGatesLeavesINF() {
        List<List<Integer>> dungeon = arrayToList(new int[][]{
                {INF, -1, INF},
                {INF, INF, -1}
        });
        List<List<Integer>> copy = deepCopy(dungeon);
        List<List<Integer>> out = WallsAndGates.mapGateDistances(dungeon);
        // No gates: output should be unchanged (all INF where INF originally)
        assertEquals(copy, out);
    }

    public void testAllGatesBecomeZero() {
        List<List<Integer>> dungeon = arrayToList(new int[][]{
                {0,0},
                {0,0}
        });
        List<List<Integer>> expected = arrayToList(new int[][]{
                {0,0},
                {0,0}
        });
        List<List<Integer>> out = WallsAndGates.mapGateDistances(dungeon);
        assertEquals(expected, out);
    }

    public void testWallsBlockPropagation() {
        List<List<Integer>> dungeon = arrayToList(new int[][]{
                {INF, -1, 0},
                {INF, -1, INF},
                {INF, -1, INF}
        });
        // The wall column isolates the right gate from the left cells; only reachable cells change
        List<List<Integer>> expected = arrayToList(new int[][]{
                {INF, -1, 0},
                {INF, -1, 1},
                {INF, -1, 2}
        });
        List<List<Integer>> out = WallsAndGates.mapGateDistances(dungeon);
        assertEquals(expected, out);
    }

    public void testSingleCellVariations() {
        List<List<Integer>> infCell = arrayToList(new int[][]{{INF}});
        List<List<Integer>> out1 = WallsAndGates.mapGateDistances(infCell);
        // no gates -> stays INF
        assertEquals(arrayToList(new int[][]{{INF}}), out1);

        List<List<Integer>> gateCell = arrayToList(new int[][]{{0}});
        List<List<Integer>> out2 = WallsAndGates.mapGateDistances(gateCell);
        assertEquals(arrayToList(new int[][]{{0}}), out2);

        List<List<Integer>> wallCell = arrayToList(new int[][]{{-1}});
        List<List<Integer>> out3 = WallsAndGates.mapGateDistances(wallCell);
        assertEquals(arrayToList(new int[][]{{-1}}), out3);
    }

    public void testMultipleGatesNearestDistance() {
        List<List<Integer>> dungeon = arrayToList(new int[][]{
                {0, INF, INF},
                {INF, INF, INF},
                {INF, INF, 0}
        });
        // Distances should be Manhattan distance to nearest gate
        List<List<Integer>> expected = arrayToList(new int[][]{
                {0, 1, 2},
                {1, 2, 1},
                {2, 1, 0}
        });
        List<List<Integer>> out = WallsAndGates.mapGateDistances(dungeon);
        assertEquals(expected, out);
    }

    // helpers
    private List<List<Integer>> arrayToList(int[][] arr) {
        List<List<Integer>> out = new ArrayList<>();
        for (int[] row : arr) {
            List<Integer> r = new ArrayList<>();
            for (int v : row) r.add(v);
            out.add(r);
        }
        return out;
    }

    private List<List<Integer>> deepCopy(List<List<Integer>> src) {
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> r : src) out.add(new ArrayList<>(r));
        return out;
    }
}

