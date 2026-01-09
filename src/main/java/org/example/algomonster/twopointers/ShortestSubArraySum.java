package org.example.algomonster.twopointers;

import java.util.List;

/**
 * Let's continue on finding the sum of subarrays. This time given a positive integer array nums, we want to find the
 * length of the shortest subarray such that the subarray sum is at least target. Recall the same example with
 * input nums = [1, 4, 1, 7, 3, 0, 2, 5] and target = 10, then the smallest window with the sum >= 10 is [7, 3]
 * with length 2. So the output is 2.
 *
 * We'll assume for this problem that it's guaranteed target will not exceed the sum of all elements in nums.
 */
public class ShortestSubArraySum {
    public static int subarraySumShortest(List<Integer> nums, int target) {
        int length = Integer.MAX_VALUE;
        int sum = 0, l = 0, r = 0;
        while (r < nums.size()) {
            sum += nums.get(r);
            while (sum >= target) {
                length = Math.min(length, 1 + r - l);
                sum -= nums.get(l);
                l++;
            }
            r++;
        }
        if (length == Integer.MAX_VALUE) {
            return 0;
        }
        return length;
    }
}
