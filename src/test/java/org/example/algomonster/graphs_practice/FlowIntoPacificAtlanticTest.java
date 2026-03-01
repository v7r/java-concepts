package org.example.algomonster.graphs_practice;

import junit.framework.TestCase;

import java.util.*;

/**
 * Tests for FlowIntoPacificAtlantic.pacificAtlantic
 * These tests follow the problem statement and include cases intended to surface common bugs
 * (equal-height traversal, correct identification of cells that can reach both oceans, null handling).
 * Do NOT modify production logic; tests assert the expected problem-statement behavior so
 * incorrect implementations will fail.
 */
public class FlowIntoPacificAtlanticTest extends TestCase {

    public void testExampleMatrix() {
        int[][] heights = new int[][]{
                {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}
        };
        Set<String> expected = new HashSet<>(Arrays.asList(
                "0,4","1,3","1,4","2,2","3,0","3,1","4,0"
        ));
        List<List<Integer>> out = FlowIntoPacificAtlantic.pacificAtlantic(heights);
        assertNotNull(out);
        Set<String> actual = toSet(out);
        assertEquals("Expected set of coordinates does not match", expected, actual);
        assertEquals(7, out.size());
    }

    public void testSingleCell() {
        int[][] heights = new int[][]{{1}};
        List<List<Integer>> out = FlowIntoPacificAtlantic.pacificAtlantic(heights);
        assertNotNull(out);
        assertEquals(1, out.size());
        assertEquals(Arrays.asList(0,0), out.get(0));
    }

    public void testAllEqualHeights2x2() {
        int[][] heights = new int[][]{{1,1},{1,1}};
        // With equal heights, all cells should be able to flow to both oceans
        Set<String> expected = new HashSet<>(Arrays.asList("0,0","0,1","1,0","1,1"));
        List<List<Integer>> out = FlowIntoPacificAtlantic.pacificAtlantic(heights);
        Set<String> actual = toSet(out);
        assertEquals(expected, actual);
    }

    public void testNullInputReturnsEmpty() {
        List<List<Integer>> out = FlowIntoPacificAtlantic.pacificAtlantic(null);
        assertNotNull(out);
        assertEquals(0, out.size());
    }

    // Helper to convert list of [r,c] pairs to a set of "r,c" strings for easy comparison
    private Set<String> toSet(List<List<Integer>> coords) {
        Set<String> s = new HashSet<>();
        for (List<Integer> p : coords) {
            s.add(p.get(0) + "," + p.get(1));
        }
        return s;
    }
}

