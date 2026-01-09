package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LengthOfLongestSubArrayTest extends TestCase {

    // Example from class comment
    public void testExampleFromComment() {
        List<Integer> nums = Arrays.asList(1, 6, 3, 1, 2, 4, 5);
        int target = 10;
        assertEquals(4, LengthOfLongestSubArray.subarraySumLongest(nums, target));
    }
    public void testExampleFromComment1() {
        List<Integer> nums = Arrays.asList(1, 6, 3, 1, 2, 4, 5,1,1,1,1,1);
        int target = 10;
        assertEquals(6, LengthOfLongestSubArray.subarraySumLongest(nums, target));
    }

    public void testAllZerosTargetZero() {
        List<Integer> nums = Arrays.asList(0,0,0,0);
        assertEquals(4, LengthOfLongestSubArray.subarraySumLongest(nums, 0));
    }

    public void testSingleElementGreaterThanTarget() {
        List<Integer> nums = Collections.singletonList(5);
        assertEquals(0, LengthOfLongestSubArray.subarraySumLongest(nums, 3));
    }

    public void testSingleElementEqualToTarget() {
        List<Integer> nums = Collections.singletonList(3);
        assertEquals(1, LengthOfLongestSubArray.subarraySumLongest(nums, 3));
    }

    public void testNoSubarrayFits() {
        List<Integer> nums = Arrays.asList(4,5,6);
        assertEquals(0, LengthOfLongestSubArray.subarraySumLongest(nums, 3));
    }

    public void testSlidingWindowNeedsShrinkMultiple() {
        List<Integer> nums = Arrays.asList(2,2,2,2,2);
        // best windows are length 2 (2+2=4 <=5)
        assertEquals(2, LengthOfLongestSubArray.subarraySumLongest(nums, 5));
    }

    public void testAscendingSequence() {
        List<Integer> nums = Arrays.asList(1,2,3,4,5);
        // best is [1,2] => length 2 (sum 3) or [5] => length 1. 2 is correct.
        assertEquals(2, LengthOfLongestSubArray.subarraySumLongest(nums, 5));
    }

    public void testZerosAndPositives() {
        List<Integer> nums = Arrays.asList(0,1,2,0,3);
        // [0,1,2,0] sums to 3 and length 4
        assertEquals(4, LengthOfLongestSubArray.subarraySumLongest(nums, 3));
    }

    public void testEmptyList() {
        List<Integer> nums = Collections.emptyList();
        assertEquals(0, LengthOfLongestSubArray.subarraySumLongest(nums, 10));
    }

    public void testLargeTargetCapturesWholeArray() {
        List<Integer> nums = Arrays.asList(1,2,3,4);
        assertEquals(4, LengthOfLongestSubArray.subarraySumLongest(nums, 100));
    }
}

