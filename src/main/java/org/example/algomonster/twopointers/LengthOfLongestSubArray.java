package org.example.algomonster.twopointers;

import java.util.List;

/**
 * Recall finding the largest size k subarray sum of an integer array in 'Largest Subarray Sum'. What if we don't need
 * the largest sum among all subarrays of fixed size k, but instead, we want to find the length of the longest subarray
 * with sum smaller than or equal to a target?
 *
 * Given an array of non-negative integers nums = [1, 6, 3, 1, 2, 4, 5] and target = 10, the longest subarray that does
 * not exceed 10 is [3, 1, 2, 4], so the output is 4.
 *
 */
public class LengthOfLongestSubArray {
    // MySolution
    public static int subarraySumLongestMySolution(List<Integer> nums, int target) {
        int maxLength = 0, l = 0, r = 0;
        int sum = 0;
        while (r < nums.size()) {
            sum += nums.get(r);
            if (sum <= target) {
                maxLength = Math.max(maxLength,1+r-l);
            } else {
                sum -= nums.get(l);
                l++;
            }
            r++;
        }
        return maxLength;
    }

    //[1, 6, 3, 1, 2, 4, 5] and target = 10
    // Solution from algo.monster website.
    public static int subarraySumLongest(List<Integer> nums, int target) {
        int maxLength = 0, l = 0, r = 0;
        int sum = 0;
        while (r < nums.size()) {
            sum += nums.get(r);
            while (sum > target) {
                sum -= nums.get(l);
                l++;
            }
            maxLength = Math.max(maxLength, 1 + r - l);
            r++;
        }
        return maxLength;
    }
}
