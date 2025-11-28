package org.example.algomonster.binarysearch;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstNonSimilarTest {

    @Test
    void typicalBetweenValues() {
        List<Integer> arr = Arrays.asList(1, 3, 5, 7);
        assertEquals(2, FirstNonSimilar.firstNotSmaller(arr, 4));
    }

    @Test
    void duplicatesFirstOfRepeatedValues() {
        List<Integer> arr = Arrays.asList(1, 3, 3, 5, 8);
        assertEquals(1, FirstNonSimilar.firstNotSmaller(arr, 3));
    }

    @Test
    void exactMatchMiddle() {
        List<Integer> arr = Arrays.asList(2, 4, 6, 8);
        assertEquals(2, FirstNonSimilar.firstNotSmaller(arr, 6));
    }

    @Test
    void targetLessThanMin() {
        List<Integer> arr = Arrays.asList(5, 9, 12);
        assertEquals(0, FirstNonSimilar.firstNotSmaller(arr, 1));
    }

    @Test
    void targetGreaterThanMax() {
        List<Integer> arr = Arrays.asList(2, 4, 6);
        assertEquals(-1, FirstNonSimilar.firstNotSmaller(arr, 10));
    }

    @Test
    void emptyListReturnsMinusOne() {
        List<Integer> arr = Collections.emptyList();
        assertEquals(-1, FirstNonSimilar.firstNotSmaller(arr, 3));
    }

    @Test
    void singleElementEqual() {
        List<Integer> arr = Collections.singletonList(5);
        assertEquals(0, FirstNonSimilar.firstNotSmaller(arr, 5));
    }

    @Test
    void singleElementSmaller() {
        List<Integer> arr = Collections.singletonList(5);
        assertEquals(-1, FirstNonSimilar.firstNotSmaller(arr, 6));
    }

    @Test
    void allElementsSameTargetEqualsValue() {
        List<Integer> arr = Arrays.asList(2, 2, 2, 2);
        assertEquals(0, FirstNonSimilar.firstNotSmaller(arr, 2));
    }

    @Test
    void targetEqualsLastElement() {
        List<Integer> arr = Arrays.asList(1, 4, 6, 9);
        assertEquals(3, FirstNonSimilar.firstNotSmaller(arr, 9));
    }
}

