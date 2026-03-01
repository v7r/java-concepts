package org.example.algomonster.graphs_weighted;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Tests for DijkstrasShortestPathAlgorithm.findShortestPath
 * These tests follow the problem statement and include cases that should reveal incorrect implementations:
 * - direct edge
 * - multi-hop path where an intermediate route is shorter than direct edge
 * - unreachable target
 * - source equals target
 * - cycles
 * - multiple edges between same nodes (pick smallest)
 *
 * Do NOT modify production logic; tests assert the expected correct shortest path values so
 * any bug in the implementation will be detected.
 */
public class DijkstrasShortestPathAlgorithmTest extends TestCase {

    private List<List<Entry<Integer, Integer>>> makeGraph(int n, int[][] edges) {
        List<List<Entry<Integer, Integer>>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph.get(u).add(Map.entry(v, w));
        }
        return graph;
    }

    public void testDirectEdge() {
        // 0 -> 1 (5)
        List<List<Entry<Integer,Integer>>> graph = makeGraph(2, new int[][]{{0,1,5}});
        assertEquals(5, DijkstrasShortestPathAlgorithm.findShortestPath(graph, 0, 1));
    }

    public void testMultiHopShorterThanDirect() {
        // 0->1 (2), 1->2 (3), 0->2 (10) => shortest 0->1->2 = 5
        List<List<Entry<Integer,Integer>>> graph = makeGraph(3, new int[][]{{0,1,2},{1,2,3},{0,2,10}});
        assertEquals(5, DijkstrasShortestPathAlgorithm.findShortestPath(graph, 0, 2));
    }

    public void testUnreachableReturnsMinusOne() {
        // two disconnected nodes
        List<List<Entry<Integer,Integer>>> graph = makeGraph(3, new int[][]{{0,1,1}});
        assertEquals(-1, DijkstrasShortestPathAlgorithm.findShortestPath(graph, 0, 2));
    }

    public void testSourceEqualsTargetZeroDistance() {
        List<List<Entry<Integer,Integer>>> graph = makeGraph(1, new int[][]{});
        assertEquals(0, DijkstrasShortestPathAlgorithm.findShortestPath(graph, 0, 0));
    }

    public void testCycleGraph() {
        // 0->1 (1), 1->0 (1), 1->2 (2) => 0->1->2 = 3
        List<List<Entry<Integer,Integer>>> graph = makeGraph(3, new int[][]{{0,1,1},{1,0,1},{1,2,2}});
        assertEquals(3, DijkstrasShortestPathAlgorithm.findShortestPath(graph, 0, 2));
    }

    public void testMultipleEdgesChooseSmaller() {
        // 0->1 weight 10, and 0->1 weight 3 (two parallel edges). algorithm should pick weight 3
        List<List<Entry<Integer,Integer>>> graph = makeGraph(2, new int[][]{{0,1,10},{0,1,3}});
        assertEquals(3, DijkstrasShortestPathAlgorithm.findShortestPath(graph, 0, 1));
    }
}

