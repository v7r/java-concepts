package org.example.algomonster.binarysearch;

import junit.framework.TestCase;

public class SingleElementInSortedArrayTest extends TestCase {

    public void testExample1() {
        int[] nums = {1,1,2,3,3,4,4,8,8};
        SingleElementInSortedArray solver = new SingleElementInSortedArray();
        assertEquals(2, solver.singleNonDuplicate(nums));
    }

    public void testExample2() {
        int[] nums = {3,3,7,7,10,11,11};
        SingleElementInSortedArray solver = new SingleElementInSortedArray();
        assertEquals(10, solver.singleNonDuplicate(nums));
    }

    public void testSingleElementOnly() {
        int[] nums = {42};
        SingleElementInSortedArray solver = new SingleElementInSortedArray();
        assertEquals(42, solver.singleNonDuplicate(nums));
    }

    public void testSingleAtStart() {
        int[] nums = {2,3,3,4,4,5,5};
        SingleElementInSortedArray solver = new SingleElementInSortedArray();
        assertEquals(2, solver.singleNonDuplicate(nums));
    }

    public void testSingleAtEnd() {
        int[] nums = {1,1,2,2,3,3,9};
        SingleElementInSortedArray solver = new SingleElementInSortedArray();
        assertEquals(9, solver.singleNonDuplicate(nums));
    }

    public void testLargerArray() {
        int[] nums = {0,0,1,1,2,2,3,3,4,4,5,7,7,8,8};
        SingleElementInSortedArray solver = new SingleElementInSortedArray();
        assertEquals(5, solver.singleNonDuplicate(nums));
    }

    public void testMinimumLengthThree() {
        int[] nums = {1,1,2};
        SingleElementInSortedArray solver = new SingleElementInSortedArray();
        assertEquals(2, solver.singleNonDuplicate(nums));
    }
}

