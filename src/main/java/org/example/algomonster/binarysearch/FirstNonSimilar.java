package org.example.algomonster.binarysearch;

import java.util.List;

/**
 * Given an array of integers sorted in increasing order and a target, find the index of the first element in the
 * array that is larger than or equal to the target. Assume that it is guaranteed to find a satisfying number.
 *
 * Input:
 *
 * arr = [1, 3, 3, 5, 8, 8, 10]
 * target = 2
 * Output: 1
 *
 * Explanation: The first element larger than 2 is 3, which has index 1.
 *
 * Input:
 *
 * arr = [2, 3, 5, 7, 11, 13, 17, 19]
 * target = 6
 * Output: 3
 *
 * Explanation: The first element larger than 6 is 7, which has index 3.
 */
public class FirstNonSimilar {
    public static int firstNotSmaller(List<Integer> arr, int target) {
        int fns = -1;
        int begin = 0, end = arr.size() - 1;
        while (begin <= end) {
            int mid = begin + (end - begin)/2;
            if (arr.get(mid) >= target) {
                fns = mid;
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return fns;
    }
}
