package org.example.algomonster.priorityqueue_heap_practice;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TaskSchedulerTest {

    @Test
    public void testExample1_givenInComment_n2() {
        String[] tasks = new String[]{"A","A","A","B","B","B"};
        assertEquals(8, TaskScheduler.scheduleTasks(tasks, 2));
    }

    @Test
    public void testExample2_givenInComment_n1() {
        String[] tasks = new String[]{"A","C","A","B","D","B"};
        assertEquals(6, TaskScheduler.scheduleTasks(tasks, 1));
    }

    @Test
    public void testExample3_givenInComment_n3() {
        String[] tasks = new String[]{"A","A","A","B","B","B"};
        assertEquals(10, TaskScheduler.scheduleTasks(tasks, 3));
    }

    @Test
    public void testZeroN_noCooling() {
        String[] tasks = new String[]{"A","A","A","B","B","C"};
        assertEquals(tasks.length, TaskScheduler.scheduleTasks(tasks, 0));
    }

    @Test
    public void testAllDistinct_largeN() {
        String[] tasks = new String[]{"A","B","C","D"};
        assertEquals(tasks.length, TaskScheduler.scheduleTasks(tasks, 50));
    }

    @Test
    public void testSingleTaskManyRepeats_largeN_formula() {
        String[] tasks = new String[]{"A","A","A"};
        int n = 50;
        // expected minimal intervals: (maxCount-1)*(n+1) + numMax
        int maxCount = 3;
        int numMax = 1;
        int expected = (maxCount - 1) * (n + 1) + numMax;
        assertEquals(expected, TaskScheduler.scheduleTasks(tasks, n));
    }

    @Test
    public void testEmptyTasks_returnsZero() {
        String[] tasks = new String[0];
        assertEquals(0, TaskScheduler.scheduleTasks(tasks, 2));
    }

    @Test
    public void testNullTasks_throws() {
        assertThrows(NullPointerException.class, () -> TaskScheduler.scheduleTasks(null, 2));
    }

    @Test
    public void testMultipleMaxFrequencies() {
        String[] tasks = new String[]{"A","A","B","B","C"};
        // counts: A=2, B=2, C=1; with n=2, expected minimal = max(tasks.length, (maxCount-1)*(n+1)+numMax)
        int n = 2;
        int maxCount = 2;
        int numMax = 2; // A and B
        int expected = Math.max(tasks.length, (maxCount - 1) * (n + 1) + numMax);
        assertEquals(expected, TaskScheduler.scheduleTasks(tasks, n));
    }

    @Test
    public void testMultipleMaxFrequencies2() {
        String[] tasks = new String[]{"C","B","B","A", "A", "A"};
        // counts: A=2, B=2, C=1; with n=2, expected minimal = max(tasks.length, (maxCount-1)*(n+1)+numMax)
        int n = 2;
        int maxCount = 3;
        int numMax = 1; // A
        int expected = Math.max(tasks.length, (maxCount - 1) * (n + 1) + numMax);
        assertEquals(expected, TaskScheduler.scheduleTasks(tasks, n));
    }

    @Test
    public void testPriorityQueueImportance() {
        String[] tasks = new String[]{"A","B","B"};
        int n = 1;
        int maxCount = 2;
        int numMax = 1; // B
        int expected = Math.max(tasks.length, (maxCount - 1) * (n + 1) + numMax);
        assertEquals(expected, TaskScheduler.scheduleTasks(tasks, n));
    }

    @Test
    public void testSmallerTasksThanWindow() {
        String[] tasks = new String[]{"A","B"};
        int n = 3;
        int maxCount = 1;
        int numMax = 2; // B
        int expected = Math.max(tasks.length, (maxCount - 1) * (n + 1) + numMax);
        assertEquals(expected, TaskScheduler.scheduleTasks(tasks, n));
    }
}

