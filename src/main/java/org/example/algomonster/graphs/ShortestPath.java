package org.example.algomonster.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Given an (unweighted) connected graph, return the length of the shortest path between two nodes A and B, in terms
 * of the number of edges.
 *
 * Assume there always exists a path between nodes A and B.
 *
 * Input:
 *
 * graph = [[1, 2], [0, 2, 3], [0, 1], [1]]
 * A = 0
 * B = 3
 * Output: 2
 *
 * <img src="imgs/ShortestPath.png" />
 *
 * Note that the graph is given as a 2-d list, where graph[i] is the list of nodes that node i is adjacent to.
 * You can use the BFS template from BFS on Graphs as your starter code.
 */
public class ShortestPath {
    public static int shortestPath(List<List<Integer>> graph, int a, int b) {
        int shortPath = 0;
        List<Integer> visited = new ArrayList<>();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.offer(a);
        visited.add(a);
        while (!deque.isEmpty()) {
            shortPath++;
            int lcount = deque.size();
            for (int i = 0; i < lcount; i++) {
                int currentNode = deque.pop();
                List<Integer> edges = graph.get(currentNode);
                for (int j = 0; j < edges.size(); j++) {
                    int edge = edges.get(j);
                    if (visited.contains(edge)) continue;
                    if (edge == b) return shortPath;
                    deque.add(edge);
                }
            }
        }
        return 0;
    }

}
