package org.example.algomonster.binarysearch;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice,
 * except for one element which appears exactly once.
 *
 * Return the single element that appears only once.
 *
 * Your solution must run in O(log n) time and O(1) space.
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,3,3,4,4,8,8] Output: 2
 *
 * Example 2:
 *
 * Input: nums = [3,3,7,7,10,11,11] Output: 10
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 */
public class SingleElementInSortedArray {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1, ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (toTheLeft(mid, nums)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
        return nums[ans];
    }

    /**
     * This should return true if the non-duplicate single element is on left side
     * or else return false.
     *
     * Until encountering the non-duplicate single element the paris of similar elements begin in the even locations
     * (0,2,4,6 etc..) so the next element at the even index is always same.
     * Non-duplicate single element disrupts this pattern. The pattern reverses such that the Odd element and
     * the next element of it are equal.
     *
     * We should return true to discard right part.
     *    when index is even and element in index+1 is not same
     * we should return false ti discard left part.
     *    when index is
     * @param idx
     * @param nums
     * @return
     */
    //  {1,1,2,3,3,4,4,8,8}
    private boolean toTheLeft(int idx, int[] nums) {
        if (idx == nums.length - 1) return true;
        if (idx % 2 == 0) {
            // at even location it expected that next element should be equal if non-duplication single item is towards the right side
            // if not equal then non-duplication element must be on the left half.
            // So return true if next element is not same.
            return nums[idx] != nums[idx+1];
        } else {
            // at odd location it is expected that next element should be equal if non-duplicate single item is towards the right side
            // if not equal then the non-duplicate element must be on the right half.
            // so return false if next element is same.
            return nums[idx] == nums[idx+1];
        }
    }
}
