package org.example.algomonster.twopointers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Followup to PrefixSum problem statement:
 *      Find the total number of subarrays that sum to the target.
 *
 * Solution explanation at the end of this class definition.
 */
public class PrefixSumCountAllSubarrays {
    // Input: arr = [1, -20, -3, 30, 5, 2], target = 7
    // I memorized the logic rather than grasping it !!
    public static int subarraySumTotal(List<Integer> arr, int target) {
        Map<Integer, Integer> prefixSumCt = new HashMap<>();
        prefixSumCt.put(0,1);
        int count = 0; int sum = 0;
        for (int i=0;i<arr.size();i++) {
            sum += arr.get(i);
            int comp = sum - target;
            if (prefixSumCt.containsKey(comp)) {
                count += prefixSumCt.get(comp);
            }
            prefixSumCt.compute(sum,(s,c) -> c==null?1:++c);
        }
        return count;
    }
}

/**
 Explanation for counting:
 Instead of returning the first matching subarray, count all subarrays that sum to the target. The core approach remains
 the same, but change what the dictionary stores. Map prefix_sum → frequency instead of prefix_sum → index. The
 frequency tracks how many times each prefix sum has appeared.

 When the current sum is S and S - target has appeared F times previously, there are F different subarrays ending at
 the current position that sum to the target. Add F to the total count.

 Consider [1, 2, 3] with target = 3. Initialize prefix_map = {0: 1} and count = 0.

 At index 0: sum = 1. Check 1 - 3 = -2 (not in map, count stays 0). Store {0: 1, 1: 1}.

 At index 1: sum = 3. Check 3 - 3 = 0 (found with frequency 1, so count += 1). Store {0: 1, 1: 1, 3: 1}.

 At index 2: sum = 6. Check 6 - 3 = 3 (found with frequency 1, so count += 1). Store {0: 1, 1: 1, 3: 1, 6: 1}.

 Final count: 2, representing subarrays [1, 2] (indices 0-1) and [3] (index 2).

 The algorithm runs in O(n) time with O(n) space, processing each element once and updating counts in constant time.



 */
