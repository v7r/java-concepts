package org.example.algomonster.twopointers;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the
 * same color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 *
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is either 0, 1, or 2.
 *
 *
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */
public class SortColors {
    // [2,0,2,1,1,0]
    // [0,0,2,1,1,2]
    // [0,0,2,1,1,2]

    //[2,0,2,1,1,0]
    //[0,0,2,1,1,2]
    //[0,0,1,1,2,2]
    public void sortColors(int[] nums) {
        int l = 0, m = 0, r = nums.length - 1;
        while (m <= r) {
            if (nums[m] == 0) {
                int t = nums[l];
                nums[l] = nums[m];
                nums[m] = t;
                l++; m++;
            } else if (nums[m] == 1) {
                m++;
            } else if (nums[m] == 2){
                int t = nums[r];
                nums[r] = nums[m];
                nums[m] = t;
                r--;
            }
        }
    }
}
