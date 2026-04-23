package org.example.algomonster.priorityqueue_heap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KthLargestElementTest {

    @Test
    public void testExample1() {
        List<Integer> nums = Arrays.asList(3, 2, 1, 5, 6, 4);
        int k = 2;
        int got = KthLargestElement.findKthLargest(nums, k);
        assertEquals(5, got);
    }

    @Test
    public void testExample2() {
        List<Integer> nums = Arrays.asList(3,2,3,1,2,4,5,5,6);
        int k = 4;
        int got = KthLargestElement.findKthLargest(nums, k);
        assertEquals(4, got);
    }

    @Test
    public void testKEqualsSize_returnsMin() {
        List<Integer> nums = Arrays.asList(7, 10, 4, 3);
        int k = nums.size();
        int got = KthLargestElement.findKthLargest(nums, k);
        assertEquals(3, got);
    }

    @Test
    public void testKIsOne_returnsMax() {
        List<Integer> nums = Arrays.asList(-1, -2, -3, 0, 5);
        int got = KthLargestElement.findKthLargest(nums, 1);
        assertEquals(5, got);
    }

    @Test
    public void testAllDuplicates() {
        List<Integer> nums = Arrays.asList(1,1,1,1);
        int got = KthLargestElement.findKthLargest(nums, 2);
        assertEquals(1, got);
    }

    @Test
    public void testNegativeNumbers() {
        List<Integer> nums = Arrays.asList(-10, -5, -3, -1);
        int got = KthLargestElement.findKthLargest(nums, 3);
        // Sorted descending: -1, -3, -5, -10 => 3rd largest = -5
        assertEquals(-5, got);
    }

    @Test
    public void testSingleElement() {
        List<Integer> nums = Collections.singletonList(42);
        assertEquals(42, KthLargestElement.findKthLargest(nums, 1));
    }

    @Test
    public void testKGreaterThanSize_returnsMinusOne() {
        List<Integer> nums = Arrays.asList(1,2);
        int got = KthLargestElement.findKthLargest(nums, 5);
        assertEquals(-1, got);
    }

    @Test
    public void testKZero_returnsMinusOne() {
        List<Integer> nums = Arrays.asList(1,2,3);
        int got = KthLargestElement.findKthLargest(nums, 0);
        assertEquals(-1, got);
    }

    @Test
    public void testNullInput_throws() {
        assertThrows(NullPointerException.class, () -> KthLargestElement.findKthLargest(null, 1));
    }

    @Test
    public void testListWithNullElement_throws() {
        List<Integer> nums = Arrays.asList(1, null, 3);
        assertThrows(NullPointerException.class, () -> KthLargestElement.findKthLargest(nums, 2));
    }
}

