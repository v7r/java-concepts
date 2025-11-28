package org.example.algomonster.binarysearch;

/**
 * This is not a problem from AlgoMonster. But from the internet.
 *
 * Problem: Find first and last position of an element in a sorted array.
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given
 * target value.
 *
 * If the target is not found in the array, return  [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 * Constraints:
 * 1. 0 <= nums.length <= 10^5
 * nums is a non-decreasing array.
 *
 *
 * To run the test cases use the FindFirstAndLastTest class.
 */
public class FindFirstAndLast {

    public int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findLast(nums, target);
        return new int[] { first, last };
    }

    // [5,7,7,8,8,10]
    private int findFirst(int[] nums, int target) {
        int first = -1;
        int begin = 0, end = nums.length - 1;
        while (begin <= end) {
            int mid = begin + (end - begin)/2;
            if (nums[mid] >= target) {
                if (nums[mid] == target) first = mid;
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return first;
    }

    // [5,7,7,8,8,10]
    //  t,t,t,t,t,f  (m <= 8)
    //  f,f,f,f,f,t  (!m <= 8)
    private int findLast(int[] nums, int target) {
        int last = -1;
        int begin = 0, end = nums.length - 1;
        while (begin <= end) {
            int mid = begin + (end - begin)/2;
            if (!(nums[mid] <= target)) {
                end = mid - 1;
            } else {
                if (nums[mid] == target) last = mid;
                begin = mid + 1;
            }
        }
        return last;
    }
}
