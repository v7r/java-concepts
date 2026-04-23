 package org.example.algomonster.priorityqueue_heap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class KthSmallestElementInSortedMatrixTest {

    private static List<List<Integer>> of(Integer[]... rows) {
        List<List<Integer>> matrix = new ArrayList<>();
        for (Integer[] r : rows) matrix.add(Arrays.asList(r));
        return matrix;
    }

    //Processing (1,1)->(1)
    //Processing (1,2)->(5)
    //Processing (1,3)->(9)
    //Processing (2,1)->(10)
    //Processing (2,2)->(11)
    //Processing (2,3)->(13)
    @Test
    public void testExampleFromProblem() {
        List<List<Integer>> matrix = of(
                new Integer[]{1, 5, 9},
                new Integer[]{10, 11, 13},
                new Integer[]{12, 13, 15}
        );
        assertEquals(13, KthSmallestElementInSortedMatrix.kthSmallest(matrix, 8));
    }

    @Test
    public void testExample101() {
        List<List<Integer>> matrix = of(
                new Integer[]{ 3,  5,  6,  9},
                new Integer[]{ 4,  6, 10, 11},
                new Integer[]{ 8, 11, 15, 17},
                new Integer[]{10, 13, 18, 20}
        );
        assertEquals(13, KthSmallestElementInSortedMatrix.kthSmallest(matrix, 8));
    }

    @Test
    public void testKEqualsOne() {
        List<List<Integer>> matrix = of(
                new Integer[]{1, 2, 3},
                new Integer[]{4, 5, 6},
                new Integer[]{7, 8, 9}
        );
        assertEquals(1, KthSmallestElementInSortedMatrix.kthSmallest(matrix, 1));
    }

    @Test
    public void testKEqualsN2_returnsMax() {
        List<List<Integer>> matrix = of(
                new Integer[]{1, 5, 9},
                new Integer[]{10, 11, 13},
                new Integer[]{12, 13, 15}
        );
        assertEquals(15, KthSmallestElementInSortedMatrix.kthSmallest(matrix, 9));
    }

    @Test
    public void testSingleElementMatrix() {
        List<List<Integer>> matrix = of(new Integer[]{-5});
        assertEquals(-5, KthSmallestElementInSortedMatrix.kthSmallest(matrix, 1));
    }

    @Test
    public void testAllDuplicatesCounted() {
        List<List<Integer>> matrix = of(
                new Integer[]{1, 1},
                new Integer[]{1, 1}
        );
        // Flatten: [1,1,1,1] => 3rd smallest = 1
        assertEquals(1, KthSmallestElementInSortedMatrix.kthSmallest(matrix, 3));
    }

    @Test
    public void testNegativeAndPositiveValues() {
        List<List<Integer>> matrix = of(
                new Integer[]{-5, -4, -1},
                new Integer[]{0, 3, 5},
                new Integer[]{6, 7, 8}
        );
        assertEquals(3, KthSmallestElementInSortedMatrix.kthSmallest(matrix, 5));
    }

    @Test
    public void testLargerMatrix_vsReferenceSort() {
        List<List<Integer>> matrix = of(
                new Integer[]{2,  6,  8,  12},
                new Integer[]{3,  7,  10, 13},
                new Integer[]{5,  9,  11, 14},
                new Integer[]{16, 18, 20, 22}
        );
        // compute reference via flatten+sort
        List<Integer> flat = matrix.stream().flatMap(List::stream).collect(Collectors.toList());
        flat.sort(Comparator.naturalOrder());
        for (int k = 1; k <= flat.size(); k++) {
            int expected = flat.get(k - 1);
            int got = KthSmallestElementInSortedMatrix.kthSmallest(matrix, k);
            assertEquals(expected, got, "k=" + k);
        }
    }

    @Test
    public void testDoesNotModifyInputMatrix() {
        List<List<Integer>> matrix = of(
                new Integer[]{1, 3, 5},
                new Integer[]{2, 4, 6},
                new Integer[]{7, 8, 9}
        );
        // make a deep copy
        List<List<Integer>> copy = matrix.stream()
                .map(r -> new ArrayList<>(r))
                .collect(Collectors.toList());

        int res = KthSmallestElementInSortedMatrix.kthSmallest(matrix, 4);
        assertEquals(4, res);
        assertEquals(copy, matrix, "input matrix should not be modified");
    }
}

