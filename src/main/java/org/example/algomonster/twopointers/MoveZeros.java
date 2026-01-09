package org.example.algomonster.twopointers;

import java.util.List;

/**
 * Given an array of integers, move all the 0s to the back of the array while maintaining the relative order of the
 * non-zero elements. Do this in-place using constant auxiliary space.
 *
 * Input: [1, 0, 2, 0, 0, 7]
 * Output: [1, 2, 7, 0, 0, 0]
 */
public class MoveZeros {

    public static void moveZerosToLast(List<Integer> nums) {
        int f = 0, s = 0;
        while (f < nums.size()) {
            if (nums.get(f) != 0) {
                int t = nums.get(s);
                nums.set(s,nums.get(f));
                nums.set(f, t);
                s++;
            }
            f++;
        }
    }

    // [1, 0, 2, 0, 0, 7]
    public static void moveZerosToFirst(List<Integer> nums) {
        int f = nums.size()-1, s = f;
        while (f >= 0) {
            if (nums.get(f) != 0) {
                int t = nums.get(s);
                nums.set(s,nums.get(f));
                nums.set(f,t);
                s--;
            }
            f--;
        }
    }
}
