package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Random;

public class MinimumSwapsToGroupAllOnesTogetherTest extends TestCase {

    // Brute-force oracle: compute minimum zeros in any window of size totalOnes
    private int bruteForceMinSwaps(int[] data) {
        int n = data.length;
        int totalOnes = 0;
        for (int v : data) totalOnes += v;
        if (totalOnes <= 1) return 0; // no swaps needed for 0 or 1 one
        int minZeros = Integer.MAX_VALUE;
        for (int start = 0; start + totalOnes <= n; start++) {
            int ones = 0;
            for (int j = start; j < start + totalOnes; j++) ones += data[j];
            int zeros = totalOnes - ones;
            minZeros = Math.min(minZeros, zeros);
        }
        return minZeros == Integer.MAX_VALUE ? 0 : minZeros;
    }

    public void testExample1() {
        int[] data = {1,0,1,0,1};
        assertEquals(1, new MinimumSwapsToGroupAllOnesTogether().minSwaps(data));
    }

    public void testExample3FromComment() {
        int[] data = {1,0,1,0,1,0,0,1,1,0,1};
        assertEquals(3, new MinimumSwapsToGroupAllOnesTogether().minSwaps(data));
    }

    public void testAllZeros() {
        int[] data = {0,0,0,0,0};
        assertEquals(0, new MinimumSwapsToGroupAllOnesTogether().minSwaps(data));
    }

    public void testAllOnes() {
        int[] data = {1,1,1,1,1};
        assertEquals(0, new MinimumSwapsToGroupAllOnesTogether().minSwaps(data));
    }

    public void testSingleOne() {
        int[] data = {0,0,1,0};
        assertEquals(0, new MinimumSwapsToGroupAllOnesTogether().minSwaps(data));
    }

    public void testSingleZero() {
        int[] data = {0};
        assertEquals(0, new MinimumSwapsToGroupAllOnesTogether().minSwaps(data));
    }

    public void testEdgeWindowAtEnd() {
        int[] data = {0,1,0,1,1};
        int expected = bruteForceMinSwaps(data);
        assertEquals(expected, new MinimumSwapsToGroupAllOnesTogether().minSwaps(Arrays.copyOf(data, data.length)));
    }

    public void testRandomizedAgainstBruteForce() {
        Random rnd = new Random(12345);
        MinimumSwapsToGroupAllOnesTogether solver = new MinimumSwapsToGroupAllOnesTogether();
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(20);
            int[] data = new int[n];
            for (int i = 0; i < n; i++) data[i] = rnd.nextBoolean() ? 1 : 0;
            int expected = bruteForceMinSwaps(data);
            int actual = solver.minSwaps(Arrays.copyOf(data, data.length));
            assertEquals("Mismatch for data=" + Arrays.toString(data), expected, actual);
        }
    }
}

