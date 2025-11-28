package org.example.algomonster.binarysearch;

import junit.framework.TestCase;

public class FindInRotatedSortedArrayTest extends TestCase {

    public void testExampleTargetFound() {
        int[] nums = {4,5,6,7,0,1,2};
        FindInRotatedSortedArray solver = new FindInRotatedSortedArray();
        assertEquals(4, solver.search(nums, 0));
    }

    public void testExampleTargetNotFound() {
        int[] nums = {4,5,6,7,0,1,2};
        FindInRotatedSortedArray solver = new FindInRotatedSortedArray();
        assertEquals(-1, solver.search(nums, 3));
    }

    public void testSingleElementPresent() {
        int[] nums = {1};
        FindInRotatedSortedArray solver = new FindInRotatedSortedArray();
        assertEquals(0, solver.search(nums, 1));
    }

    public void testSingleElementAbsent() {
        int[] nums = {1};
        FindInRotatedSortedArray solver = new FindInRotatedSortedArray();
        assertEquals(-1, solver.search(nums, 0));
    }

    public void testNoRotationFindsAllPositions() {
        int[] nums = {1,2,3,4,5};
        FindInRotatedSortedArray solver = new FindInRotatedSortedArray();
        assertEquals(0, solver.search(nums, 1));
        assertEquals(2, solver.search(nums, 3));
        assertEquals(4, solver.search(nums, 5));
    }

    public void testRotationVariousPositions() {
        int[] nums = {4,5,6,7,0,1,2};
        FindInRotatedSortedArray solver = new FindInRotatedSortedArray();
        assertEquals(2, solver.search(nums, 6));
        assertEquals(1, solver.search(nums, 5));
        assertEquals(6, solver.search(nums, 2));
    }

    public void testRotationByOne() {
        int[] nums = {2,3,4,5,1};
        FindInRotatedSortedArray solver = new FindInRotatedSortedArray();
        assertEquals(4, solver.search(nums, 1));
        assertEquals(0, solver.search(nums, 2));
    }

    public void testRotationByOneX() {
        int[] nums = {3,4,0,1,2};
        FindInRotatedSortedArray solver = new FindInRotatedSortedArray();
        assertEquals(1, solver.search(nums, 4));
    }

    public void testTwoElements() {
        int[] nums = {2,1};
        FindInRotatedSortedArray solver = new FindInRotatedSortedArray();
        assertEquals(1, solver.search(nums, 1));
        assertEquals(0, solver.search(nums, 2));
        assertEquals(-1, solver.search(nums, 3));
    }

    public void testNegativeNumbersAndRotation() {
        int[] nums = {1,2,-2,-1,0}; // rotated version of [-2,-1,0,1,2]
        FindInRotatedSortedArray solver = new FindInRotatedSortedArray();
        assertEquals(3, solver.search(nums, -1));
        assertEquals(2, solver.search(nums, -2));
        assertEquals(1, solver.search(nums, 2));
    }
}

