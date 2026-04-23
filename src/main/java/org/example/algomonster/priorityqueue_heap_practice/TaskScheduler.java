package org.example.algomonster.priorityqueue_heap_practice;

import java.util.*;

/**
 * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n. Each CPU interval can
 * be idle or allow the completion of one task. Tasks can be completed in any order, but there's a constraint: there
 * has to be a gap of at least n intervals between two tasks with the same label.
 *
 * Return the minimum number of CPU intervals required to complete all tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 *
 * Output: 8
 *
 * Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
 *
 * After completing task A, you must wait two intervals before doing A again. The same applies to task B. In the 3rd
 * interval, neither A nor B can be done, so you idle. By the 4th interval, you can do A again as 2 intervals have
 * passed.
 *
 * Example 2:
 *
 * Input: tasks = ["A","C","A","B","D","B"], n = 1
 *
 * Output: 6
 *
 * Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
 *
 * With a cooling interval of 1, you can repeat a task after just one other task.
 *
 * Example 3:
 *
 * Input: tasks = ["A","A","A", "B","B","B"], n = 3
 *
 * Output: 10
 *
 * Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
 *
 * There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice
 * between repetitions of these tasks.
 *
 *
 *
 * Constraints:
 *
 * 1 <= tasks.length <= 104
 * tasks[i] is an uppercase English letter.
 * 0 <= n <= 100
 */
public class TaskScheduler {
    // waitQ - taskGroups waiting to enter into limiterQ
    // limiterQ of size n at-max
    // holdBackQ - taskGroup once scheduled will wait here until next available clock.

    // scheduledTasks
    //
    public static int scheduleTasks(String[] tasks, int n) {
        if (tasks.length == 0) return 0;
        Map<String, Integer> taskGroup = new HashMap<>();
        for (String task : tasks) {
            taskGroup.compute(task, (k,v) -> v == null ? 1 : ++v);
        }
        Queue<Map.Entry<String, Integer>> q = new PriorityQueue<>(
                (e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));
        for (Map.Entry<String, Integer> e : taskGroup.entrySet()) {
            q.offer(e);
        }
        Queue<Map.Entry<String, Integer>> limiterQ = new ArrayDeque<>();
        int i = 0;
        // Fill the limiterQ with n tasks.
        while (i <= n) {
            Map.Entry<String, Integer> e = q.poll();
            if (e == null) {
                e = new AbstractMap.SimpleEntry<>("idle", 1);
            }
            limiterQ.add(e);
            i++;
        }

        int taskCount = 0;
        List<String> taskSchedule = new ArrayList<>();
        while (!limiterQ.isEmpty()) {
            Map.Entry<String, Integer> current = limiterQ.poll();

            if (!current.getKey().equals("idle")) taskCount++;
            taskSchedule.add(current.getKey());
            current.setValue(current.getValue() - 1);

            // Break when all the tasks are scheduled.
            if (taskCount == tasks.length) break;

            // idle tasks will never enter into holdBack;
            if (current.getValue() > 0) {
                q.add(current);
            }
            Map.Entry<String, Integer> next = q.poll();
            if (next == null) {
                next = new AbstractMap.SimpleEntry<>("idle",1);
            }
            limiterQ.add(next);
        }
        return taskSchedule.size();
    }

    // group tasks;
    // Queue all the groups into q
    // until the q is not empty
    //      poll next group from q and add it to holdBack queue if more tasks left to process.
    //      if q is empty and holdBack queue is not empty
    //          if n == holdBack queue size
    //
    public static int scheduleTasksV1(String[] tasks, int n) {
        Map<String, Integer> taskGroup = new HashMap<>();
        for (String task : tasks) {
            taskGroup.compute(task, (k,v) -> v == null ? 1 : ++v);
        }
        ArrayDeque<Map.Entry<String, Integer>> holdBack = new ArrayDeque<>();
        ArrayDeque<Map.Entry<String, Integer>> q = new ArrayDeque<>();
        for (Map.Entry<String, Integer> e : taskGroup.entrySet()) {
            q.offer(e);
        }
        // AAABBB; 3
        // 0;A3,B3;
        // A,B,;A2;B3;

        int idleCount = 0;
        List<String> taskSchedule = new ArrayList<>();
        while (!q.isEmpty() || !holdBack.isEmpty()) {
            Map.Entry<String, Integer> current = q.poll();

            taskSchedule.add(current.getKey());
            current.setValue(current.getValue() - 1);

            if (current.getKey().equals("idle")) idleCount++;

            // idle tasks will never enter into holdBack;
            if (current.getValue() > 0) {
                holdBack.add(current);
            }
            // if enough idle cycles are scheduled then we can unhold the task group and schedule next;
            if (!holdBack.isEmpty() && holdBack.size() + idleCount%n == 0) {
                // release to q
                q.add(holdBack.poll());
                idleCount--;
            } else if (q.isEmpty() && !holdBack.isEmpty()) {
                // not enough idle cycles are elapsed and no other tasks to schedule.
                // So schedule idle cycle to spend the interleave time.
                q.add(new AbstractMap.SimpleEntry<String, Integer>("idle", 1));
            }
        }
        return taskSchedule.size();
    }
}
