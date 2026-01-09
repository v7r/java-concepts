package org.example.algomonster.twopointers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers arr and a target value, find a subarray that sums to the target. Return the start and end
 * indices of the subarray.
 *
 * Input: arr = [1, -20, -3, 30, 5, 4], target = 7
 *
 * Output: [1, 4]
 *
 * Explanation: The subarray [-20, -3, 30] from index 1 (inclusive) to 4 (exclusive) sums to 7.
 *
 * NOTE: THIS PROBLEM IS DIFFERENT FROM THE OTHER SUBARRAY PROBLEMS IN THAT THE ARRAY IN THIS PROBLEM IS NOT SORTED AND
 *       MAY CONTAIN NEGATIVE NUMBERS. SO THE ALGORITHM IS DIFFERENT.
 */
public class PrefixSum {
    //  Sum of [i,j] = sum of [0,j] - sum of [0,i-1]
    //  Find i,j such that runningSum of (0 through j) - sum of [0,i-1] == target.
    //  rearrange the above expression to runningSum - target = sum of [0,i-1]
    //  At each j calculate the 'runningSum - target'. If the calculated value is found in precomputed directory then
    //  it's value + 1 becomes the 'i'. Therefore, the sub array is [i,j+1]. If not found save the 'runningSum-target'
    //  value into the directory with key as the computed value and value as the j+1;
    //

    // 1, -20, -3, 30, 5, 4 t=7

    //1 3 -3 8 5   7 t=5 A:2,4
    //prefixSum:
    //0 1 4 1 9 14 21
    //runningSum:
    //1 4 1 9 14 21
    //comp:
    //-4-1-4 4 9 16
    //       ^(2,4) return here
    public static List<Integer> subarraySum(List<Integer> arr, int target) {
        Map<Integer,Integer> prefixSum = new HashMap<>();
        prefixSum.put(0,0);
        int runningSum = 0;
        for (int j=0;j<arr.size();j++) {
            runningSum += arr.get(j);
            int complement = runningSum - target;
            if (prefixSum.containsKey(complement)) {
                return List.of(prefixSum.get(complement),j+1);
            }
            prefixSum.put(runningSum,j+1);
        }
        return List.of();
    }
}

/**

 Solution explanation:

 The prefix sum transformation
 The sum of elements from index i to j equals the sum from 0 to j minus the sum from 0 to (i-1). Consider the array
 [1, -20, -3, 30, 5, 4]. The sum from index 1 to 3 is -20 + (-3) + 30 = 7.
 We can compute this as: (sum from 0 to 3) - (sum from 0 to 0) = 8 - 1 = 7. The sum from 0 to 3 includes all four
 elements 1 + (-20) + (-3) + 30 = 8. The sum from 0 to 0 is just 1. Subtracting removes the first element, leaving the
 sum of indices 1 to 3.

 This observation lets us precompute cumulative sums—called prefix sums—for each position. The prefix sum at index i
 represents the total of all elements from index 0 through i. With these precomputed values, we can calculate any range
 sum in constant time by subtracting two prefix sums.

 Build the prefix sum array by maintaining a running total. Start with 0 to represent the sum before any elements:

 prefix_sum = [0]
 for num in arr:
 prefix_sum.append(prefix_sum[-1] + num)
 For [1, -20, -3, 30, 5, 4], this produces [0, 1, -19, -22, 8, 13, 17]. The value at prefix_sum[i] represents the sum
 of all elements from index 0 to i-1. So prefix_sum[4] contains the sum of elements at indices 0, 1, 2, and 3, which is
 1 + (-20) + (-3) + 30 = 8.

 To find the sum from index 1 to 3, calculate prefix_sum[4] - prefix_sum[1] = 8 - 1 = 7. This works because
 prefix_sum[4] includes indices 0-3, and subtracting prefix_sum[1] removes index 0, leaving indices 1-3.

 Converting to a lookup problem
 We need indices i and j where prefix_sum[j] - prefix_sum[i] = target.
 Rearranging: prefix_sum[i] = prefix_sum[j] - target. As we compute each prefix sum at position j,
 check whether prefix_sum[j] - target exists in a dictionary of previously seen prefix sums. If it does, we've found
 our subarray.

 The dictionary maps each prefix sum value to the index where it occurred. Initialize with {0: 0} to handle subarrays
 starting at index 0. Consider arr = [7, 3, 5] with target = 7. At index 0, the running sum is 7. We check if 7 - 7 = 0
 exists in the dictionary. It does at position 0, indicating the subarray from index 0 to 0 (the element [7]) sums to
 the target.

 Walking through the algorithm
 Start with arr = [1, -20, -3, 30, 5, 4] and target = 7. Initialize the dictionary prefix_map = {0: 0} and running sum
 current_sum = 0.

 At index 0: current_sum = 1. Check if 1 - 7 = -6 exists in prefix_map. It doesn't. Add {1: 1} to the map (mapping sum
 to the next index after seeing this sum).

 At index 1: current_sum = 1 + (-20) = -19. Check if -19 - 7 = -26 exists. It doesn't. Add {-19: 2}.

 At index 2: current_sum = -19 + (-3) = -22. Check if -22 - 7 = -29 exists. It doesn't. Add {-22: 3}.

 At index 3: current_sum = -22 + 30 = 8. Check if 8 - 7 = 1 exists. Yes, at position 1. This means the subarray from
 index 1 (inclusive) to 4 (exclusive) sums to 7. Return [1, 4].

 The dictionary lookup replaced an O(n) scan for each position with an O(1) check, reducing the time from O(n²) to O(n).

 */
