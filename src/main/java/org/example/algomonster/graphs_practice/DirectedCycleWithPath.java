package org.example.algomonster.graphs_practice;

import java.util.*;

/**
 * Implement DFS logic to detect and return the cycle path for a given directed cyclic graph.
 * If no cycle return null.
 */
public class DirectedCycleWithPath {

    public List<Character> findCycle(Map<Character, List<Character>> graph) {

        Map<Character, Integer> state = new HashMap<>();
        Map<Character, Character> parent = new HashMap<>();

        for (Character node : graph.keySet()) {
            state.put(node, 0);
        }

        for (Character node : graph.keySet()) {
            if (state.get(node) == 0) {
                List<Character> cycle = dfs(node, graph, state, parent);
                if (cycle != null) return cycle;
            }
        }

        return null;
    }

    private List<Character> dfs(Character node,
                                Map<Character, List<Character>> graph,
                                Map<Character, Integer> state,
                                Map<Character, Character> parent) {

        state.put(node, 1); // visiting

        for (Character nei : graph.getOrDefault(node, List.of())) {

            if (state.get(nei) == 0) {
                parent.put(nei, node);

                List<Character> cycle = dfs(nei, graph, state, parent);
                if (cycle != null) return cycle;
            }

            else if (state.get(nei) == 1) {
                // BACK EDGE → build cycle
                return buildCycle(node, nei, parent);
            }
        }

        state.put(node, 2);
        return null;
    }

    private List<Character> buildCycle(Character start,
                                       Character end,
                                       Map<Character, Character> parent) {

        List<Character> cycle = new ArrayList<>();
        cycle.add(end);

        Character cur = start;
        while (!cur.equals(end)) {
            cycle.add(cur);
            cur = parent.get(cur);
        }

        cycle.add(end); // close loop
        Collections.reverse(cycle);
        return cycle;
    }
}
