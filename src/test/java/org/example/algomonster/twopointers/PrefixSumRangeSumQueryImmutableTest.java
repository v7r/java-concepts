package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrefixSumRangeSumQueryImmutableTest extends TestCase {

    public void testSimpleRange() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        assertEquals(9, PrefixSumRangeSumQueryImmutable.rangeSumQueryImmutable(nums, 1, 3));
    }

    public void testLeftZeroRange() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        assertEquals(6, PrefixSumRangeSumQueryImmutable.rangeSumQueryImmutable(nums, 0, 2));
    }

    public void testSingleElementRange() {
        List<Integer> nums = Collections.singletonList(5);
        assertEquals(5, PrefixSumRangeSumQueryImmutable.rangeSumQueryImmutable(nums, 0, 0));
    }

    public void testRangeWithNegativesAndZeros() {
        List<Integer> nums = Arrays.asList(1, -2, 3, 0, 2);
        // sum from index 1 to 3: -2 + 3 + 0 = 1
        assertEquals(1, PrefixSumRangeSumQueryImmutable.rangeSumQueryImmutable(nums, 1, 3));
    }

    public void testRangeEntireArray() {
        List<Integer> nums = Arrays.asList(2, 4, 6);
        assertEquals(12, PrefixSumRangeSumQueryImmutable.rangeSumQueryImmutable(nums, 0, 2));
    }

    public void testRangeAtEnd() {
        List<Integer> nums = Arrays.asList(7, 1, 3, 9, 2);
        // sum indices 3..4 = 9 + 2 = 11
        assertEquals(11, PrefixSumRangeSumQueryImmutable.rangeSumQueryImmutable(nums, 3, 4));
    }

    public void testSmallArrayLeftGreaterThanZero() {
        List<Integer> nums = Arrays.asList(1, 1, 1);
        // sum indices 1..2 = 1 + 1 = 2
        assertEquals(2, PrefixSumRangeSumQueryImmutable.rangeSumQueryImmutable(nums, 1, 2));
    }

    public void testTrickyCoincidentalPrefixValue() {
        // This array produces a prefix sum value that equals an index (1),
        // which may accidentally mask incorrect logic that looks up prefixSum by index.
        List<Integer> nums = Arrays.asList(1, 0, 2);
        // sum indices 1..2 = 0 + 2 = 2
        assertEquals(2, PrefixSumRangeSumQueryImmutable.rangeSumQueryImmutable(nums, 1, 2));
    }
}

