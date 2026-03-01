package org.example.algomonster.adv_datastructures.dsu;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Tests for NumberOfConnectedComponents.numberOfConnectedComponents
 * These tests exercise the DSU logic via the public API and include cases that often
 * reveal subtle DSU bugs: redundant merges, self-merge, disjoint merges, and the example from
 * the class documentation. Do NOT modify production logic.
 */
public class NumberOfConnectedComponentsTest extends TestCase {

    public void testExampleFromComment() {
        int n = 5;
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(2,3),
                Arrays.asList(1,3),
                Arrays.asList(0,4),
                Arrays.asList(0,4)
        );
        List<Integer> expected = Arrays.asList(4,3,3,2,2);
        List<Integer> out = NumberOfConnectedComponents.numberOfConnectedComponents(n, connections);
        assertEquals(expected, out);
    }

    public void testRedundantMergesDontDecreaseCount() {
        int n = 3;
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(0,1),
                Arrays.asList(0,1), // redundant
                Arrays.asList(1,2)
        );
        List<Integer> expected = Arrays.asList(2,2,1);
        List<Integer> out = NumberOfConnectedComponents.numberOfConnectedComponents(n, connections);
        assertEquals(expected, out);
    }

    public void testSelfMergeIgnored() {
        int n = 3;
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(0,0),
                Arrays.asList(1,2)
        );
        List<Integer> expected = Arrays.asList(3,2);
        List<Integer> out = NumberOfConnectedComponents.numberOfConnectedComponents(n, connections);
        assertEquals(expected, out);
    }

    public void testDisjointMerges() {
        int n = 6;
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(0,1),
                Arrays.asList(2,3),
                Arrays.asList(4,5)
        );
        List<Integer> expected = Arrays.asList(5,4,3);
        List<Integer> out = NumberOfConnectedComponents.numberOfConnectedComponents(n, connections);
        assertEquals(expected, out);
    }

    public void testAllBecomeOneComponent() {
        int n = 4;
        List<List<Integer>> connections = Arrays.asList(
                Arrays.asList(0,1),
                Arrays.asList(1,2),
                Arrays.asList(2,3)
        );
        List<Integer> expected = Arrays.asList(3,2,1);
        List<Integer> out = NumberOfConnectedComponents.numberOfConnectedComponents(n, connections);
        assertEquals(expected, out);
    }

    public void testNoConnectionsReturnsEmptyList() {
        int n = 4;
        List<List<Integer>> connections = Collections.emptyList();
        List<Integer> out = NumberOfConnectedComponents.numberOfConnectedComponents(n, connections);
        assertNotNull(out);
        assertTrue(out.isEmpty());
    }
}

