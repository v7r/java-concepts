package org.example.algomonster.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * You are faced with a 4-wheel lock where each wheel contains the numbers '0' through '9'. Turning a wheel can either
 * increase or decrease its number by one, wrapping around from '9' to '0' or vice versa. A single move involves
 * rotating any one of the wheels by one slot.
 *
 * The lock starts with the combination '0000'. However, there are specific combinations termed as "deadends". If the
 * lock lands on any of these deadend combinations, the wheels jam, making it impossible to proceed.
 *
 * Your task is to determine the least number of moves needed to reach a given target combination from the starting
 * point without hitting any deadend. If reaching the target is impossible due to deadends, return -1.
 *
 * Input
 * target_combo: a string representing the four digit combination to open the lock.
 * trapped_combos: a list of strings representing the trapped combinations.
 * Output
 * An integer representing the number of steps it takes to open the lock, or -1 if you can't open it without triggering
 * the trap.
 *
 * Examples
 * Example 1:
 * Input:
 *
 * target_combo = "0202"
 * trapped_combos = ["0201","0101","0102","1212","2002"]
 * Output: 6
 *
 * Explanation:
 *
 * 0000 -> 1000 -> 1100 -> 1200 -> 1201 -> 1202 -> 0202, a total of 6 steps.
 *
 * Constraints
 * The starting combination (0000) and the final combination is not trapped because that defeats the purpose of the
 * lock.
 *
 */
public class OpenTheLock {
    private static List<String> findEdges(String combo) {
        List<String> edges = new ArrayList<>();
        for (int i = 0; i < combo.length(); i++) {
            char[] c = combo.toCharArray();
            char ci = c[i];

            c[i] = (ci + 1) > (int)'9' ? '0' : (char)(ci + 1);
            edges.add(new String(c));

            c[i] = (ci - 1) < (int)'0' ? '9' : (char)(ci - 1);
            edges.add(new String(c));
        }
        return edges;
    }

    public static int numSteps(String targetCombo, List<String> trappedCombos) {
        if (targetCombo.equals("0000")) return 0;
        List<String> visited = new ArrayList<>();
        ArrayDeque<String> queue = new ArrayDeque<>();
        int minSteps = 0;
        queue.add("0000");
        visited.add("0000");
        while (!queue.isEmpty()) {
            int n = queue.size();
            minSteps++;
            System.out.print("Level " + minSteps +" edges: ");
            for (int i = 0; i < n; i++) {
                String currentCombo = queue.pop();
                List<String> edges = findEdges(currentCombo);
                System.out.print(" "+edges);
                for (String nextCombo : edges) {
                    if (nextCombo.equals(targetCombo)) return minSteps;
                    if (trappedCombos.contains(nextCombo)) continue;
                    if (visited.contains(nextCombo)) continue;
                    visited.add(nextCombo);
                    queue.add(nextCombo);
                }
            }
            System.out.println("\n");
        }
        return -1;
    }
}
