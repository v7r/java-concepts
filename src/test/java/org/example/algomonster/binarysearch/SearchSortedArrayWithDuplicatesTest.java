package org.example.algomonster.binarysearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchSortedArrayWithDuplicatesTest {

    private final SearchSortedArrayWithDuplicates solution = new SearchSortedArrayWithDuplicates();

    @Test
    void case1() {
        int[] nums = {5,7,7,8,8,10};
        assertEquals(4, solution.searchLastOccurrence(nums, 8));
    }

    @Test
    void case2() {
        int[] nums = {2,2};
        assertEquals(1, solution.searchLastOccurrence(nums, 2));
    }

    @Test
    void case3() {
        int[] nums = {1,2};
        assertEquals(-1, solution.searchLastOccurrence(nums, 7));
    }
}
