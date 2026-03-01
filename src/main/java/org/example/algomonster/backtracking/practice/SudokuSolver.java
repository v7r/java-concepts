package org.example.algomonster.backtracking.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem statement
 *
 * Write a function to solve a 9×9 Sudoku puzzle by filling the empty cells.
 *
 * Empty cells are indicated by '.'.
 *
 * A solution must satisfy:
 *
 * Each row contains the digits 1–9 exactly once
 *
 * Each column contains the digits 1–9 exactly once
 *
 * Each of the nine 3×3 sub-boxes contains the digits 1–9 exactly once
 *
 * You may assume that the input puzzle has exactly one solution.
 *
 * Example
 * Input:
 * [
 *  ["5","3",".",".","7",".",".",".","."],
 *  ["6",".",".","1","9","5",".",".","."],
 *  [".","9","8",".",".",".",".","6","."],
 *  ["8",".",".",".","6",".",".",".","3"],
 *  ["4",".",".","8",".","3",".",".","1"],
 *  ["7",".",".",".","2",".",".",".","6"],
 *  [".","6",".",".",".",".","2","8","."],
 *  [".",".",".","4","1","9",".",".","5"],
 *  [".",".",".",".","8",".",".","7","9"]
 * ]
 */
public class SudokuSolver {

    // V3 - Implementation grade; need further clarity before implementation.
    // Before recursive call;
    //      initialize rowUsed, colUsed, boxUsed with the overlay values from the initial board.
    // begin recursive call.
    //
    // base case; no empty cell, then record the board; return true;
    // state: int[][] board, boolean[][] rowUsed, boolean[][] colUsed, boolean[][] boxUsed
    // recursion body:
    //     Find the-next-best-empty-cell (row,col)
    //     compute boxNumber
    //     Compute the candidates at the cell
    //     For each candidate
    //         board[row][col] = candidate;
    //         rowUsed[row][candidate] = true;
    //         colUsed[col][candidate] = true;
    //         boxUsed[boxNumber][candidate] = true;
    //         boolean found = recurse: board
    //         board[row][col] = 0;
    //         rowUsed[row][candidate] = false;
    //         colUsed[col][candidate] = false;
    //         boxUsed[boxNumber][candidate] = false;
    //         if found return true;
    //     return false;
    //
    // Definitions:
    //    the-next-best-empty-cell: boolean[][] rowUsed, boolean[][] colUsed, boolean[][] boxUsed
    //        from rowUse: find the minimum remaining to fill row.
    //        from colUsed: find the minimum remaining to fill col
    //        from boxUsed: find the minimum remaining to fill box.
    //        find the minimum unused row/col/box and pick a random cell
    //    v2: then-next-best-empty-cell: boolean[][] rowUsed, boolean[][] colUsed, boolean[][] boxUsed
    //        of all unused calls calculate the candidate list. Find the cell with minimum candidates.


    // V2 - Correct but immature.
    // base case; no empty cell, then record the board; return;
    // state: int[][] board;
    // recursion body:
    //     Find the next best empty cell (row,col)
    //     Compute the candidates at the cell
    //     For each candidate
    //         board[row][col] = candidate;
    //         recurse: board
    //         board[row][col] = 0;

    // V1 - INCORRECT
    // base case; row==9 record the board; return;
    // state: row, int[][] valAtRowCol, int[][] board
    // at each recursion:
    //    For each next empty column 'col' that is not already filled in valAtRowCol[row] and empty in board[row]
    //        Find the possible numbers that does not violate row, column and 3x3 local grid (next choices)
    //        For each possible number 'n'
    //            valAtRowCol[row][col] = n;
    //            recurse: row, valAtRowCol, board;
    //            valAtRowCol[row][col] = 0;
    //    recurse: row+1, valAtRowCol, board;

    /**
     *
     * @param board
     * @param rowUsed
     * @param colUsed
     * @param boxUsed
     * @return
     */
    private static boolean dfs(int[][] board, boolean[][] rowUsed, boolean[][] colUsed, boolean[][] boxUsed) {
        if (!unusedExists(board)) {
            return true;
        }
        int[] emptyCell = findNextBestEmptyCell(board, rowUsed, colUsed, boxUsed);
        int row = emptyCell[0], col = emptyCell[1];
        int boxNumber = boxNumber(row,col);
        List<Integer> candidates = computePotentialCandidates(row, col, rowUsed, colUsed, boxUsed);
        // iterate each candidate and mark -> recurse -> unmark
        for ( int candidate : candidates) {
            // track
            board[row][col] = candidate;
            rowUsed[row][candidate] = true;
            colUsed[col][candidate] = true;
            boxUsed[boxNumber][candidate] = true;
            // recurse
            boolean found = dfs(board,rowUsed,colUsed, boxUsed);
            // backtrack
            if (!found) {
                board[row][col] = 0;
                rowUsed[row][candidate] = false;
                colUsed[col][candidate] = false;
                boxUsed[boxNumber][candidate] = false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the minimum unused row/col/box number between [1..9]
     *
     * @param usedXXX
     * @return
     * Index 0 - is the row/col/box number
     * Index 1 - is the unused count;
     */
    private static int[] findMinUnusedXXX(boolean[][] usedXXX) {
        int minUnused = 0, min = 10;
        for (int b = 1; b < 10; b++) {
            // count unused;
            int unusedCount = 0;
            for (int i = 1; i < 10; i++) if (!usedXXX[b][i]) unusedCount++;
            if (unusedCount == 0) continue; // all are used;
            if (unusedCount < min) {
                minUnused = b;
                min = unusedCount;
            }
        }
        return new int[]{minUnused, min};
    }

    /**
     * Minimal Remaining Value - heuristic.
     * @param board
     * @param rowUsed
     * @param colUsed
     * @param boxUsed
     * @return
     */
    private static int[] findNextBestEmptyCell(int[][] board, boolean[][] rowUsed, boolean[][] colUsed,
                                               boolean[][] boxUsed) {
        // Find all the unused cells and compute the possible candidates. Find the cell with min candidates.
        int[] minCandidatesCell = new int[2];
        int min = 10;
        for (int r = 1; r < 10; r++) {
            for (int c = 1; c < 10; c++) {
                if (board[r][c] == 0) {
                    // Find possible candidates in this unused cell.
                    List<Integer> candidates = computePotentialCandidates(r,c,rowUsed, colUsed, boxUsed);
                    if (candidates.isEmpty()) {
                        // This will stop subtree recursion and return false;
                        // Because this empty call cannot have any candidate, so this branch is dead.
                        return new int[]{r,c};
                    }
                    if (candidates.size() < min) {
                        min = candidates.size();
                        minCandidatesCell = new int[]{r,c};
                    }
                }
            }
        }
        return minCandidatesCell;
    }

    private static List<Integer> computePotentialCandidates(int row, int col, boolean[][] rowUsed, boolean[][] colUsed,
                                                    boolean[][] boxUsed) {
        boolean[] rowUsage = rowUsed[row];
        // find unused in the col;
        boolean[] colUsage = colUsed[col];
        // find unused in the box;
        int boxNumber = boxNumber(row,col);
        boolean[] boxUsage = boxUsed[boxNumber];
        // find the common unused in the above three;
        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (!rowUsage[i] && !colUsage[i] && !boxUsage[i]) {
                candidates.add(i);
            }
        }
        return candidates;
    }

    private static boolean unusedExists(int[][] board) {
        for (int r = 1; r < 10; r++) {
            for (int c = 1; c < 10; c++) {
                if (board[r][c] == 0) return true;
            }
        }
        return false;
    }

    /**
     *
     * @param row
     * @param col
     * @return
     */
    private static int boxNumber(int row, int col) {
        return ((row - 1) / 3) * 3 + ((col - 1) / 3) + 1;
    }

    /**
     *
     * @param board
     * @return
     */
    public static boolean sudokuSolver(char[][] board) {
        int[][] normalizedBoard = new int[10][10];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                normalizedBoard[r+1][c+1] = board[r][c] == '.' ? 0 : Integer.parseInt(String.valueOf(board[r][c]));
            }
        }
        boolean[][] rowUsed = new boolean[10][10];
        boolean[][] colUsed = new boolean[10][10];
        boolean[][] boxUsed = new boolean[10][10];
        for (int r = 1; r < 10; r++) {
            for (int c = 1; c < 10; c++) {
                int number = normalizedBoard[r][c];
                if (number == 0) continue;
                int boxNumber = boxNumber(r,c);
                // Early invalid board detection;
                if (rowUsed[r][number] || colUsed[c][number] || boxUsed[boxNumber][number]) return false;
                rowUsed[r][number] = true;
                colUsed[c][number] = true;
                boxUsed[boxNumber][number] = true;
            }
        }
        boolean found = dfs(normalizedBoard, rowUsed, colUsed, boxUsed);
        if (found) {
            for (int r = 1; r < 10; r++)
                for (int c = 1; c < 10; c++)
                    board[r - 1][c - 1] = (char) ('0' + normalizedBoard[r][c]);
        }

        return found;
    }
}
