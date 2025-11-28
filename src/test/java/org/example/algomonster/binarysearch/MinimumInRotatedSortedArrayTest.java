package org.example.algomonster.binarysearch;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumInRotatedSortedArrayTest {

    @Test
    void rotatedExample() {
        List<Integer> arr = Arrays.asList(30, 40, 50, 10, 20);
        assertEquals(3, MinimumInRotatedSortedArray.findMinRotated(arr));
    }

    @Test
    void longRotatedExample() {
        List<Integer> arr = Arrays.asList(3, 5, 7, 11, 13, 17, 19, 2);
        assertEquals(7, MinimumInRotatedSortedArray.findMinRotated(arr));
    }

    @Test
    void unrotatedSortedReturnsZero() {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(0, MinimumInRotatedSortedArray.findMinRotated(arr));
    }

    @Test
    void singleElementReturnsZero() {
        List<Integer> arr = Collections.singletonList(5);
        assertEquals(0, MinimumInRotatedSortedArray.findMinRotated(arr));
    }

    @Test
    void emptyListReturnsMinusOne() {
        List<Integer> arr = Collections.emptyList();
        assertEquals(-1, MinimumInRotatedSortedArray.findMinRotated(arr));
    }

    @Test
    void twoElementsRotated() {
        List<Integer> arr = Arrays.asList(2, 1);
        assertEquals(1, MinimumInRotatedSortedArray.findMinRotated(arr));
    }

    @Test
    void minAtLastIndex() {
        List<Integer> arr = Arrays.asList(4, 5, 6, 1);
        assertEquals(3, MinimumInRotatedSortedArray.findMinRotated(arr));
    }

    @Test
    void negativesAndPositives() {
        List<Integer> arr = Arrays.asList(-3, -1, 0, 2, -5);
        assertEquals(4, MinimumInRotatedSortedArray.findMinRotated(arr));
    }
}

