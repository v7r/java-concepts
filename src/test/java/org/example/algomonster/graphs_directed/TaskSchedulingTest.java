package org.example.algomonster.graphs_directed;

import junit.framework.TestCase;

import java.util.*;

/**
 * Tests for TaskScheduling.taskScheduling
 * These tests exercise canonical and edge cases that should be satisfied by a correct topological ordering:
 * - the example from the class comment
 * - disconnected tasks
 * - cycle detection (should return empty list)
 * - duplicate requirements (should be treated as a single prerequisite)
 * - random larger DAG to validate ordering
 *
 * Do NOT modify production logic.
 */
public class TaskSchedulingTest extends TestCase {

    public void testExampleFromCommentProducesExpectedOrder() {
        List<String> tasks = Arrays.asList("a","b","c","d");
        List<List<String>> reqs = Arrays.asList(
                Arrays.asList("a","b"),
                Arrays.asList("c","b"),
                Arrays.asList("b","d")
        );
        List<String> out = TaskScheduling.taskScheduling(tasks, reqs);
        // implementation enqueues tasks in the original tasks order when indegree==0, so expected order is [a,c,b,d]
        List<String> expected = Arrays.asList("a","c","b","d");
        assertEquals("Expected specific ordering for the example", expected, out);
        assertTrue("Order must satisfy all requirements", isTopologicalOrder(tasks, reqs, out));
    }

    public void test101() {
        List<String> tasks = Arrays.asList("a","b","c","d");
        List<List<String>> reqs = Arrays.asList(
                Arrays.asList("a","b"),
                Arrays.asList("a","c"),
                Arrays.asList("b","d"),
                Arrays.asList("c","d")
        );
        List<String> out = TaskScheduling.taskScheduling(tasks, reqs);
        // implementation enqueues tasks in the original tasks order when indegree==0, so expected order is [a,c,b,d]
        List<String> expected = Arrays.asList("a","b","c","d");
        assertEquals("Expected specific ordering for the example", expected, out);
        assertTrue("Order must satisfy all requirements", isTopologicalOrder(tasks, reqs, out));
    }

    public void testDisconnectedTasksProduceValidTopologicalOrder() {
        List<String> tasks = Arrays.asList("a","b","c","d");
        List<List<String>> reqs = Arrays.asList(
                Arrays.asList("a","b"),
                Arrays.asList("c","d")
        );
        List<String> out = TaskScheduling.taskScheduling(tasks, reqs);
        assertEquals("Should produce 4 tasks", 4, out.size());
        assertTrue(isTopologicalOrder(tasks, reqs, out));
    }

    public void testCycleReturnsEmptyList() {
        List<String> tasks = Arrays.asList("a","b");
        List<List<String>> reqs = Arrays.asList(
                Arrays.asList("a","b"),
                Arrays.asList("b","a")
        );
        List<String> out = TaskScheduling.taskScheduling(tasks, reqs);
        assertNotNull(out);
        assertEquals("Cycle should make scheduling impossible and return empty list", 0, out.size());
    }

    public void testDuplicateRequirementsDoNotBreakScheduling() {
        // Problem statement semantics: duplicate requirement edges should not affect result.
        // A correct implementation will treat duplicates idempotently and produce a valid ordering.
        List<String> tasks = Arrays.asList("a","b","c");
        List<List<String>> reqs = Arrays.asList(
                Arrays.asList("a","b"),
                Arrays.asList("a","b"), // duplicate
                Arrays.asList("b","c")
        );
        List<String> out = TaskScheduling.taskScheduling(tasks, reqs);
        assertTrue("Scheduling should succeed despite duplicate requirements", out.size() == tasks.size());
        assertTrue(isTopologicalOrder(tasks, reqs, out));
    }

    public void testLargerRandomDAGOrderingValidity() {
        // build a simple DAG: 0->1->2, 0->3, 3->4
        List<String> tasks = Arrays.asList("t0","t1","t2","t3","t4");
        List<List<String>> reqs = Arrays.asList(
                Arrays.asList("t0","t1"),
                Arrays.asList("t1","t2"),
                Arrays.asList("t0","t3"),
                Arrays.asList("t3","t4")
        );
        List<String> out = TaskScheduling.taskScheduling(tasks, reqs);
        assertEquals(5, out.size());
        assertTrue(isTopologicalOrder(tasks, reqs, out));
    }

    // Helper: verify 'order' contains exactly the tasks and satisfies all reqs (prereq before dependent)
    private boolean isTopologicalOrder(List<String> tasks, List<List<String>> reqs, List<String> order) {
        if (order == null) return false;
        if (order.size() != tasks.size()) return false;
        // same elements
        Set<String> taskSet = new HashSet<>(tasks);
        Set<String> orderSet = new HashSet<>(order);
        if (!taskSet.equals(orderSet)) return false;
        // index map
        Map<String,Integer> index = new HashMap<>();
        for (int i = 0; i < order.size(); i++) index.put(order.get(i), i);
        for (List<String> req : reqs) {
            String a = req.get(0);
            String b = req.get(1);
            Integer ia = index.get(a);
            Integer ib = index.get(b);
            if (ia == null || ib == null) return false;
            if (ia >= ib) return false;
        }
        return true;
    }
}

