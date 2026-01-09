package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Random;

public class PrefixSumDivisibleByKTest extends TestCase {

    private int bruteForce(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                int mod = sum % k;
                if (mod < 0) mod += k;
                if (mod == 0) count++;
            }
        }
        return count;
    }

    public void testExample1() {
        int[] nums = {4,5,0,-2,-3,1};
        int k = 5;
        // expected from problem statement: 7
        assertEquals(7, new PrefixSumDivisibleByK().subarraysDivByK(nums, k));
    }

    public void testExample2_singleNotDivisible() {
        int[] nums = {5};
        int k = 9;
        assertEquals(0, new PrefixSumDivisibleByK().subarraysDivByK(nums, k));
    }

    public void testAllZeros() {
        int n = 5;
        int[] nums = new int[n]; // all zeros
        int k = 3;
        // every subarray sum is 0, so number of subarrays is n*(n+1)/2
        int expected = n * (n + 1) / 2;
        assertEquals(expected, new PrefixSumDivisibleByK().subarraysDivByK(nums, k));
    }

    public void testNegativesAndPositives() {
        int[] nums = { -1, 2, 9, -6, 3 };
        int k = 4;
        int expected = bruteForce(nums, k);
        assertEquals(expected, new PrefixSumDivisibleByK().subarraysDivByK(nums, k));
    }

    public void testSingleElementDivisible() {
        int[] nums = { 8 };
        int k = 4;
        assertEquals(1, new PrefixSumDivisibleByK().subarraysDivByK(nums, k));
    }

    public void testLargeKWithSmallNums() {
        int[] nums = {1,2,3,4,5};
        int k = 100;
        int expected = bruteForce(nums, k);
        assertEquals(expected, new PrefixSumDivisibleByK().subarraysDivByK(nums, k));
    }

    public void testRandomComparisons() {
        Random rnd = new Random(123456);
        PrefixSumDivisibleByK solver = new PrefixSumDivisibleByK();
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(8);
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) nums[i] = rnd.nextInt(21) - 10; // range -10..10
            int k = 2 + rnd.nextInt(10); // 2..11
            int expected = bruteForce(nums, k);
            int actual = solver.subarraysDivByK(nums, k);
            assertEquals("Mismatch for nums=" + java.util.Arrays.toString(nums) + " k=" + k, expected, actual);
        }
    }

}

