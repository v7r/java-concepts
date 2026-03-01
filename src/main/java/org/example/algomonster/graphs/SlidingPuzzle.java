package org.example.algomonster.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A sliding puzzle consists of a 2 x 3 board with tiles numbered 1 to 5 and one empty space represented by 0. The board
 * is represented as a 2 x 3 matrix. For example:
 *
 *
 *
 * The configuration above is represented by [[4, 1, 3], [2, 0, 5]].
 *
 * Each move swaps the empty space with an adjacent tile (horizontally or vertically). The goal is to reach the solved
 * state [[1, 2, 3], [4, 5, 0]]:
 *
 *
 *
 * Given an initial board configuration, find the minimum number of moves to reach the solved state, or return -1 if
 * impossible.
 *
 * Input
 * init_pos: an integer matrix representing the initial position of the puzzle.
 * Output
 * The number of steps required to solve this puzzle, or -1 if the puzzle is impossible to solve.
 *
 * Examples
 * Example 1:
 * Input:
 *
 * init_pos = [[4, 1, 3], [2, 0, 5]]
 * Output: 5
 *
 * Explanation:
 *
 *
 * First slide
 * Previous
 * Next
 * Constraints
 * The input must be a 2 x 3 integer matrix containing exactly one of each from 0 to 5
 */
public class SlidingPuzzle {
    private static int hashOf(int[][] board) {
        int rows = board.length, cols = board[0].length;
        int hash = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                hash = hash * 10 + board[r][c];
            }
        }
        return hash;
    }


    private static int[] findZeroCell(int[][] board) {
        int rows = board.length, cols = board[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 0) {
                    return new int[] {r, c};
                }
            }
        }
        return new int[] {-1,-1};
    }

    private static int[] rowDir = {1,-1,0,0};
    private static int[] colDir = {0,0,1,-1};
    private static List<int[][]> computeEdges(int[][] board) {
        List<int[][]> edges = new ArrayList<>();
        int rows = board.length, cols = board[0].length;
        int[] cell = findZeroCell(board);
        int row = cell[0], col = cell[1];
        if (row == -1) return edges; // empty edges;

        for (int i = 0; i < rowDir.length; i++) {
            int edgeRow = row + rowDir[i];
            int edgeCol = col + colDir[i];
            if (edgeRow >= rows || edgeRow < 0) continue;
            if (edgeCol >= cols || edgeCol < 0 ) continue;

            int[][] edge = new int[rows][0];
            for (int r = 0; r < rows; r++) {
                edge[r] = Arrays.copyOf(board[r], cols);
            }

            // swap zero to from row,col to edgeRow,edgeCol
            int t = edge[edgeRow][edgeCol]; // value at the edge;
            edge[edgeRow][edgeCol] = 0;     // replace the value at the edge to 0
            edge[row][col] = t;             // replace the value at the zero cell with t;
            edges.add(edge);
        }

        return edges;
    }

    public static int numSteps(List<List<Integer>> initPos) {
        int rows = initPos.size(), cols = initPos.get(0).size();
        int[][] board = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = initPos.get(r).get(c);
            }
        }

        int[][] finalState = new int[rows][cols];
        int cellNum = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                finalState[r][c] = ++cellNum;
            }
        }
        finalState[rows-1][cols-1] = 0;

        int targetStateHash = hashOf(finalState);
        int initialHash = hashOf(board);
        if (initialHash == targetStateHash) return 0;

        List<Integer> visited = new ArrayList<>();
        ArrayDeque<int[][]> queue = new ArrayDeque<>();
        queue.add(board);
        visited.add(initialHash);

        int moves = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            moves++;
            for (int i = 0; i < n; i++) {
                int[][] currentBoard = queue.pop();
                List<int[][]> edges = computeEdges(currentBoard);
                for (int[][] edge : edges) {
                    int edgeHash = hashOf(edge);
                    if (edgeHash == targetStateHash) return moves;
                    if (visited.contains(edgeHash)) continue;
                    visited.add(edgeHash);
                    queue.add(edge);
                }
            }
        }

        return -1;
    }
}
