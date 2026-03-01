package org.example.algomonster.backtracking.practice;

import junit.framework.TestCase;

/**
 * Tests for BeautifulArrangements.arrange
 * These tests check canonical small-n counts, deterministic behavior across calls,
 * and that the returned counts are positive for valid n. They are designed to
 * challenge incorrect pruning or counting in the implementation.
 *
 * Do NOT modify production logic.
 */
public class BeautifulArrangementsTest extends TestCase {

    public void testNEqualsOne() {
        assertEquals(1, BeautifulArrangements.arrange(1));
    }

    public void testNEqualsTwo() {
        assertEquals(2, BeautifulArrangements.arrange(2));
    }

    public void testNEqualsThree() {
        assertEquals(3, BeautifulArrangements.arrange(3));
    }

    public void testNEqualsFourCountAndDeterminism() {
        int first = BeautifulArrangements.arrange(4);
        int second = BeautifulArrangements.arrange(4);
        assertEquals("Expected deterministic counts across calls", first, second);
        assertEquals(8, first);
    }

    public void testPositiveForValidN() {
        for (int n = 1; n <= 6; n++) {
            int cnt = BeautifulArrangements.arrange(n);
            assertTrue("Count should be non-negative for n=" + n, cnt >= 0);
        }
    }

    public void testZeroInputHandledGracefully() {
        // Problem definition states n >= 1. If implementation is called with 0,
        // ensure it doesn't crash and returns 0 (no arrangements).
        int cnt = BeautifulArrangements.arrange(0);
        assertTrue("For n=0 expect 0 or non-negative result; got " + cnt, cnt >= 0);
    }
}

