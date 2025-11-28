package org.example.algomonster.binarysearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class FindFirstAndLastTest {

    private final FindFirstAndLast solution = new FindFirstAndLast();

    @Test
    void testSearchRange1() {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        int[] expected = {3,4};
        assertArrayEquals(expected, solution.searchRange(nums, target), "Failed for nums = " + java.util.Arrays.toString(nums) + ", target = " + target + ", expected = " + java.util.Arrays.toString(expected));
    }
    @Test
    void testSearchRange2() {
        int[] nums = {5,7,7,8,8,10};
        int target = 6;
        int[] expected = {-1,-1};
        assertArrayEquals(expected, solution.searchRange(nums, target), "Failed for nums = " + java.util.Arrays.toString(nums) + ", target = " + target + ", expected = " + java.util.Arrays.toString(expected));
    }
    @Test
    void testSearchRange3() {
        int[] nums = {};
        int target = 0;
        int[] expected = {-1,-1};
        assertArrayEquals(expected, solution.searchRange(nums, target), "Failed for nums = " + java.util.Arrays.toString(nums) + ", target = " + target + ", expected = " + java.util.Arrays.toString(expected));
    }

    @Test
    void testSearchRange4() {
        int[] nums = {5,7,7,8,8,10};
        int target = 5;
        int[] expected = {0,0};
        assertArrayEquals(expected, solution.searchRange(nums, target), "Failed for nums = " + java.util.Arrays.toString(nums) + ", target = " + target + ", expected = " + java.util.Arrays.toString(expected));
    }

    @Test
    void testSearchRange5() {
        int[] nums = {5,7,7,8,8,10};
        int target = 10;
        int[] expected = {5,5};
        assertArrayEquals(expected, solution.searchRange(nums, target), "Failed for nums = " + java.util.Arrays.toString(nums) + ", target = " + target + ", expected = " + java.util.Arrays.toString(expected));
    }

    @Test
    void testSearchRange6() {
        int[] nums = {8,8,8,8,8,8};
        int target = 8;
        int[] expected = {0,5};
        assertArrayEquals(expected, solution.searchRange(nums, target), "Failed for nums = " + java.util.Arrays.toString(nums) + ", target = " + target + ", expected = " + java.util.Arrays.toString(expected));
    }

    @Test
    void testSearchRange7() {
        int[] nums = {8};
        int target = 8;
        int[] expected = {0,0};
        assertArrayEquals(expected, solution.searchRange(nums, target), "Failed for nums = " + java.util.Arrays.toString(nums) + ", target = " + target + ", expected = " + java.util.Arrays.toString(expected));
    }

    @Test
    void testSearchRange8() {
        int[] nums = {7};
        int target = 8;
        int[] expected = {-1,-1};
        assertArrayEquals(expected, solution.searchRange(nums, target), "Failed for nums = " + java.util.Arrays.toString(nums) + ", target = " + target + ", expected = " + java.util.Arrays.toString(expected));
    }










}
