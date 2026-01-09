package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Arrays;

public class SortColorsTest extends TestCase {

    private void assertArrayEquals(String msg, int[] expected, int[] actual) {
        assertTrue(msg + ": expected=" + Arrays.toString(expected) + " actual=" + Arrays.toString(actual),
                Arrays.equals(expected, actual));
    }

    public void testExample1() {
        int[] nums = {2,0,2,1,1,0};
        new SortColors().sortColors(nums);
        assertArrayEquals("example1", new int[]{0,0,1,1,2,2}, nums);
    }

    public void testExample2() {
        int[] nums = {2,0,1};
        new SortColors().sortColors(nums);
        assertArrayEquals("example2", new int[]{0,1,2}, nums);
    }

    public void testAllZeros() {
        int[] nums = {0,0,0,0};
        new SortColors().sortColors(nums);
        assertArrayEquals("allZeros", new int[]{0,0,0,0}, nums);
    }

    public void testAllOnes() {
        int[] nums = {1,1,1};
        new SortColors().sortColors(nums);
        assertArrayEquals("allOnes", new int[]{1,1,1}, nums);
    }

    public void testAllTwos() {
        int[] nums = {2,2,2,2};
        new SortColors().sortColors(nums);
        assertArrayEquals("allTwos", new int[]{2,2,2,2}, nums);
    }

    public void testAlreadySorted() {
        int[] nums = {0,0,1,1,2,2};
        new SortColors().sortColors(nums);
        assertArrayEquals("alreadySorted", new int[]{0,0,1,1,2,2}, nums);
    }

    public void testReverseSorted() {
        int[] nums = {2,2,1,1,0,0};
        new SortColors().sortColors(nums);
        assertArrayEquals("reverseSorted", new int[]{0,0,1,1,2,2}, nums);
    }

    public void testOnlyZerosAndTwos() {
        int[] nums = {2,0,2,0,0,2};
        new SortColors().sortColors(nums);
        assertArrayEquals("zerosAndTwos", new int[]{0,0,0,2,2,2}, nums);
    }

    public void testSingleElementZero() {
        int[] nums = {0};
        new SortColors().sortColors(nums);
        assertArrayEquals("singleZero", new int[]{0}, nums);
    }

    public void testSingleElementOne() {
        int[] nums = {1};
        new SortColors().sortColors(nums);
        assertArrayEquals("singleOne", new int[]{1}, nums);
    }

    public void testSingleElementTwo() {
        int[] nums = {2};
        new SortColors().sortColors(nums);
        assertArrayEquals("singleTwo", new int[]{2}, nums);
    }

    public void testTwoElementsVarious() {
        int[] a = {1,0};
        new SortColors().sortColors(a);
        assertArrayEquals("two-1-0", new int[]{0,1}, a);

        int[] b = {2,0};
        new SortColors().sortColors(b);
        assertArrayEquals("two-2-0", new int[]{0,2}, b);

        int[] c = {2,1};
        new SortColors().sortColors(c);
        assertArrayEquals("two-2-1", new int[]{1,2}, c);
    }

    public void testManyRandomCombinations() {
        int[] nums = {0,2,1,2,0,1,2,0,1,0,2,1};
        new SortColors().sortColors(nums);
        assertArrayEquals("randomCombos", new int[]{0,0,0,0,1,1,1,1,2,2,2,2}, nums);
    }
}

