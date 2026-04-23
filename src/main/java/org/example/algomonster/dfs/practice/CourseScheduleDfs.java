package org.example.algomonster.dfs.practice;

import java.util.*;

public class CourseScheduleDfs {
    /**
     * 0 = Unvisited
     * 1 = Visiting (Currently in recursion stack)
     * 2 = Visited (Fully processed)
     */
    public static boolean isValidCourseSchedule(int n, List<List<Integer>> prerequisites) {
        // 1. Build Adjacency List
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (List<Integer> pair : prerequisites) {
            // [0, 1] means 1 -> 0 (1 must be taken before 0)
            adj.get(pair.get(1)).add(pair.get(0));
        }

        // 2. Track states of each node
        int[] state = new int[n];

        // 3. Check every node (handles disconnected components)
        for (int i = 0; i < n; i++) {
            if (state[i] == 0) { // Only start DFS if not visited
                if (hasCycle(i, adj, state)) {
                    return false; // Cycle detected
                }
            }
        }

        return true;
    }

    private static boolean hasCycle(int node, List<List<Integer>> adj, int[] state) {
        if (state[node] == 1) return true;  // Found a node currently in the stack!
        if (state[node] == 2) return false; // Already fully explored this path

        state[node] = 1; // Mark as "Visiting"

        for (int neighbor : adj.get(node)) {
            if (hasCycle(neighbor, adj, state)) {
                return true;
            }
        }

        state[node] = 2; // Mark as "Visited"
        return false;
    }

    public static void main(String[] args) {
        // Example: 2 courses, 1 depends on 0
        List<List<Integer>> test = new ArrayList<>();
        test.add(Arrays.asList(0, 1));

        System.out.println("Is schedule valid? " + isValidCourseSchedule(2, test));
    }
}