package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrefixSumTest extends TestCase {

    public void testExampleFromComment() {
        List<Integer> arr = Arrays.asList(1, -20, -3, 30, 5, 4);
        List<Integer> expected = Arrays.asList(1, 4);
        assertEquals(expected, PrefixSum.subarraySum(arr, 7));
    }

    public void testSingleElementMatch() {
        List<Integer> arr = Collections.singletonList(7);
        List<Integer> expected = Arrays.asList(0, 1);
        assertEquals(expected, PrefixSum.subarraySum(arr, 7));
    }

    public void testSingleElementNoMatch() {
        List<Integer> arr = Collections.singletonList(5);
        assertEquals(Collections.emptyList(), PrefixSum.subarraySum(arr, 7));
    }

    public void testZeroTargetWithZeros() {
        List<Integer> arr = Arrays.asList(0, 0, 0);
        List<Integer> expected = Arrays.asList(0, 1);
        assertEquals(expected, PrefixSum.subarraySum(arr, 0));
    }

    public void testNegativeNumbersAndZeroTarget() {
        List<Integer> arr = Arrays.asList(-1, -2, 3, 0);
        // subarray [-1,-2,3,0] sums to 0 => indices [0,4]
        List<Integer> expected = Arrays.asList(0, 3);
        assertEquals(expected, PrefixSum.subarraySum(arr, 0));
    }

    public void testMultiplePossibleMatchesReturnFirst() {
        List<Integer> arr = Arrays.asList(3, 2, -1, 4, 1);
        // matches: [0,2) => 3+2=5 => indices [0,2]; also [3,5) => 4+1=5 but earlier one should be returned
        List<Integer> expected = Arrays.asList(0, 2);
        assertEquals(expected, PrefixSum.subarraySum(arr, 5));
    }

    public void testMatchAtEnd() {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4);
        // subarray [2,4) => 3+4=7 indices [2,4]
        List<Integer> expected = Arrays.asList(2, 4);
        assertEquals(expected, PrefixSum.subarraySum(arr, 7));
    }
}

