package org.example.algomonster.twopointers;

import junit.framework.TestCase;

public class TwoSumIITest extends TestCase {

    public void testExample1() {
        TwoSumII solver = new TwoSumII();
        int[] nums = {2,7,11,15};
        int[] res = solver.twoSum(nums, 9);
        assertEquals(1, res[0]);
        assertEquals(2, res[1]);
    }

    public void testExample2() {
        TwoSumII solver = new TwoSumII();
        int[] nums = {2,3,4};
        int[] res = solver.twoSum(nums, 6);
        assertEquals(1, res[0]);
        assertEquals(3, res[1]);
    }

    public void testExample3() {
        TwoSumII solver = new TwoSumII();
        int[] nums = {-1,0};
        int[] res = solver.twoSum(nums, -1);
        assertEquals(1, res[0]);
        assertEquals(2, res[1]);
    }

    public void testNegativeNumbers() {
        TwoSumII solver = new TwoSumII();
        int[] nums = {-10, -3, 0, 3, 7};
        int[] res = solver.twoSum(nums, -13);
        assertEquals(1, res[0]);
        assertEquals(2, res[1]);
    }

    public void testDuplicatesAdjacent() {
        TwoSumII solver = new TwoSumII();
        int[] nums = {1,1,3,5};
        int[] res = solver.twoSum(nums, 2);
        assertEquals(1, res[0]);
        assertEquals(2, res[1]);
    }

    public void testDuplicatesNonAdjacent() {
        TwoSumII solver = new TwoSumII();
        int[] nums = {1,2,3,4,4,9};
        int[] res = solver.twoSum(nums, 8);
        assertEquals(4, res[0]);
        assertEquals(5, res[1]);
    }

    public void testLargeAndSmallValues() {
        TwoSumII solver = new TwoSumII();
        int[] nums = {-1000, 0, 1000};
        int[] res = solver.twoSum(nums, 0);
        assertEquals(1, res[0]);
        assertEquals(3, res[1]);
    }

    public void testTwoElementsMinimal() {
        TwoSumII solver = new TwoSumII();
        int[] nums = {1,1};
        int[] res = solver.twoSum(nums, 2);
        assertEquals(1, res[0]);
        assertEquals(2, res[1]);
    }

    public void testInteriorSolution() {
        TwoSumII solver = new TwoSumII();
        int[] nums = {1,2,3,4,6};
        int[] res = solver.twoSum(nums, 7);
        assertEquals(1, res[0]);
        assertEquals(5, res[1]);
    }

    public void testNoSolutionReturnsEmpty() {
        TwoSumII solver = new TwoSumII();
        int[] nums = {1,2,3};
        int[] res = solver.twoSum(nums, 7);
        // Implementation returns empty array if no pair found
        assertEquals(0, res.length);
    }
}

