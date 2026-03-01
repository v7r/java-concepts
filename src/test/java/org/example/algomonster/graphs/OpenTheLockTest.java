package org.example.algomonster.graphs;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Tests for OpenTheLock.numSteps
 * These tests follow the problem statement and include edge cases intended to challenge the implementation.
 * Do NOT modify production logic.
 */
public class OpenTheLockTest extends TestCase {

    public void testExample0202() {
        List<String> trapped = Arrays.asList("0201","0101","0102","1212","2002");
        int steps = OpenTheLock.numSteps("0202", trapped);
        assertEquals(6, steps);
    }

    public void testTargetIsStart() {
        int steps = OpenTheLock.numSteps("0000", Collections.<String>emptyList());
        assertEquals(0, steps);
    }

    public void testOneMoveNeighbor() {
        // decrement last wheel 0 -> 9 should be one move
        int steps = OpenTheLock.numSteps("0009", Collections.<String>emptyList());
        assertEquals(1, steps);
    }

    public void testBlockedAroundStartLeadsToUnreachable() {
        // Block all immediate neighbors of "0000" so there is no first move
        List<String> blocked = Arrays.asList("1000","9000","0100","0900","0010","0090","0001","0009");
        int steps = OpenTheLock.numSteps("0202", blocked);
        assertEquals(-1, steps);
    }

    public void testUnrelatedDeadendsDoNotBlockSolution() {
        // Deadends that don't lie on the optimal path should not prevent reaching the target
        List<String> trapped = Arrays.asList("1111","2222","3333");
        int steps = OpenTheLock.numSteps("0001", trapped);
        assertEquals(1, steps);
    }

    public void testCase1() {
        // Deadends that don't lie on the optimal path should not prevent reaching the target
        List<String> trapped = Arrays.asList("0009", "0090", "0900", "9000");
        int steps = OpenTheLock.numSteps("9999", trapped);
        assertEquals(6, steps);
    }
}

