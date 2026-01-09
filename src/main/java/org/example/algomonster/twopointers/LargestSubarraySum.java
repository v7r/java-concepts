package org.example.algomonster.twopointers;

import java.util.List;

/**
 * Given an array (list) nums consisted of only non-negative integers, find the largest sum among all subarrays of
 * length k in nums.
 *
 * For example, if the input is nums = [1, 2, 3, 7, 4, 1], k = 3, then the output would be 14 as the largest length 3
 * subarray sum is given by [3, 7, 4] which sums to 14.
 *
 */
public class LargestSubarraySum {
    // [1, 2, 3, 7, 4, 1], k = 3
    public static int subarraySumFixed(List<Integer> nums, int k) {
        int maxSum = 0;
        if (nums.size() < k) return maxSum;
        for (int i=0; i<k; i++) {
            maxSum += nums.get(i);
        }
        int windowSum = maxSum;
        for (int i=k; i<nums.size(); i++) {
            int l = i-k;
            windowSum -= nums.get(l);
            windowSum += nums.get(i);
            maxSum = Math.max(windowSum, maxSum);
        }

        return maxSum;
    }
}
