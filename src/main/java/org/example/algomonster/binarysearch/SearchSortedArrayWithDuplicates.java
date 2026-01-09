package org.example.algomonster.binarysearch;

/**
 * This is not a problem from AlgoMonster. But from my mind.
 *
 * Problem: Given an array of numbers in nums that is sorted in ascending order and elements may be duplicated.
 * Find the target number and return its index that is the last if it is duplicated. If the target is not found return
 * -1.
 *
 * Example1:
 *
 * Input: nums [1,2,3,3,4,5], target 3
 * output: 3
 * explanation: last occurrence of 3 is at the index 3
 *
 * Example2:
 *
 * Input: nums [2,2], target 2
 * output: 1
 *
 */
public class SearchSortedArrayWithDuplicates {

    // [1,2,3,3,4,5]
    // nums[i]<=3
    // [t,t,t,t,f,f]
    // converge at first 'f' ( find the last 't' )
    public int searchLastOccurrence(int[] nums, int target) {
        int k = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (nums[mid]<=target) {
                if (nums[mid]==target) k = mid;
                // Note: this is opposite of the 'template' where we flip the left and right sides in the if condition.
                //       because we are finding the first 'f' instead of first 't' in
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return k;
    }
}
