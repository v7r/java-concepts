package org.example.algomonster.backtracking.D_AggregationAndMemoization;

import junit.framework.TestCase;

/**
 * Tests for NumberOfWaysToDecodeAMessage.decodeWays
 * These tests follow the classic "decode ways" problem statement and include
 * edge cases (zeros, leading zeros, long repeated digits) intended to challenge
 * incorrect DFS/indexing/pruning logic in the current implementation.
 * Do NOT modify production logic.
 */
public class NumberOfWaysToDecodeAMessageTest extends TestCase {

    public void testExample18() {
        assertEquals(2, NumberOfWaysToDecodeAMessage.decodeWays("18"));
    }

    public void testExample123() {
        assertEquals(3, NumberOfWaysToDecodeAMessage.decodeWays("123"));
    }

    public void test226() {
        assertEquals(3, NumberOfWaysToDecodeAMessage.decodeWays("226"));
    }

    public void testLeadingZero06() {
        // "06" is invalid because numbers cannot start with '0'
        assertEquals(0, NumberOfWaysToDecodeAMessage.decodeWays("06"));
    }

    public void testTen() {
        // "10" -> only "J"
        assertEquals(1, NumberOfWaysToDecodeAMessage.decodeWays("10"));
    }

    public void testOneHundred() {
        // "100" -> no valid decodings: 10 0 (0 invalid standalone), 1 00 (00 invalid)
        assertEquals(0, NumberOfWaysToDecodeAMessage.decodeWays("100"));
    }

    public void testOneZeroOne() {
        // "101" -> 10|1 is valid, others invalid
        assertEquals(1, NumberOfWaysToDecodeAMessage.decodeWays("101"));
    }

    public void testLongOnes() {
        // "1111" -> Fibonacci sequence: for length 4, ways = 5
        assertEquals(5, NumberOfWaysToDecodeAMessage.decodeWays("1111"));
    }

    public void testOneOneZero() {
        // "110" -> only valid segmentation is 11|0? no; 1|10 -> valid => 1
        assertEquals(1, NumberOfWaysToDecodeAMessage.decodeWays("110"));
    }

    public void testEmptyStringReturnsOne() {
        // The implementation treats empty string as 1 way (empty decoding)
        assertEquals(1, NumberOfWaysToDecodeAMessage.decodeWays(""));
    }
}

