package org.example.algomonster.graphs;

import java.util.ArrayDeque;
import java.util.List;

/**
 * In computer graphics, an uncompressed raster image is presented as a matrix of numbers. Each entry of the matrix
 * represents the color of a pixel. A flood fill algorithm takes a coordinate r, c and a replacement color, and replaces
 * all pixels connected to r, c that have the same color (i.e., the pixels connected to the given coordinate with same
 * color and all the other pixels connected to the those pixels of the same color) with the replacement color. (e.g.
 * MS-Paint's paint bucket tool).
 *
 * Input
 * r: row
 * c: column
 * replacement: replacement color
 * image: an 2D array of integers representing the image
 * Output
 * the replaced image
 *
 * Examples
 * Example 1:
 * Input:
 *
 * r = 2
 * c = 2
 * replacement = 9
 * arr = [[0,1,3,4,1],[3,8,8,3,3],[6,7,8,8,3],[12,2,8,9,1],[12,3,1,3,2]]
 * Output: [[0,1,3,4,1],[3,9,9,3,3],[6,7,9,9,3],[12,2,9,9,1],[12,3,1,3,2]]
 *
 * Explanation:
 *
 * From
 *
 * 0 1 3 4 1
 * 3 8 8 3 3
 * 6 7 8 8 3
 * 12 2 8 9 1
 * 12 3 1 3 2
 * to
 *
 * 0 1 3 4 1
 * 3 9 9 3 3
 * 6 7 9 9 3
 * 12 2 9 9 1
 * 12 3 1 3 2
 *
 * <img src="imgs/FloodFill.png" />
 */
public class FloodFill {
    public static List<List<Integer>> floodFill(int r, int c, int replacement, List<List<Integer>> image) {
        int rows = image.size(), cols = image.get(0).size();
        boolean[][] visited = new boolean[rows][cols];
        ArrayDeque<Integer[]> deque = new ArrayDeque<>();
        Integer[] node = {r,c};
        deque.add(node);
        int targetColor = image.get(r).get(c);
        visited[r][c] = true;
        while (!deque.isEmpty()) {
            int n = deque.size();
            for (int i = 0; i < n; i++) {
                node = deque.pop();
                int row = node[0], col = node[1];
                image.get(row).set(col, replacement);
                // compute unvisited edges and queue if edge color is same as targetColor; Also, check for boundaries.
                if (row+1 < rows && !visited[row+1][col] && image.get(row+1).get(col) == targetColor) {
                    deque.add(new Integer[]{row+1,col});
                }
                if (row-1 >= 0 && !visited[row-1][col] && image.get(row-1).get(col) == targetColor) {
                    deque.add(new Integer[]{row-1,col});
                }
                if (col+1 < cols && !visited[row][col+1] && image.get(row).get(col+1) == targetColor) {
                    deque.add(new Integer[]{row,col+1});
                }
                if (col-1 >= 0 && !visited[row][col-1] && image.get(row).get(col-1) == targetColor) {
                    deque.add(new Integer[]{row,col-1});
                }
            }
        }

        return image;
    }
}
