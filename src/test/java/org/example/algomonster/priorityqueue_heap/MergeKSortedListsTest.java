package org.example.algomonster.priorityqueue_heap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MergeKSortedListsTest {

    private static List<Integer> asList(Integer... vals) {
        return new ArrayList<>(Arrays.asList(vals));
    }

    @Test
    public void testMergeBasic() {
        List<List<Integer>> input = Arrays.asList(
                asList(1, 3, 5),
                asList(2, 4, 6),
                asList(7, 10)
        );
        List<Integer> expected = asList(1, 2, 3, 4, 5, 6, 7, 10);
        List<Integer> got = MergeKSortedLists.mergeKSortedLists(input);
        assertEquals(expected, got);
    }

    @Test
    public void testWithEmptyList() {
        List<List<Integer>> input = Arrays.asList(
                asList(1, 2),
                Collections.emptyList(),
                asList(0, 3)
        );
        List<Integer> expected = asList(0, 1, 2, 3);
        List<Integer> got = MergeKSortedLists.mergeKSortedLists(input);
        assertEquals(expected, got);
    }

    @Test
    public void testAllEmpty() {
        List<List<Integer>> input = Arrays.asList(
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );
        List<Integer> got = MergeKSortedLists.mergeKSortedLists(input);
        assertTrue(got.isEmpty());
    }

    @Test
    public void testDuplicatesAcrossLists() {
        List<List<Integer>> input = Arrays.asList(
                asList(1, 1, 2),
                asList(1, 3),
                asList(2, 2)
        );
        List<Integer> expected = asList(1, 1, 1, 2, 2, 2, 3);
        List<Integer> got = MergeKSortedLists.mergeKSortedLists(input);
        assertEquals(expected, got);
    }

    @Test
    public void testNegativeNumbersAndMixedLengths() {
        List<List<Integer>> input = Arrays.asList(
                asList(-3, -1),
                asList(-2, 0),
                asList(1)
        );
        List<Integer> expected = asList(-3, -2, -1, 0, 1);
        List<Integer> got = MergeKSortedLists.mergeKSortedLists(input);
        assertEquals(expected, got);
    }

    @Test
    public void testSingleList() {
        List<List<Integer>> input = Arrays.asList(
                asList(5, 6, 7)
        );
        List<Integer> expected = asList(5, 6, 7);
        List<Integer> got = MergeKSortedLists.mergeKSortedLists(input);
        assertEquals(expected, got);
    }

    @Test
    public void testNullInnerList_throws() {
        List<List<Integer>> input = Arrays.asList(
                asList(1, 2),
                null
        );
        assertThrows(NullPointerException.class, () -> MergeKSortedLists.mergeKSortedLists(input));
    }
}

