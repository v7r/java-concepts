package org.example.algomonster.graphs_practice;

import junit.framework.TestCase;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Tests for CriticalConnectionsInANetwork. These tests follow the problem statement and
 * include examples and edge cases intended to challenge incorrect logic (directed graph bug,
 * missing reverse edges, naive bridge detection, etc.).
 *
 * Do NOT modify production logic.
 */
public class CriticalConnectionsInANetworkTest extends TestCase {

    public void testExample1() {
        int n = 4;
        // [[1,0],[2,0],[3,2],[4,2],[4,3],[3,0],[4,0]]
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(1,0),
                Arrays.asList(2,0),
                Arrays.asList(3,2),
                Arrays.asList(4,2),
                Arrays.asList(4,3),
                Arrays.asList(3,0),
                Arrays.asList(4,0)
                );
        List<List<Integer>> out = new CriticalConnectionsInANetwork().criticalConnections(n, connections);
        Set<String> actual = normalizeEdgeSet(out);
        assertTrue("Expected result to contain [1,0]", actual.contains(normEdge(1,0)));
        assertEquals(1, actual.size());
    }

    public void testExample1101() {
        int n = 4;
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(0,1),
                Arrays.asList(1,2),
                Arrays.asList(2,0),
                Arrays.asList(1,3)
        );
        List<List<Integer>> out = new CriticalConnectionsInANetwork().criticalConnections(n, connections);
        Set<String> actual = normalizeEdgeSet(out);
        // expected critical edge is [1,3] (order-insensitive)
        assertTrue("Expected result to contain [1,3]", actual.contains(normEdge(1,3)));
        // There should be exactly one critical connection for this graph
        assertEquals(1, actual.size());
    }

    public void testExample2() {
        int n = 2;
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(0,1)
        );
        List<List<Integer>> out = new CriticalConnectionsInANetwork().criticalConnections(n, connections);
        Set<String> actual = normalizeEdgeSet(out);
        assertEquals(Collections.singleton(normEdge(0,1)), actual);
    }

    public void testLineGraphAllCritical() {
        // 0-1-2-3 : every edge is a bridge
        int n = 4;
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(0,1),
                Arrays.asList(1,2),
                Arrays.asList(2,3)
        );
        List<List<Integer>> out = new CriticalConnectionsInANetwork().criticalConnections(n, connections);
        Set<String> actual = normalizeEdgeSet(out);
        Set<String> expected = connections.stream().map(e -> normEdge(e.get(0), e.get(1))).collect(Collectors.toSet());
        assertEquals(expected, actual);
    }

    public void testTriangleNoCritical() {
        // 0-1-2-0 : no bridges
        int n = 3;
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(0,1),
                Arrays.asList(1,2),
                Arrays.asList(2,0)
        );
        List<List<Integer>> out = new CriticalConnectionsInANetwork().criticalConnections(n, connections);
        Set<String> actual = normalizeEdgeSet(out);
        assertTrue("Expected no critical connections in a triangle", actual.isEmpty());
    }

    public void testMixedComponents() {
        // component A: 0-1-2 (chain) -> all edges critical
        // component B: 3-4-5-3 (triangle) -> no critical
        int n = 6;
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(0,1),
                Arrays.asList(1,2),
                Arrays.asList(3,4),
                Arrays.asList(4,5),
                Arrays.asList(5,3)
        );
        List<List<Integer>> out = new CriticalConnectionsInANetwork().criticalConnections(n, connections);
        Set<String> actual = normalizeEdgeSet(out);
        Set<String> expected = new HashSet<>();
        expected.add(normEdge(0,1));
        expected.add(normEdge(1,2));
        assertEquals(expected, actual);
    }

    public void testLargeComponentSet() {
        int n = 1000;
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(List.of(1,0));
        connections.add(List.of(1,2));
        connections.add(List.of(2,3));
        connections.add(List.of(3,4));
        connections.add(List.of(1,4));
        connections.add(List.of(5,4));
        connections.add(List.of(5,6));
        connections.add(List.of(6,7));

        for (int i = 7; i < n; i++) {
            connections.add(List.of(i, i + 1));
        }
        connections.add(List.of(n, 7));

        List<List<Integer>> out = new CriticalConnectionsInANetwork().criticalConnections(n, connections);
        Set<String> actual = normalizeEdgeSet(out);
        Set<String> expected = new HashSet<>();
        expected.add(normEdge(0,1));
        expected.add(normEdge(4,5));
        expected.add(normEdge(6,5));
        expected.add(normEdge(6,7));
        assertEquals(expected, actual);
    }

    // Helpers
    private static String normEdge(int a, int b) {
        if (a <= b) return a + "," + b;
        return b + "," + a;
    }

    private static Set<String> normalizeEdgeSet(List<List<Integer>> edges) {
        if (edges == null) return Collections.emptySet();
        return edges.stream().map(e -> normEdge(e.get(0), e.get(1))).collect(Collectors.toSet());
    }
}

