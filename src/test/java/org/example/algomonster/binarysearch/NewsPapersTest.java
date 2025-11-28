package org.example.algomonster.binarysearch;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NewsPapersTest extends TestCase {

    public void testExample1() {
        List<Integer> times = Arrays.asList(7, 2, 5, 10, 8);
        int workers = 2;
        int expected = 18;
        assertEquals(expected, NewsPapers.newspapersSplit(times, workers));
    }

    public void testExample2() {
        List<Integer> times = Arrays.asList(2, 3, 5, 7);
        int workers = 3;
        int expected = 7;
        assertEquals(expected, NewsPapers.newspapersSplit(times, workers));
    }

    public void testSingleNewspaperManyWorkers() {
        List<Integer> times = Collections.singletonList(5);
        int workers = 10;
        int expected = 5; // only one paper, time equals its read time
        assertEquals(expected, NewsPapers.newspapersSplit(times, workers));
    }

    public void testOneWorkerReadsAll() {
        List<Integer> times = Arrays.asList(7, 2, 5, 10, 8);
        int workers = 1;
        int expected = 7 + 2 + 5 + 10 + 8; // sum
        assertEquals(expected, NewsPapers.newspapersSplit(times, workers));
    }

    public void testWorkersMoreThanPapers() {
        List<Integer> times = Arrays.asList(1,2,3);
        int workers = 5; // more workers than papers
        int expected = 3; // constrained by largest single paper
        assertEquals(expected, NewsPapers.newspapersSplit(times, workers));
    }

    public void testEmptyListThrows() {
        List<Integer> times = Collections.emptyList();
        try {
            NewsPapers.newspapersSplit(times, 3);
            fail("Expected NoSuchElementException for empty list");
        } catch (java.util.NoSuchElementException expected) {
            // expected
        }
    }

    public void testLargeValues() {
        List<Integer> times = Arrays.asList(100000, 100000, 100000);
        int workers = 2;
        // best split is [100000,100000] and [100000] => max = 200000
        int expected = 200000;
        assertEquals(expected, NewsPapers.newspapersSplit(times, workers));
    }
}
