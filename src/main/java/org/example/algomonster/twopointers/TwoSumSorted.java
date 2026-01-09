package org.example.algomonster.twopointers;

import java.util.List;

/**
 * Two Sum Sorted
 * Given an array of integers sorted in ascending order, find two numbers that add up to a given target.
 * Return the indices of the two numbers in ascending order. You can assume elements in the array are unique and there
 * is only one solution. Do this in O(n) time and with constant auxiliary space.
 *
 * Input:
 *
 * arr: a sorted integer array
 * target: the target sum we want to reach
 * Sample Input: [2, 3, 4, 5, 8, 11, 18], 8
 *
 * Sample Output: 1 3
 */
public class TwoSumSorted {
    public static List<Integer> twoSumSorted(List<Integer> arr, int target) {
        int l = 0, r = arr.size() - 1;
        while (l<r) {
            if (arr.get(l) + arr.get(r) >= target) {
                if (arr.get(l) + arr.get(r) == target) break;
                r--;
            } else {
                l++;
            }
        }
        return List.of(l,r);
    }
}
