package org.example.algomonster.twopointers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an integer array nums, calculate the sum of elements between indices left and right (inclusive). You need to
 * answer multiple queries efficiently. You are required to preprocess the array so that each query can be answered in
 * constant time.
 *
 * Example: Input: nums = [1, 2, 3, 4], sumRange(1, 3). Output: 9.
 *
 * Your function should return 9 because the sum of elements from index 1 to 3 is 2 + 3 + 4 = 9.
 */
public class PrefixSumRangeSumQueryImmutable {

    public static int rangeSumQueryImmutable(List<Integer> nums, int left, int right) {
        Map<Integer,Integer> prefixSum = new HashMap<>();
        prefixSum.put(0,0);
        int sum = 0;
        for (int i=0;i<nums.size();i++) {
            sum += nums.get(i);
            if (right == i) {
                return sum - prefixSum.get(left);
            }
            prefixSum.put(i+1,sum);
        }
        return 0;
    }
}
