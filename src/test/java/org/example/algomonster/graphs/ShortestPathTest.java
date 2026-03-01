package org.example.algomonster.graphs;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Tests for ShortestPath.shortestPath
 * These tests exercise the problem statement examples and edge cases intended to
 * challenge the BFS implementation (visited handling, cycles, same-node case, line graphs).
 * Do NOT modify production logic.
 */
public class ShortestPathTest extends TestCase {

    public void testExampleGraph() {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1,2));
        graph.add(Arrays.asList(0,2,3));
        graph.add(Arrays.asList(0,1));
        graph.add(Arrays.asList(1));

        int dist = ShortestPath.shortestPath(graph, 0, 3);
        assertEquals(2, dist);
    }

    public void testDirectNeighbor() {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1));
        graph.add(Arrays.asList(0));

        assertEquals(1, ShortestPath.shortestPath(graph, 0, 1));
    }

    public void testSameNodeZeroDistance() {
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1));
        graph.add(Arrays.asList(0));

        // shortest path from a node to itself should be 0
        assertEquals(0, ShortestPath.shortestPath(graph, 0, 0));
    }

    public void testLineGraphLongerDistance() {
        // 0 - 1 - 2 - 3
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1));             // 0
        graph.add(Arrays.asList(0,2));           // 1
        graph.add(Arrays.asList(1,3));           // 2
        graph.add(Arrays.asList(2));             // 3

        assertEquals(3, ShortestPath.shortestPath(graph, 0, 3));
        assertEquals(2, ShortestPath.shortestPath(graph, 1, 3));
    }

    public void testGraphWithCycle() {
        // Graph with cycles to ensure BFS doesn't get stuck: a square with diagonal
        // 0 connected to 1
        // 1 connected to 0,2,3
        // 2 connected to 1,3
        // 3 connected to 1,2
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(Arrays.asList(1));
        graph.add(Arrays.asList(0,2,3));
        graph.add(Arrays.asList(1,3));
        graph.add(Arrays.asList(1,2));

        // shortest path from 0 to 3 is 2 (0->1->3)
        assertEquals(2, ShortestPath.shortestPath(graph, 0, 3));
    }

}

