package org.example.algomonster.twopointers;

/**
 * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the
 * array together in any place in the array.
 *
 * Example 1:
 *
 * Input: data = [1,0,1,0,1]
 * Output: 1
 * Explanation: There are 3 ways to group all 1's together: [1,1,1,0,0] using 1 swap. [0,1,1,1,0] using
 * 2 swaps. [0,0,1,1,1] using 1 swap. The minimum is 1.
 *
 * Example 2:
 *
 * Input: data = [0,0,0,1,0]
 * Output: 0
 * Explanation: Since there is only one 1 in the array, no swaps are needed.
 *
 * Example 3:
 *
 * Input: data = [1,0,1,0,1,0,0,1,1,0,1]
 * Output: 3
 * Explanation: One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
 *
 * Constraints:
 *
 * 1 <= data.length <= 105
 * data[i] is either 0 or 1.
 */
public class MinimumSwapsToGroupAllOnesTogether {
    // 1,0,1,0,1
    //
    public int minSwaps(int[] data) {
        // Count all ones. This will be the window size when grouped all 1s together.
        int totalOnes = 0;
        for (int i : data) totalOnes += i;


        //Since our aim is to find the minimum swaps needed to remove 0's we find the window which has
        //minimum zeros
        int l = 0, r = totalOnes; // set r to the position of the totalOnes to represent the first window from 0.
        int windowCount = 0;
        // Count the first window 1's
        while (l < totalOnes) {
            windowCount += data[l++];
        }
        l = 0; // reset l to the starting of the first window.
        int minSwaps = totalOnes - windowCount; // first window minimum swaps
        while (r<data.length) {
            windowCount += -1 * data[l++];
            windowCount += data[r++];
            minSwaps = Math.min(minSwaps, totalOnes - windowCount);
        }
        return minSwaps;
    }
}
