package org.example.algomonster.twopointers;

import java.util.HashMap;

/**
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible
 * by k.
 *
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 *
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by k = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * Example 2:
 *
 * Input: nums = [5], k = 9
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * 2 <= k <= 104
 */
public class PrefixSumDivisibleByK {

    // sum divisible by k
    // sum of [i,j] % k = 0
    // (sum of [0,j] - sum of [0,i-1]) % k = 0
    // sum of [0,j] % k - sum of [0,i-1] % k = 0
    // sum of [0,j] % k = sum of [0,i-1] % k
    public int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> memo = new HashMap<>();
        memo.put(0,1);
        int count = 0;
        int prefixSum = 0;
        for (int i=0;i<nums.length;i++) {
            prefixSum += nums[i];

            // IMPORTANT:
            // there is a flaw in Java modulo operator for negative numbers
            // Mathematically -2 mod 5 is 3; however, java evaluates to -2;
            // you should normalize the remainder:
            //The "Code-Safe" Remainder Formula: remainder = ((sum % k) + k) % k

            // Option1;
            //int comp = prefixSum % k;
            //if (comp < 0) {
            //    comp = comp + k;
            //}

            //or

            // Option2;
            int comp = (k + (prefixSum % k)) % k;

            if (memo.containsKey(comp)) {
                count += memo.get(comp);
            }
            memo.compute(comp, (s,c) -> c==null?1:++c);
        }
        return count;
    }
}
