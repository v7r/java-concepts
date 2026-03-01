package org.example.algomonster.graphs_directed;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

/**
 * Tests for TaskScheduling2.taskScheduling2
 * These tests are designed to follow the problem statement and to challenge common incorrect greedy
 * implementations (for example, scheduling indegree-zero tasks one-by-one instead of considering
 * parallel execution / longest path dependency).
 *
 * Do NOT modify production logic; tests assert the expected (correct) minimal completion times.
 */
public class TaskScheduling2Test extends TestCase {

    public void testExampleFromComment() {
        List<String> tasks = Arrays.asList("a","b","c","d");
        List<Integer> times = Arrays.asList(1,1,2,1);
        List<List<String>> reqs = Arrays.asList(
                Arrays.asList("a","b"),
                Arrays.asList("c","b"),
                Arrays.asList("b","d")
        );
        // longest chain: c(2) -> b(1) -> d(1) = 4
        int res = TaskScheduling2.taskScheduling2(tasks, times, reqs);
        assertEquals(4, res);
    }

    public void testParallelAfterSinglePredecessor() {
        // Connected graph where a is predecessor for both b and c: a->b and a->c; c->d
        // a runs 3, then b(3) and c(4) can run in parallel; c then triggers d(1)
        // finish times: b finishes at 6, c finishes at 7, d finishes at 8 => total = 8
        List<String> tasks = Arrays.asList("a","b","c","d");
        List<Integer> times = Arrays.asList(3,3,4,1);
        List<List<String>> reqs = Arrays.asList(
                Arrays.asList("a","b"),
                Arrays.asList("a","c"),
                Arrays.asList("c","d")
        );
        int res = TaskScheduling2.taskScheduling2(tasks, times, reqs);
        assertEquals(8, res);
    }

    public void testTaskWithMultiplePrerequisites() {
        // d depends on both a and b. durations: a=3, b=2, d=1
        // a and b can run in parallel, so d can start after max(a,b) finishes.
        // minimal total time = max(3,2) + 1 = 4
        List<String> tasks = Arrays.asList("a","b","d");
        List<Integer> times = Arrays.asList(3,2,1);
        List<List<String>> reqs = Arrays.asList(
                Arrays.asList("a","d"),
                Arrays.asList("b","d")
        );
        int res = TaskScheduling2.taskScheduling2(tasks, times, reqs);
        assertEquals(4, res);
    }

    public void testCommonPredecessorForChainAndIndependentTask() {
        // a is common predecessor for both b and c. chain is a->b, and a->c.
        // durations: a=2, b=2, c=3 => after a finishes at 2, b and c run in parallel
        // finish times: b at 4, c at 5 => total = 5
        List<String> tasks = Arrays.asList("a","b","c");
        List<Integer> times = Arrays.asList(2,2,3);
        List<List<String>> reqs = Arrays.asList(
                Arrays.asList("a","b"),
                Arrays.asList("a","c")
        );
        int res = TaskScheduling2.taskScheduling2(tasks, times, reqs);
        assertEquals(5, res);
    }

    public void testLongerChainDominatesAndConnectsOthers() {
        // Chain x->y->z dominates; connect z->p->q so graph is connected.
        // durations x=1,y=2,z=3,p=1,q=1 => total = 1+2+3+1+1 = 8
        List<String> tasks = Arrays.asList("x","y","z","p","q");
        List<Integer> times = Arrays.asList(1,2,3,1,1);
        List<List<String>> reqs = Arrays.asList(
                Arrays.asList("x","y"),
                Arrays.asList("y","z"),
                Arrays.asList("z","p"),
                Arrays.asList("p","q")
        );
        int res = TaskScheduling2.taskScheduling2(tasks, times, reqs);
        assertEquals(8, res);
    }

    public void testDisconnectedMaxPath() {
        List<String> tasks = Arrays.asList("abbreviate", "bricks", "cardinals", "dextrous", "fibre", "green", "height");
        List<Integer> times = Arrays.asList(1, 1, 1, 1, 1, 100, 150);
        List<List<String>> reqs = Arrays.asList(
                Arrays.asList("abbreviate", "bricks"),
                Arrays.asList("cardinals","bricks"),
                Arrays.asList("dextrous","bricks"),
                Arrays.asList("bricks","fibre"),
                Arrays.asList("green","height")
        );
        int res = TaskScheduling2.taskScheduling2(tasks, times, reqs);
        assertEquals(250, res);
    }
}
