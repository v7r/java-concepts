package org.example.algomonster.graphs;

import java.util.ArrayDeque;
import java.util.List;

/**
 * You are given an m by n grid of integers representing a map of a dungeon. In this map:
 *
 * -1 represents a wall or an obstacle of some kind.
 * 0 represents a gate out of the dungeon.
 * INF represents empty space.
 * For this question, let INF be 2^31 - 1 == 2147483647, which is the max value of the integer type in many programming
 * languages.
 *
 * Venturing into the dungeon is very dangerous, so you would like to know how close you are at each point in the
 * dungeon to the closest exit. Given the map of the dungeon, return the same map, but for each empty space, that space
 * is replaced by the number of steps it takes to reach the closest exit. If a space cannot reach the exit, that space
 * remains INF.
 *
 * Note that each step, you can move horizontally or vertically, but you cannot move pass a wall or an obstacle.
 *
 * Input
 * dungeon_map: a matrix of integer representing the dungeon map.
 * Output
 * A matrix of integer representing the dungeon map with the addition of distance to an exit for each empty space.
 *
 * Examples
 * Example 1:
 * Input:
 *
 * dungeon_map = [
 *   [INF, -1, 0, INF],
 *   [INF, INF, INF, -1],
 *   [INF, -1, INF, -1],
 *   [0, -1, INF, INF],
 * ]
 * Output: [ [3, -1, 0, 1], [2, 2, 1, -1], [1, -1, 2, -1], [0, -1, 3, 4], ]
 *
 * Explanation:
 *
 * Constraints
 * 1 <= n, m <= 500
 */
public class WallsAndGates {
    public static class Coords {
        public int row;
        public int col;

        public Coords(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Coords add(Coords other) {
            return new Coords(row + other.row, col + other.col);
        }
    }

    public static List<Coords> directions = List.of(new Coords(0, 1), new Coords(1, 0), new Coords(0, -1), new Coords(-1, 0));

    /**
     * Solution: We queue each gate, then process each gate from queue and add it's adjacent cells to queue.
     *           Each adjacent queue is only added to queue once. the queue forces to process each tage in cycles.
     *           In each cycle the gate's adjacent nodes are filled first, which ever fills first will update the
     *           cell with the step count.
     *
     *           This is not truly a bfs. But a round-robin flood fill. !!
     *
     * @param dungeonMap
     * @return
     */
    public static List<List<Integer>> mapGateDistances(List<List<Integer>> dungeonMap) {
        ArrayDeque<Coords> deque = new ArrayDeque<>();
        int n = dungeonMap.size(), m = dungeonMap.get(0).size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dungeonMap.get(i).get(j) == 0) {
                    deque.offer(new Coords(i, j));
                }
            }
        }
        while (!deque.isEmpty()) {
            Coords currentPos = deque.poll();
            for (Coords delta : directions) {
                Coords newPos = currentPos.add(delta);
                if (newPos.row >= 0 && newPos.row < n && newPos.col >= 0 && newPos.col < m) {
                    if (dungeonMap.get(newPos.row).get(newPos.col) == Integer.MAX_VALUE) {
                        dungeonMap.get(newPos.row).set(newPos.col, 1 + dungeonMap.get(currentPos.row).get(currentPos.col));
                        deque.add(newPos);
                    }
                }
            }
            for (int r = 0; r < dungeonMap.size(); r++) {
                for (int c = 0; c < dungeonMap.size(); c++) {
                    System.out.print( "\t\t" + (dungeonMap.get(r).get(c) == Integer.MAX_VALUE ? "INF" : dungeonMap.get(r).get(c)));
                }
                System.out.println(" ");
            }
            System.out.println("  ------------------------------------------------------------------------ ");
        }
        return dungeonMap;
    }

}
