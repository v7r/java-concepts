package org.example.algomonster.twopointers;

import java.util.List;
import java.util.Objects;

/**
 * Remove Duplicates
 * Given a sorted list of numbers, remove duplicates and return the new length. You must do this in-place and without
 * using extra memory.
 *
 * Input: [0, 0, 1, 1, 1, 2, 2].
 *
 * Output: 3.
 *
 * Your function should modify the list in place so that the first three elements become 0, 1, 2. Return 3 because
 * the new length is 3.
 *
 *
 */
public class RemoveDuplicates {

    public static int removeDuplicates(List<Integer> arr) {
        int left = 0, right = 0;
        while (right < arr.size()) {
            if (!Objects.equals(arr.get(left), arr.get(right))) {
                left++;
                int t = arr.get(right);
                arr.set(left,t);
            }
            right++;
        }
        return left + 1;
    }
}
