package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Random;

public class PrefixSumEqualNumberOfZerosAndOnesTest extends TestCase {

    private int bruteForce(int[] nums) {
        int n = nums.length;
        int best = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0; // treat 0 -> -1, 1 -> +1
            for (int j = i; j < n; j++) {
                sum += nums[j] == 0 ? -1 : 1;
                if (sum == 0) best = Math.max(best, j - i + 1);
            }
        }
        return best;
    }

    public void testExampleSimplePair() {
        int[] nums = {0,1};
        assertEquals(2, bruteForce(nums));
        assertEquals(2, new PrefixSumEqualNumberOfZerosAndOnes().findMaxLength(nums));
    }

    public void testExampleThree() {
        int[] nums = {0,1,0};
        assertEquals(2, bruteForce(nums));
        assertEquals(2, new PrefixSumEqualNumberOfZerosAndOnes().findMaxLength(nums));
    }

    public void testLongerExampleFromComment() {
        int[] nums = {0,1,1,1,1,1,0,0,0};
        assertEquals(6, bruteForce(nums));
        assertEquals(6, new PrefixSumEqualNumberOfZerosAndOnes().findMaxLength(nums));
    }

    public void testAllZerosNoMatch() {
        int[] nums = {0,0,0,0};
        assertEquals(0, bruteForce(nums));
        assertEquals(0, new PrefixSumEqualNumberOfZerosAndOnes().findMaxLength(nums));
    }

    public void testBalancedEvenSplit() {
        int[] nums = {0,0,1,1};
        assertEquals(4, bruteForce(nums));
        assertEquals(4, new PrefixSumEqualNumberOfZerosAndOnes().findMaxLength(nums));
    }

    public void testMultipleBalancedWindows() {
        int[] nums = {0,1,0,1,0,1,1};
        assertEquals(bruteForce(nums), new PrefixSumEqualNumberOfZerosAndOnes().findMaxLength(nums));
    }

    public void testRandomizedAgainstBruteForce() {
        Random rnd = new Random(12345);
        PrefixSumEqualNumberOfZerosAndOnes solver = new PrefixSumEqualNumberOfZerosAndOnes();
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(20);
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) nums[i] = rnd.nextBoolean() ? 1 : 0;
            int expected = bruteForce(nums);
            int actual = solver.findMaxLength(nums);
            assertEquals("Mismatch for nums=" + Arrays.toString(nums), expected, actual);
        }
    }

    public void testEdgeCaseSingleOneOrZero() {
        assertEquals(0, new PrefixSumEqualNumberOfZerosAndOnes().findMaxLength(new int[]{0}));
        assertEquals(0, new PrefixSumEqualNumberOfZerosAndOnes().findMaxLength(new int[]{1}));
    }

    public void testAlternatingPattern() {
        int[] nums = {0,1,0,1,0,1,0,1};
        assertEquals(8, bruteForce(nums));
        assertEquals(8, new PrefixSumEqualNumberOfZerosAndOnes().findMaxLength(nums));
    }
}
