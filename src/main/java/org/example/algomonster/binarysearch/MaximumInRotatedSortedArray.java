package org.example.algomonster.binarysearch;

import java.util.List;

/**
 * A sorted array of unique integers was rotated at an unknown pivot.
 * For example, [10, 20, 30, 40, 50] becomes [30, 40, 50, 10, 20].
 * Find the index of the maximum element in this array.
 *
 * Input: [30, 40, 50, 10, 20]
 *
 * Output: 2
 *
 * Explanation: The greatest element is 50, and its index is 2.
 *
 * Input: [3, 5, 7, 11, 13, 17, 19, 2]
 *
 * Output: 6
 *
 * Explanation: The greatest element is 19, and its index is 6.
 */
public class MaximumInRotatedSortedArray {

    //[30, 40, 50, 10, 20]
    // f,  f,  f,  t,  t,  a[0] > a[i]
    // t,  t,  t,  f,  f
    //[2, 1]
    // f, t
    // t, f
    //[3, 5, 7, 11, 13, 17, 19, 2]
    // f, f, f, f,  f,  f,  f, t
    // t, t, t, t,  t,  t,  t, f
    //[1,2,3,4,5]
    // f,f,f,f,f   a[i] > a[n]

    public static int findMaxRotated(List<Integer> arr) {
        if (arr.isEmpty()) return -1;
        int max = -1;
        int left = 0, right = arr.size() - 1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (arr.get(mid) > arr.get(arr.size()-1)) {
                max = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (max == -1) max = arr.size() - 1;
        return max;
    }
}
