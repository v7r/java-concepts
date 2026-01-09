package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumTest extends TestCase {

    private final ThreeSum solution = new ThreeSum();

    public void testThreeSumEx1() {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(-1,-1,2));
        expected.add(Arrays.asList(-1,0,1));
        List<List<Integer>> result = solution.threeSum(nums);
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected) && expected.containsAll(result));
    }

    public void testThreeSumEx12() {
        int[] nums = {-6,-4,-3,0,2,3,4,7};
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(-6,2,4));
        expected.add(Arrays.asList(-4,0,4));
        expected.add(Arrays.asList(-3,0,3));
        expected.add(Arrays.asList(-4,-3,7));
        List<List<Integer>> result = solution.threeSum(nums);
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected) && expected.containsAll(result));
    }

    public void testThreeSumEx01() {
        int[] nums = {0,0,0,0};
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(0,0,0));
        List<List<Integer>> result = solution.threeSum(nums);
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected) && expected.containsAll(result));
    }

    public void testThreeSumEx11() {
        int[] nums = {1,2,3,4,5};
        List<List<Integer>> expected = new ArrayList<>();
        List<List<Integer>> result = solution.threeSum(nums);
        assertEquals(0, result.size());
        assertTrue(result.containsAll(expected) && expected.containsAll(result));
    }

    public void testThreeSumEx2() {
        int[] nums = {};
        List<List<Integer>> expected = new ArrayList<>();
        List<List<Integer>> result = solution.threeSum(nums);
        assertEquals(0, result.size());
        assertTrue(result.containsAll(expected) && expected.containsAll(result));
    }

    public void testThreeSumEx3() {
        int[] nums = {0};
        List<List<Integer>> expected = new ArrayList<>();
        List<List<Integer>> result = solution.threeSum(nums);
        assertEquals(0, result.size());
        assertTrue(result.containsAll(expected) && expected.containsAll(result));
    }
}
