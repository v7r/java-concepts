package org.example.algomonster.binarysearch;

/**
 * Given an integer, find its square root without using the built-in square root function. Only return the integer part
 * (truncate the decimals).
 *
 * Input: 16
 *
 * Output: 4
 *
 * Input: 8
 *
 * Output: 2
 *
 * Explanation: square root of 8 is 2.83..., return the integer part, 2
 */
public class SquareRootEstimation {

    public int findSqRootEstimate(int number) {
        if (number == 0) return 0;
        int sr = 1;
        int left = 1, right = number;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ( mid * mid >= number) {
                if (mid * mid > number) sr = mid - 1; else sr = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return sr;
    }
}
