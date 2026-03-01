package org.example.algomonster.graphs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;

/**
 * On an infinitely large chessboard, a knight is located on [0, 0].
 *
 * A knight can move in eight directions.
 *
 * <img src="imgs/KnightMinimumMoves.png" />
 *
 * Given a destination coordinate [x, y], determine the minimum number of moves from [0, 0] to [x, y].
 */
public class KnightMinimumMoves {
    public static int getKnightShortestPath(int x, int y) {
        int minPath = 0;
        Integer[] destNode = {x, y};
        Integer[] node = {0, 0};
        ArrayDeque<Integer[]> queue = new ArrayDeque<>();
        HashSet<Integer[]> visited = new HashSet<>();
        visited.add(node);
        queue.offer(node);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                node = queue.pop();
                if (Arrays.equals(node,destNode)) return minPath;
                // Find the possible adjacent nodes
                int[] rowDir = {2,  2, -2, -2, 1, -1,  1, -1};
                int[] colDir = {1, -1,  1, -1, 2,  2, -2, -2};
                for (int dir = 0; dir < rowDir.length; dir++) {
                    Integer[] edge = {node[0]+rowDir[dir], node[1]+colDir[dir]};
                    if (visited.contains(edge)) continue;
                    visited.add(edge);
                    queue.add(edge);
                }
            }
            minPath++;
        }
        return 0;
    }
}
