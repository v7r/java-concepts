package org.example.algomonster.graphs_directed;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This problem is similar to TaskScheduling. The primary difference is now we assign times to tasks and we ask for
 * the minimum amount of time to complete all tasks. There will be an extra times array such that times[i] indicates
 * the time required to complete task[i]. You have also invited all your friends to help complete your tasks so you
 * can work on any amount of tasks at a given time. Remember that task a must be completed before completing task b
 * (but the starting times don't need to be in order).
 *
 * There is guaranteed to be a solution.
 *
 * Examples
 * Example 1
 * Input:
 * tasks = ["a", "b", "c", "d"]
 * times = [1, 1, 2, 1]
 * requirements = [["a", "b"], ["c", "b"], ["b", "d"]]
 * Output: 4
 *
 * Figure
 *  <img src="./imgs/TaskScheduling2.png" />
 *
 * Example 1 dependency graph with task durations
 *
 * The longest dependency chain is c -> b -> d, so the minimum total time is 2 + 1 + 1 = 4.
 */
public class TaskScheduling2 {
    private static String findMaxTimeTask(List<String> tasks, Map<String, Integer> taskTimeMap) {
        String maxTimeTask = null;
        int maxTime = 0;
        for (String task : tasks) {
            if (taskTimeMap.get(task) > maxTime) {
                maxTime = taskTimeMap.get(task);
                maxTimeTask = task;
            }
        }
        return maxTimeTask;
    }

    //a b c d
    //1 1 2 1
    //3
    //a b
    //c b
    //b d
    public static int taskScheduling2(List<String> tasks, List<Integer> times, List<List<String>> requirements) {
        int minTime = 0;
        Map<String, Integer> taskTimeMap = new HashMap<>();
        Map<String, List<String>> inDegree = new HashMap<>();
        Map<String, List<String>> outDegree = new HashMap<>();
        for (int i = 0; i < tasks.size(); i++) {
            taskTimeMap.put(tasks.get(i),times.get(i));
            inDegree.put(tasks.get(i),new ArrayList<>());
            outDegree.put(tasks.get(i),new ArrayList<>());
        }
        for (List<String> req : requirements) {
            for (int i = 0; i < req.size()-1; i++) {
                String current = req.get(i);
                String next = req.get(i+1);
                outDegree.get(current).add(next);
                inDegree.get(next).add(current);
            }
        }

        // find the empty inorder tasks. ( independent tasks )
        List<String> edgeTasks = inDegree.entrySet().stream()
                .filter(e -> e.getValue().isEmpty())
                .map(e -> e.getKey())
                .collect(Collectors.toList());
        ArrayDeque<String> q = new ArrayDeque<>();
        q.addAll(edgeTasks);

        List<String> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            //String currentTask = q.poll();
            int n = q.size();
            // of this edges find the max and accumulate it's time, and remove the edges.
            edgeTasks = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                edgeTasks.add(q.poll());
            }
            String maxTimeTask = findMaxTimeTask(edgeTasks, taskTimeMap);
            ans.add(maxTimeTask);
            minTime += taskTimeMap.get(maxTimeTask);

            for (String currentTask : edgeTasks) {
                List<String> outDegreeList = outDegree.get(currentTask);
                for (String outDegreeTask : outDegreeList) {
                    inDegree.get(outDegreeTask).remove(currentTask);
                    if (inDegree.get(outDegreeTask).isEmpty() && maxTimeTask.equals(currentTask) ) {
                        // Only queue the maxTimeTask's neighbor.
                        q.add(outDegreeTask);
                    }
                }
            }
        }

        return minTime;
    }
}
