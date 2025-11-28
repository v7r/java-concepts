package org.example.algomonster.binarysearch;

import junit.framework.TestCase;

public class FindMaximumInSortedRotatedArrayTest extends TestCase {

    public void testRotatedExample0() {
        int[] nums = {5,7,10,0,1,2,3,4};
        FindMaximumInSortedRotatedArray solver = new FindMaximumInSortedRotatedArray();
        assertEquals(2, solver.searchMax(nums)); // max 5 at index 2
    }

    public void testRotatedExample1() {
        int[] nums = {3,4,5,0,1,2};
        FindMaximumInSortedRotatedArray solver = new FindMaximumInSortedRotatedArray();
        assertEquals(2, solver.searchMax(nums)); // max 5 at index 2
    }

    public void testRotatedExample2() {
        int[] nums = {35,42,5,15,27,29};
        FindMaximumInSortedRotatedArray solver = new FindMaximumInSortedRotatedArray();
        assertEquals(1, solver.searchMax(nums)); // max 42 at index 1
    }

    public void testRotatedExample31() {
        int[] nums = {27,29,35,42,5,15};
        FindMaximumInSortedRotatedArray solver = new FindMaximumInSortedRotatedArray();
        assertEquals(3, solver.searchMax(nums)); // max 42 at index 3
    }

    public void testRotatedExample3() {
        int[] nums = {27,29,35,42,5,15};
        FindMaximumInSortedRotatedArray solver = new FindMaximumInSortedRotatedArray();
        assertEquals(3, solver.searchMax(nums)); // max 42 at index 3
    }

    public void testNoRotation() {
        int[] nums = {1,2,3,4,5};
        FindMaximumInSortedRotatedArray solver = new FindMaximumInSortedRotatedArray();
        assertEquals(4, solver.searchMax(nums)); // max 5 at index 4
    }

    public void testSingleElement() {
        int[] nums = {7};
        FindMaximumInSortedRotatedArray solver = new FindMaximumInSortedRotatedArray();
        assertEquals(0, solver.searchMax(nums)); // only element
    }

    public void testTwoElementsRotated() {
        int[] nums = {2,1};
        FindMaximumInSortedRotatedArray solver = new FindMaximumInSortedRotatedArray();
        assertEquals(0, solver.searchMax(nums)); // max 2 at index 0
    }

    public void testTwoElementsNotRotated() {
        int[] nums = {1,2};
        FindMaximumInSortedRotatedArray solver = new FindMaximumInSortedRotatedArray();
        assertEquals(1, solver.searchMax(nums)); // max 2 at index 1
    }

    public void testRotateByOne() {
        int[] nums = {2,3,4,5,6,1};
        FindMaximumInSortedRotatedArray solver = new FindMaximumInSortedRotatedArray();
        assertEquals(4, solver.searchMax(nums)); // max 6 at index 4
    }

    public void testRotateLargeEnd() {
        int[] nums = {11,13,15,17,2,5,7,9};
        FindMaximumInSortedRotatedArray solver = new FindMaximumInSortedRotatedArray();
        assertEquals(3, solver.searchMax(nums)); // max 17 at index 3
    }
}

