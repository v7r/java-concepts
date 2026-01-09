package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LargetSubarraySumTest extends TestCase {

    public void testExampleFromComment() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 7, 4, 1);
        assertEquals(14, LargestSubarraySum.subarraySumFixed(nums, 3));
    }

    public void testKEqualsArraySize() {
        List<Integer> nums = Arrays.asList(5, 1, 2, 3);
        // k equals array length -> sum of entire array
        assertEquals(11, LargestSubarraySum.subarraySumFixed(nums, nums.size()));
    }

    public void testKGreaterThanSize() {
        List<Integer> nums = Arrays.asList(1, 2);
        // per implementation should return 0 when k > n
        assertEquals(0, LargestSubarraySum.subarraySumFixed(nums, 5));
    }

    public void testKIsOne() {
        List<Integer> nums = Arrays.asList(3, 6, 1, 9, 2);
        // max single element
        assertEquals(9, LargestSubarraySum.subarraySumFixed(nums, 1));
    }

    public void testAllZeros() {
        List<Integer> nums = Arrays.asList(0, 0, 0, 0);
        assertEquals(0, LargestSubarraySum.subarraySumFixed(nums, 2));
    }

    public void testRepeatingPattern() {
        List<Integer> nums = Arrays.asList(2, 2, 2, 2, 2);
        // any window of size 3 sums to 6
        assertEquals(6, LargestSubarraySum.subarraySumFixed(nums, 3));
    }

    public void testWindowAtStartIsMax() {
        List<Integer> nums = Arrays.asList(9, 8, 1, 1, 1);
        assertEquals(9+8+1, LargestSubarraySum.subarraySumFixed(nums, 3));
    }

    public void testWindowAtEndIsMax() {
        List<Integer> nums = Arrays.asList(1, 1, 1, 8, 9);
        assertEquals(8+9+1, LargestSubarraySum.subarraySumFixed(nums, 3));
    }

    public void testSingleElementArray() {
        List<Integer> nums = Collections.singletonList(7);
        assertEquals(7, LargestSubarraySum.subarraySumFixed(nums, 1));
        // k > size -> 0
        assertEquals(0, LargestSubarraySum.subarraySumFixed(nums, 2));
    }

    public void testEmptyList() {
        List<Integer> nums = Collections.emptyList();
        assertEquals(0, LargestSubarraySum.subarraySumFixed(nums, 1));
    }

}
