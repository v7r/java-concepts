package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Random;

public class PrefixSumMaxSubArrayTest extends TestCase {

    private int bruteForce(int[] nums) {
        int n = nums.length;
        int best = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                best = Math.max(best, sum);
            }
        }
        return best;
    }

    public void testExamples() {
        PrefixSumMaxSubArray solver = new PrefixSumMaxSubArray();
        assertEquals(6, solver.maxSubArraySum(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        assertEquals(1, solver.maxSubArraySum(new int[]{1}));
        assertEquals(23, solver.maxSubArraySum(new int[]{5,4,-1,7,8}));
    }

    public void testAllNegative() {
        PrefixSumMaxSubArray solver = new PrefixSumMaxSubArray();
        assertEquals(-1, solver.maxSubArraySum(new int[]{-2, -1, -3}));
        assertEquals(-5, solver.maxSubArraySum(new int[]{-5}));
    }

    public void testMixedAndZeros() {
        PrefixSumMaxSubArray solver = new PrefixSumMaxSubArray();
        assertEquals(5, solver.maxSubArraySum(new int[]{0, -1, 2, -1, 4, -5}));
        assertEquals(5, solver.maxSubArraySum(new int[]{5, -2, -3, 1, -1}));
    }

    public void testRandomizedAgainstBruteForce() {
        Random rnd = new Random(12345);
        PrefixSumMaxSubArray solver = new PrefixSumMaxSubArray();
        for (int t = 0; t < 500; t++) {
            int n = 1 + rnd.nextInt(20);
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) nums[i] = rnd.nextInt(41) - 20; // range -20..20
            int expected = bruteForce(nums);
            int actual = solver.maxSubArraySum(nums);
            assertEquals("Mismatch for nums", expected, actual);
        }
    }
}

