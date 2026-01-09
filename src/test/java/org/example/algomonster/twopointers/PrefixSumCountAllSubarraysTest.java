package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrefixSumCountAllSubarraysTest extends TestCase {

    public void testSimpleOnes() {
        List<Integer> arr = Arrays.asList(1,1,1);
        // subarrays summing to 2: [0..1], [1..2]
        assertEquals(2, PrefixSumCountAllSubarrays.subarraySumTotal(arr, 2));
    }

    public void testSingleElementMatch() {
        List<Integer> arr = Collections.singletonList(3);
        assertEquals(1, PrefixSumCountAllSubarrays.subarraySumTotal(arr, 3));
    }

    public void testStartFromZeroSubarray() {
        // important: subarray that starts at index 0 sums to target
        List<Integer> arr = Arrays.asList(2,3,1);
        // whole array sums to 6
        assertEquals(1, PrefixSumCountAllSubarrays.subarraySumTotal(arr, 6));
    }

    public void testZerosTargetZero() {
        List<Integer> arr = Arrays.asList(0,0,0);
        // number of subarrays = n*(n+1)/2 = 6
        assertEquals(6, PrefixSumCountAllSubarrays.subarraySumTotal(arr, 0));
    }

    public void testNegativesAndZeros() {
        List<Integer> arr = Arrays.asList(1, -1, 1, -1, 1);
        // many subarrays sum to 0; brute force count is 6
        assertEquals(6, PrefixSumCountAllSubarrays.subarraySumTotal(arr, 0));
    }

    public void testOverlappingSolutions() {
        List<Integer> arr = Arrays.asList(1,2,1,2,1);
        // subarrays summing to 3: [0..1],[1..2],[2..3],[3..4] => 4
        assertEquals(4, PrefixSumCountAllSubarrays.subarraySumTotal(arr, 3));
    }

    public void testNoSolution() {
        List<Integer> arr = Arrays.asList(5,5,5);
        assertEquals(0, PrefixSumCountAllSubarrays.subarraySumTotal(arr, 1));
    }

    public void testMixedNumbers() {
        List<Integer> arr = Arrays.asList(3,4,7,2,-3,1,4,2);
        // known example: total subarrays summing to 7 is 4
        assertEquals(4, PrefixSumCountAllSubarrays.subarraySumTotal(arr, 7));
    }

}

