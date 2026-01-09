package org.example.algomonster.twopointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
 * Example 2:
 *
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * Example 3:
 *
 * Input: nums = [0,1,1,1,1,1,0,0,0]
 * Output: 6
 * Explanation: [1,1,1,0,0,0] is the longest contiguous subarray with equal number of 0 and 1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 */
public class PrefixSumEqualNumberOfZerosAndOnes {
    // [0,1]
    // preifxSum:
    // 0, 0, 1
    // sum:
    // 0, 1

    // [0,1,0]
    // prefixSum:
    // 0, 0, 1, 1
    // sum:
    // 0, 1, 1

    // {0,1,0,1,0,1,0,1}  max=8
    // PrefixSum:
    // 0,0,1,

    // [0,1]
    // -1,1
    //  0,-1,0
    //  -1,0
    // sum[i,j] = 0;
    // sum[0,j] - sum[0,i-1] = 0
    // sum[0,j] = sum[0,i-1]
    // length = j - i - 1
    // {0,1,0,1,0,1,0,1}
    // {-1,1,-1,1,-1,1,-1,1}
    // find largest subarray whose sum = 0
    // sum
    // {-1,0,-1,0,-1,0,-1,0}
    //
    public int findMaxLength(int[] nums) {
        Map<Integer,Integer> prefixSum = new HashMap<>();
        prefixSum.put(0,-1);
        int maxLength = 0;
        int sum = 0;
        for (int i=0;i<nums.length;i++) {
            // we treat 0 as -1 and 1 as 1. In order to find the equal number of 1's and 0's in a sub array
            // we find the subarray sum = 0 and look for maximum length.
            sum += nums[i]==0?-1:1;
            if (prefixSum.containsKey(sum)) {
                int len = i - prefixSum.get(sum);
                maxLength = Math.max(len,maxLength);
            } else {
                // NOTE: WE REGISTER THE SUM ONLY WHEN IT WAS NOT ALREADY.
                // THIS IS SPECIFIC FOR FIND-THE-MAX-SUBARRAY-LENGTH PROBLEMS.
                prefixSum.put(sum,i);
            }
        }

        return maxLength;
    }

    public int findMaxLengthAi(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // map cumulative sum -> earliest index where this sum occurred
        Map<Integer, Integer> firstIndex = new HashMap<>();
        int sum = 0;
        firstIndex.put(0, -1); // sum 0 at index -1 simplifies length calc for prefix starting at 0
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            // treat 0 as -1, 1 as +1
            sum += (nums[i] == 0) ? -1 : 1;
            if (firstIndex.containsKey(sum)) {
                int len = i - firstIndex.get(sum);
                if (len > maxLen) maxLen = len;
            } else {
                firstIndex.put(sum, i);
            }
        }
        return maxLen;
    }
}
