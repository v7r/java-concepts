package org.example.algomonster.graphs_practice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean
 * touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where
 * heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and
 * west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any
 * cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell
 * (ri, ci) to both the Pacific and Atlantic oceans.
 *
 * Example 1:
 *  <img src="./imgs/FlowIntoPacificAtlantic.png" />
 *
 *
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:\
 *
 * [0,4]: [0,4] -> Pacific Ocean
 *        [0,4] -> Atlantic Ocean
 * [1,3]: [1,3] -> [0,3] -> Pacific Ocean
 *        [1,3] -> [1,4] -> Atlantic Ocean
 * [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
 *        [1,4] -> Atlantic Ocean
 * [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
 *        [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
 * [3,0]: [3,0] -> Pacific Ocean
 *        [3,0] -> [4,0] -> Atlantic Ocean
 * [3,1]: [3,1] -> [3,0] -> Pacific Ocean
 *        [3,1] -> [4,1] -> Atlantic Ocean
 * [4,0]: [4,0] -> Pacific Ocean
 *       [4,0] -> Atlantic Ocean
 * Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
 *
 * Example 2:
 * Input: heights = [[1]]
 * Output: [[0,0]]
 * Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 *
 * Constraints:
 *
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 */
public class FlowIntoPacificAtlantic {

    private static void bfs(ArrayDeque<Integer[]> q, boolean[][] oceanChannel, int[][] heights) {
        int[] rowDir = new int[] {1, -1, 0, 0};
        int[] colDir = new int[] {0, 0, 1, -1};
        //ArrayDeque<Integer[]> q = new ArrayDeque<>();
        //q.add(new Integer[]{r,c});
        int rows = heights.length, cols = heights[0].length;

        while(!q.isEmpty()) {
            Integer[] cell = q.poll();
            int cellR = cell[0], cellC = cell[1];
            for (int i = 0; i < 4; i++) {
                int edgeRow = cellR + rowDir[i];
                int edgeCol = cellC + colDir[i];
                if (edgeRow >= 0 && edgeRow < rows
                        && edgeCol >= 0 && edgeCol < cols
                        && heights[edgeRow][edgeCol] >= heights[cellR][cellC]
                        && !oceanChannel[edgeRow][edgeCol] // skip if already visited.
                ) {
                    oceanChannel[edgeRow][edgeCol] = true;
                    q.add(new Integer[]{edgeRow,edgeCol});
                }
            }
        }
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null) return List.of();
        int rows = heights.length, cols = heights[0].length;
        ArrayDeque<Integer[]> atlanticQ = new ArrayDeque<>();
        ArrayDeque<Integer[]> pacificQ = new ArrayDeque<>();
        boolean[][] atlanticChannel = new boolean[rows][cols];
        boolean[][] pacificChannel = new boolean[rows][cols];
        // set the border cells to true;
        // pacific touching border cells are left and top.
        // atlantic touching border cells are bottom and right.
        for (int r = 0; r < rows; r++) {
            pacificChannel[r][0] = true;
            pacificQ.add(new Integer[]{r,0});
            atlanticChannel[r][cols-1] = true;
            atlanticQ.add(new Integer[]{r,cols-1});
        }

        for (int c = 0; c < cols; c++) {
            pacificChannel[0][c] = true;
            pacificQ.add(new Integer[]{0,c});
            atlanticChannel[rows-1][c] = true;
            atlanticQ.add(new Integer[]{rows-1, c});
        }
        bfs(atlanticQ, atlanticChannel, heights);
        bfs(pacificQ, pacificChannel, heights);
        List<List<Integer>> ans = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (atlanticChannel[r][c] && pacificChannel[r][c]) {
                    ans.add(List.of(r,c));
                }
            }
        }
        return ans;
    }
}
