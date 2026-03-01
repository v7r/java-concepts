package org.example.algomonster.graphs_practice;

import junit.framework.TestCase;

import java.util.*;

/**
 * Tests for DirectedCycleWithPath.findCycle
 * These tests cover typical and edge cases to challenge the DFS-based cycle detection and path reconstruction.
 * Do NOT modify production logic; tests assert current behavior (including thrown exceptions) so bugs are exposed.
 */
public class DirectedCycleWithPathTest extends TestCase {

    public void testSimpleCycleABC1() {
        Map<Character, List<Character>> graph = new LinkedHashMap<>();
        graph.put('A', Arrays.asList('B'));
        graph.put('B', Arrays.asList('C','D'));
        graph.put('C', List.of());
        graph.put('D', Arrays.asList('E'));
        graph.put('E', Arrays.asList('B'));

        DirectedCycleWithPath solver = new DirectedCycleWithPath();
        List<Character> cycle = solver.findCycle(graph);
        List<Character> expected = Arrays.asList('B','D','E','B');
        assertEquals(expected, cycle);
    }

    public void testSimpleCycleABC() {
        Map<Character, List<Character>> graph = new LinkedHashMap<>();
        graph.put('A', Arrays.asList('B'));
        graph.put('B', Arrays.asList('C'));
        graph.put('C', Arrays.asList('A'));

        DirectedCycleWithPath solver = new DirectedCycleWithPath();
        List<Character> cycle = solver.findCycle(graph);
        // Expect the cycle [A,B,C,A]
        List<Character> expected = Arrays.asList('A','B','C','A');
        assertEquals(expected, cycle);
    }

    public void testSelfLoop() {
        Map<Character, List<Character>> graph = new LinkedHashMap<>();
        graph.put('A', Arrays.asList('A'));

        DirectedCycleWithPath solver = new DirectedCycleWithPath();
        List<Character> cycle = solver.findCycle(graph);
        // Self-loop should produce [A,A]
        List<Character> expected = Arrays.asList('A','A');
        assertEquals(expected, cycle);
    }

    public void testMultipleCyclesReturnsFirstEncountered() {
        // First component X<->Y then later A->B->C->A; solver should return the cycle from X/Y because of insertion order
        Map<Character, List<Character>> graph = new LinkedHashMap<>();
        graph.put('X', Arrays.asList('Y'));
        graph.put('Y', Arrays.asList('X'));
        graph.put('A', Arrays.asList('B'));
        graph.put('B', Arrays.asList('C'));
        graph.put('C', Arrays.asList('A'));

        DirectedCycleWithPath solver = new DirectedCycleWithPath();
        List<Character> cycle = solver.findCycle(graph);
        List<Character> expected = Arrays.asList('X','Y','X');
        assertEquals(expected, cycle);
    }

    public void testNoCycleReturnsNull() {
        Map<Character, List<Character>> graph = new LinkedHashMap<>();
        graph.put('A', Arrays.asList('B'));
        graph.put('B', Arrays.asList('C'));
        graph.put('C', Collections.emptyList());

        DirectedCycleWithPath solver = new DirectedCycleWithPath();
        List<Character> cycle = solver.findCycle(graph);
        assertNull(cycle);
    }

    public void testNeighborNotInKeysCausesNPE() {
        // Production implementation initializes state only for graph.keySet(); neighbors not present in the keyset
        // will cause state.get(nei) to return null and lead to NullPointerException during comparison.
        Map<Character, List<Character>> graph = new LinkedHashMap<>();
        graph.put('A', Arrays.asList('B')); // B is not present as a key

        DirectedCycleWithPath solver = new DirectedCycleWithPath();
        try {
            solver.findCycle(graph);
            fail("Expected NullPointerException when neighbor is not present in graph.keySet()");
        } catch (NullPointerException expected) {
            // expected - documents current behavior
        }
    }
}

