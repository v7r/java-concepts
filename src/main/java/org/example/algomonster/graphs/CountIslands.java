package org.example.algomonster.graphs;

import java.util.ArrayDeque;
import java.util.List;

/**
 * Given a 2-dimensional matrix of values with 0 and 1, count the number of islands of 1. An island consists of all 1
 * value cells and is surrounded by either an edge or all 0 cells. Cells can only be adjacent to each other horizontally
 * or vertically, not diagonally.
 *
 * <img src="imgs/CountIslands.png" />
 */
public class CountIslands {
    // bfs to visit the adjacent unvisited lands.
    private static void bfs(Integer[] unVisitedLand, boolean[][] visited, List<List<Integer>> grid) {
        int rows = grid.size(), cols = grid.get(0).size();
        ArrayDeque<Integer[]> deque = new ArrayDeque<>();
        deque.add(unVisitedLand);
        visited[unVisitedLand[0]][unVisitedLand[1]] = true;
        while (!deque.isEmpty()) {
            int n = deque.size();
            // process the current level lands
            for (int i = 0; i < n; i++) {
                Integer[] visitedLand = deque.pop();
                int row = visitedLand[0];
                int col = visitedLand[1];
                // find adjacent nodes that are unvisited and enque;
                int[] rowDir = {1,-1,0,0};
                int[] colDir = {0,0,1,-1};
                for (int d = 0; d < 4; d++) {
                    if (row+rowDir[d] < 0 || row+rowDir[d] >= rows) continue;
                    if (col+colDir[d] < 0 || col+colDir[d] >= cols) continue;
                    if (visited[row+rowDir[d]][col+colDir[d]] || grid.get(row).get(col) == 0) continue;
                    visited[row+rowDir[d]][col+colDir[d]] = true;
                    deque.add(new Integer[]{row+rowDir[d], col+colDir[d]});
                }
            }
        }
    }

    public static int countNumberOfIslands(List<List<Integer>> grid) {
        int rows = grid.size(), cols = grid.get(0).size();
        boolean[][] visited = new boolean[rows][cols];
        int islandCount = 0;
        Integer[] unVisitedLand = null;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (visited[r][c] || grid.get(r).get(c) == 0) continue;
                islandCount++;
                // You can use either dfs or bfs to visit the land.
                // dfs:- recurse adjacent land (north,east,west and south cells if unvisited and within box boundaries)
                // following is bfs implementation;
                bfs(new Integer[]{r,c}, visited, grid);
            }
        }
        return islandCount;
    }
}
