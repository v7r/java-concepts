package org.example.algomonster.binarysearch;

/**
 * Suppose you are given an array A[1...n] of sorted integers that has been circularly shifted k positions to the right.
 * For example, [35,42,5,15,27,29] is a sorted array that has been circularly shifted k = 2 positions,
 * while [27,29,35,42,5,15] has been shifted k = 4 positions.
 *
 * Give an algorithm for finding the maximum element in A
 * that runs in O(log n) time.
 */
public class FindMaximumInSortedRotatedArray {
    // 3,4,5,0,1,2  maximum is 5
    public int searchMax(int nums[]) {
        int k = -1;
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end-start)/2;
            if (nums[mid] <= nums[nums.length -1]) {
                k = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        if (k == 0) return nums.length - 1;
        return k - 1;
    }
}
