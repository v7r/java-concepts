package org.example.algomonster.graphs_directed;

import java.util.*;

/**
 * For this problem, given a list of tasks and a list of requirements, compute a sequence of tasks that can be
 * performed, such that we complete every task once while satisfying all the requirements.
 *
 * Each requirement will be in the form of a list [a, b], where task a needs to be completed first before task b can be
 * completed,
 *
 * There is guaranteed to be a solution.
 *
 * Examples
 * Example 1
 * Input:
 * tasks = ["a", "b", "c", "d"]
 * requirements = [["a", "b"], ["c", "b"], ["b", "d"]]
 * Output: ["a", "c", "b", "d"]
 */
public class TaskScheduling {
    //a b c d
    //a b
    //c b
    //b d
    public static List<String> taskScheduling(List<String> tasks, List<List<String>> requirements) {

        List<String> ans = new ArrayList<>();
        //eg: [a,b] - Key=b,Value=[a] ( a has to complete first before b can)
        //    [c,b] - Key=b,Value=[a,c] ( a, c have to complete  first before b can)
        //  So, in degree of b is 2 (a,c).
        Map<String,List<String>> inDegree = new HashMap<>();

        Map<String,List<String>> outDegree = new HashMap<>();

        // Initialize inDegree and outDegree neighbors
        for (List<String> requirement : requirements) {
            List<String> inDegreeNeighbors = inDegree.computeIfAbsent(requirement.get(1), k -> new ArrayList<>());
            inDegreeNeighbors.add(requirement.get(0));

            List<String> outDegreeNeighbors = outDegree.computeIfAbsent(requirement.get(0), k -> new ArrayList<>());
            outDegreeNeighbors.add(requirement.get(1));
        }

        ArrayDeque<String> q = new ArrayDeque<>();
        for (String t : tasks) {
            if (inDegree.get(t) == null || inDegree.get(t).isEmpty()) {
                q.add(t);
            }
        }

        while (!q.isEmpty()) {
            String task = q.poll();
            ans.add(task);
            List<String> outDegreeNeighbors = outDegree.get(task);
            if (outDegreeNeighbors == null) continue;
            for (String outDegreeNeighbor : outDegreeNeighbors) {
                if (inDegree.get(outDegreeNeighbor) != null) {
                    inDegree.get(outDegreeNeighbor).remove(task);
                    if (inDegree.get(outDegreeNeighbor).isEmpty()) {
                        q.add(outDegreeNeighbor);
                    }
                }
            }
        }

        return ans.size() == tasks.size() ? ans : List.of();
    }
}
