package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class MoveZerosTest extends TestCase {

    public void testMoveZerosToLastTypical() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 0, 2, 0, 0, 7));
        MoveZeros.moveZerosToLast(nums);
        assertEquals(Arrays.asList(1,2,7,0,0,0), nums);
    }

    public void testAlreadyNoZeros() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1,2,3));
        MoveZeros.moveZerosToLast(nums);
        assertEquals(Arrays.asList(1,2,3), nums);
    }

    public void testAllZeros() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(0,0,0,0));
        MoveZeros.moveZerosToLast(nums);
        assertEquals(Arrays.asList(0,0,0,0), nums);
    }

    public void testZerosAtEnd() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1,2,0,0));
        MoveZeros.moveZerosToLast(nums);
        assertEquals(Arrays.asList(1,2,0,0), nums);
    }

    public void testZerosAtFront() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(0,0,1,2));
        MoveZeros.moveZerosToLast(nums);
        assertEquals(Arrays.asList(1,2,0,0), nums);
    }

    public void testAlternatingZeros() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(0,1,0,2,0,3));
        MoveZeros.moveZerosToLast(nums);
        assertEquals(Arrays.asList(1,2,3,0,0,0), nums);
    }

    public void testSingleElementZero() {
        List<Integer> nums = new ArrayList<>(Collections.singletonList(0));
        MoveZeros.moveZerosToLast(nums);
        assertEquals(Collections.singletonList(0), nums);
    }

    public void testSingleElementNonZero() {
        List<Integer> nums = new ArrayList<>(Collections.singletonList(5));
        MoveZeros.moveZerosToLast(nums);
        assertEquals(Collections.singletonList(5), nums);
    }

    public void testEmptyList() {
        List<Integer> nums = new ArrayList<>();
        MoveZeros.moveZerosToLast(nums);
        assertEquals(Collections.emptyList(), nums);
    }

    // Tests for moveZerosToFirst (zeros moved to the front)

    public void testMoveZerosToFirstTypical() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 0, 2, 0, 0, 7));
        MoveZeros.moveZerosToFirst(nums);
        assertEquals(Arrays.asList(0,0,0,1,2,7), nums);
    }

    public void testMoveZerosToFirstAlreadyFront() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(0,0,1,2));
        MoveZeros.moveZerosToFirst(nums);
        assertEquals(Arrays.asList(0,0,1,2), nums);
    }

    public void testMoveZerosToFirstAllZeros() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(0,0,0));
        MoveZeros.moveZerosToFirst(nums);
        assertEquals(Arrays.asList(0,0,0), nums);
    }

    public void testMoveZerosToFirstNoZeros() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1,2,3));
        MoveZeros.moveZerosToFirst(nums);
        assertEquals(Arrays.asList(1,2,3), nums);
    }

    public void testMoveZerosToFirstAlternating() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(0,1,0,2,0,3));
        MoveZeros.moveZerosToFirst(nums);
        assertEquals(Arrays.asList(0,0,0,1,2,3), nums);
    }

    public void testMoveZerosToFirstSingleZero() {
        List<Integer> nums = new ArrayList<>(Collections.singletonList(0));
        MoveZeros.moveZerosToFirst(nums);
        assertEquals(Collections.singletonList(0), nums);
    }

    public void testMoveZerosToFirstSingleNonZero() {
        List<Integer> nums = new ArrayList<>(Collections.singletonList(5));
        MoveZeros.moveZerosToFirst(nums);
        assertEquals(Collections.singletonList(5), nums);
    }

    public void testMoveZerosToFirstEmpty() {
        List<Integer> nums = new ArrayList<>();
        MoveZeros.moveZerosToFirst(nums);
        assertEquals(Collections.emptyList(), nums);
    }
}
