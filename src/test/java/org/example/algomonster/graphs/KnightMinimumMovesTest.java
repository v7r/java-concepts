package org.example.algomonster.graphs;

import junit.framework.TestCase;

/**
 * Tests for KnightMinimumMoves.getKnightShortestPath
 * These tests exercise canonical small coordinates, symmetry (negative coordinates),
 * and repeated call independence to challenge the BFS implementation.
 * Do NOT modify production logic.
 */
public class KnightMinimumMovesTest extends TestCase {

    public void testOriginIsZero() {
        assertEquals(0, KnightMinimumMoves.getKnightShortestPath(0, 0));
    }

    public void testOneMoveTargets() {
        // Knight can reach (1,2) and (2,1) in one move
        assertEquals(1, KnightMinimumMoves.getKnightShortestPath(1, 2));
        assertEquals(1, KnightMinimumMoves.getKnightShortestPath(2, 1));
    }

    public void testKnownThreeMoveTarget_OneZero() {
        // Known minimal moves: (1,0) requires 3 moves from (0,0)
        assertEquals(3, KnightMinimumMoves.getKnightShortestPath(1, 0));
    }

    public void testModerateDistance_ThreeThree() {
        // (3,3) is reachable in 2 moves: (0,0)->(1,2)->(3,3)
        assertEquals(2, KnightMinimumMoves.getKnightShortestPath(3, 3));
    }

    public void testSymmetryNegativeCoordinates() {
        // Negative coordinates should behave the same as positive due to symmetry of moves
        int pos = KnightMinimumMoves.getKnightShortestPath(4, 5);
        int neg = KnightMinimumMoves.getKnightShortestPath(-4, -5);
        assertEquals(pos, neg);
    }

    public void testReturnedValuesIndependentAcrossCalls() {
        int first = KnightMinimumMoves.getKnightShortestPath(3, 3);
        int second = KnightMinimumMoves.getKnightShortestPath(3, 3);
        assertEquals(first, second);
    }
}

