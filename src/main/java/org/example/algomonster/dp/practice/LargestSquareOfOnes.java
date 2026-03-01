package org.example.algomonster.dp.practice;

/**
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 *
 *
 * Example 1:
 *
 *Input: matrix = [
 *      ["1","0","1","0","0"],
 *      ["1","0","1","1","1"],
 *      ["1","1","1","1","1"],
 *      ["1","0","0","1","0"]
 * ]
 * Output: 4
 *
 * Example 2:
 *
 * Input: matrix = [
 *  ["0","1"],
 *  ["1","0"]
 * ]
 * Output: 1
 *
 * Example 3:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 */
public class LargestSquareOfOnes {
    /**
     * Returns the side-length of the largest all-1s square in the matrix.
     * If you want area instead, return maxSide * maxSide.
     */
    public static int largestSquareSide(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int n = grid.length;
        int m = grid[0].length;

        // dp[i][j] = largest square side with bottom-right corner at (i, j)
        int[][] dp = new int[n][m];

        int maxSide = 0;

        for (int i = 0; i < n; i++) {
            // optional: validate rectangular matrix
            if (grid[i] == null || grid[i].length != m) {
                throw new IllegalArgumentException("Input must be a non-jagged rectangular matrix.");
            }

            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    int top  = (i > 0) ? dp[i - 1][j] : 0;
                    int left = (j > 0) ? dp[i][j - 1] : 0;
                    int diag = (i > 0 && j > 0) ? dp[i - 1][j - 1] : 0;

                    dp[i][j] = 1 + Math.min(top, Math.min(left, diag));
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide;
    }

    /** Convenience: returns the area of the largest all-1s square. */
    public static int largestSquareArea(int[][] grid) {
        int side = largestSquareSide(grid);
        return side * side;
    }

    // Quick demo
    public static void main(String[] args) {
        int[][] grid = {
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 0, 0}
        };

        int side = largestSquareSide(grid);
        int area = largestSquareArea(grid);

        System.out.println("Max square side: " + side); // 3
        System.out.println("Max square area: " + area); // 9
    }
}