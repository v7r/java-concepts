package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShortestSubArraySumTest extends TestCase {

    public void testExampleFromComment() {
        List<Integer> nums = Arrays.asList(1, 4, 1, 7, 3, 0, 2, 5);
        assertEquals(2, ShortestSubArraySum.subarraySumShortest(nums, 10));
    }

    public void testSingleElementEqualsTarget() {
        List<Integer> nums = Collections.singletonList(10);
        assertEquals(1, ShortestSubArraySum.subarraySumShortest(nums, 10));
    }

    public void testFullArrayNeeded() {
        List<Integer> nums = Arrays.asList(1,1,1,1,1);
        assertEquals(5, ShortestSubArraySum.subarraySumShortest(nums, 5));
    }

    public void testMultipleCandidatesChooseShortest() {
        List<Integer> nums = Arrays.asList(2,3,1,2,4,3);
        // possible windows >=7 include [2,3,1,2] (len4), [1,2,4] (len3), [4,3] (len2) => answer 2
        assertEquals(2, ShortestSubArraySum.subarraySumShortest(nums, 7));
    }

    public void testZerosIncludedSingleElement() {
        List<Integer> nums = Arrays.asList(0,0,5,0,0);
        assertEquals(1, ShortestSubArraySum.subarraySumShortest(nums, 5));
    }

    public void testZerosBetweenNumbers() {
        List<Integer> nums = Arrays.asList(1,0,0,5);
        // only window meeting >=6 is the whole array
        assertEquals(4, ShortestSubArraySum.subarraySumShortest(nums, 6));
    }

    public void testMultipleZerosAndOnes() {
        List<Integer> nums = Arrays.asList(0,1,0,1,0,1,0);
        // target 2 can be met by [1,0,1] length 3 or [1,0,1] elsewhere; shortest is 2? actually need sum>=2, minimal length is 3
        assertEquals(3, ShortestSubArraySum.subarraySumShortest(nums, 2));
    }

    public void testAllSameNumbers() {
        List<Integer> nums = Arrays.asList(2,2,2,2,2);
        assertEquals(3, ShortestSubArraySum.subarraySumShortest(nums, 6));
    }

    public void testLargeNumbers() {
        List<Integer> nums = Arrays.asList(1000000, 2000000, 3000000);
        assertEquals(2, ShortestSubArraySum.subarraySumShortest(nums, 5000000));
    }

    public void testEmptyListReturnsZero() {
        List<Integer> nums = Collections.emptyList();
        assertEquals(0, ShortestSubArraySum.subarraySumShortest(nums, 1));
    }

    public void testTargetGreaterThanSumReturnsZero() {
        List<Integer> nums = Arrays.asList(1,2,3);
        // even though problem promises target <= sum(nums), implementation returns 0 when no subarray meets target
        assertEquals(0, ShortestSubArraySum.subarraySumShortest(nums, 100));
    }
}

