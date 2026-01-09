package org.example.algomonster.twopointers;

/**
 * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.
 *
 * Example 1:
 *
 * Input: data = [1,0,1,0,1]
 * Output: 1
 * Explanation: There are 3 ways to group all 1's together: [1,1,1,0,0] using 1 swap. [0,1,1,1,0] using 2 swaps. [0,0,1,1,1] using 1 swap. The minimum is 1.
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
 *
 *
 */
public class MinimumSwapsToGroupOnesTogether {

    /**
     * Solution explanation
     *
     * The minimum swap is given by the number of 0's in a window with the most 1's, so the answer is the result of the
     * subtraction totalOnes - onesInWindow.
     *
     * This is a classic sliding window question that has a fixed size. We wish to find a window to store all the 1's in
     * the very end, thus we fix the size to the total number of 1's. Since we want to use minimum number of swaps, it
     * will be best if we can find a window with most 1's (or least 0's). Let count1 be the number of 1's in the entire
     * data. We will initialize total to be the number of 1's in the window from index 0 to the count1 (which is the sum
     * of the window). Then, as we slide the window to the right, we remove data[r-count1] from the total and add
     * data[r] to the total so that total is the sum of the new window. Then for each window that has size of count1,
     * we compare for the minimum swaps. Here, we calcluate the swaps count1-total, as the number of swaps is just the
     * number of 0's in the window.
     */
    public int minSwaps(int[] data) {
        // Count number of 1s
        int count1 = 0;
        for (int num : data) {
            if (num == 1) {
                count1++;
            }
        }

        // Sum of first window of size count1
        int total = 0;
        for (int i = 0; i < count1; i++) {
            total += data[i];
        }

        int swaps = count1 - total;

        // Sliding window
        for (int r = count1; r < data.length; r++) {
            total += data[r];
            total -= data[r - count1];
            swaps = Math.min(swaps, count1 - total);
        }

        return swaps;
    }
}
