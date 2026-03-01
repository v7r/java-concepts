package org.example.algomonster.graphs_mst;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Tests for BuildMinimumFense.mstForest
 * These tests exercise the MST (Kruskal) logic across components and edge cases.
 * Do NOT modify production logic.
 */
public class BuildMinimumFenceTest extends TestCase {

    public void testExampleFromPrompt() {
        int trees = 5;
        List<List<Integer>> pairs = asListOfLists(
                Arrays.asList(1,2,1),
                Arrays.asList(2,4,2),
                Arrays.asList(3,5,3),
                Arrays.asList(4,4,4)
        );
        int res = BuildMinimumFence.mstForest(trees, pairs);
        assertEquals(6, res);
    }

    public void testDisconnectedComponentsSumOfMsts() {
        int trees = 6;
        // two components: 1-2-3 with edges 1 and 2 -> total 3; and 4-5-6 with edges 2 and 3 -> total 5. Grand total 8
        List<List<Integer>> pairs = asListOfLists(
                Arrays.asList(1,2,1),
                Arrays.asList(2,3,2),
                Arrays.asList(4,5,2),
                Arrays.asList(5,6,3)
        );
        int res = BuildMinimumFence.mstForest(trees, pairs);
        assertEquals(8, res);
    }

    public void testDuplicateEdgesDontDoubleCount() {
        int trees = 3;
        List<List<Integer>> pairs = asListOfLists(
                Arrays.asList(1,2,5),
                Arrays.asList(1,2,5),
                Arrays.asList(2,3,1)
        );
        // Should choose edges (2,3)=1 and (1,2)=5 => total 6
        int res = BuildMinimumFence.mstForest(trees, pairs);
        assertEquals(6, res);
    }

    public void testSelfLoopIgnoredAndNoPairs() {
        int trees = 2;
        List<List<Integer>> pairs = asListOfLists(
                Arrays.asList(1,1,100)
        );
        // Self-loop cannot connect nodes; no valid edges to connect 2 nodes -> sum 0
        int res = BuildMinimumFence.mstForest(trees, pairs);
        assertEquals(0, res);

        // No pairs at all
        res = BuildMinimumFence.mstForest(3, Collections.emptyList());
        assertEquals(0, res);
    }

    public void testTriangleChooseTwoSmallestEdges() {
        int trees = 3;
        List<List<Integer>> pairs = asListOfLists(
                Arrays.asList(1,2,10),
                Arrays.asList(2,3,1),
                Arrays.asList(1,3,2)
        );
        // choose edges weight 1 and 2 => total 3
        int res = BuildMinimumFence.mstForest(trees, pairs);
        assertEquals(3, res);
    }

    // helper to build list of lists
    private List<List<Integer>> asListOfLists(List<Integer>... rows) {
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> r : rows) out.add(new ArrayList<>(r));
        return out;
    }
}

