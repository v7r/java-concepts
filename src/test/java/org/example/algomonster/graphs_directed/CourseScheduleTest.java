package org.example.algomonster.graphs_directed;

import junit.framework.TestCase;

import java.util.*;

/**
 * Tests for CourseSchedule.isValidCourseSchedule
 * These tests exercise canonical examples and edge cases to challenge the implementation logic.
 * Do NOT modify production logic.
 */
public class CourseScheduleTest extends TestCase {

    public void testSimpleAcyclic() {
        int n = 2;
        List<List<Integer>> prereqs = Arrays.asList(
                Arrays.asList(0,1) // 1 -> 0
        );
        assertTrue(CourseSchedule.isValidCourseSchedule(n, prereqs));
    }

    public void testSimpleCycle() {
        int n = 2;
        List<List<Integer>> prereqs = Arrays.asList(
                Arrays.asList(0,1),
                Arrays.asList(1,0)
        );
        assertFalse(CourseSchedule.isValidCourseSchedule(n, prereqs));
    }

    public void testSelfDependency() {
        int n = 1;
        List<List<Integer>> prereqs = Collections.singletonList(
                Arrays.asList(0,0)
        );
        assertFalse(CourseSchedule.isValidCourseSchedule(n, prereqs));
    }

    public void testDisconnectedNodes() {
        int n = 4;
        List<List<Integer>> prereqs = Arrays.asList(
                Arrays.asList(1,0),
                Arrays.asList(2,0) // node 3 is isolated
        );
        assertTrue(CourseSchedule.isValidCourseSchedule(n, prereqs));
    }

    public void testComplexCycleAmongSubset() {
        int n = 5;
        List<List<Integer>> prereqs = Arrays.asList(
                Arrays.asList(1,0),
                Arrays.asList(2,1),
                Arrays.asList(0,2), // 0->1->2->0 cycle
                Arrays.asList(3,4)
        );
        assertFalse(CourseSchedule.isValidCourseSchedule(n, prereqs));
    }

    public void testDuplicateEdges() {
        int n = 3;
        List<List<Integer>> prereqs = Arrays.asList(
                Arrays.asList(1,0),
                Arrays.asList(1,0), // duplicate
                Arrays.asList(2,1)
        );
        assertTrue(CourseSchedule.isValidCourseSchedule(n, prereqs));
    }

    public void testEmptyPrerequisites() {
        int n = 5;
        List<List<Integer>> prereqs = Collections.emptyList();
        assertTrue(CourseSchedule.isValidCourseSchedule(n, prereqs));
    }

    public void testLongLinearChain() {
        int n = 10;
        List<List<Integer>> prereqs = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            prereqs.add(Arrays.asList(i, i-1)); // (i depends on i-1)
        }
        assertTrue(CourseSchedule.isValidCourseSchedule(n, prereqs));
    }
}

