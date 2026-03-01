package org.example.algomonster.backtracking.practice;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Problem statement
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * You must place n queens on an n x n chessboard such that:
 *
 * No two queens share the same row
 *
 * No two queens share the same column
 *
 * No two queens share the same diagonal
 *
 * Return the solutions in any order.
 * Each solution is represented as a list of strings, where 'Q' indicates a queen and '.' indicates an empty square.
 *
 * Example
 *
 * For n = 4, one valid solution is:
 *
 * [
 *  ".Q..",
 *  "...Q",
 *  "Q...",
 *  "..Q."
 * ]
 */
public class NQueens {

    // . . . .
    // . . . .
    // . . . .
    // . . . .

    // . q . .
    // . . . q
    // q . . .
    // . . q .

    /**
     * This is the perfect model that I came up with. My v1 was a mess and way too complex to explain the model.
     *
     * This solution is elegant where I can explain in each recursion what I have and what will I have as choices.
     *
     *  Leaf: if dix == n; then record path in answer;
     *  State: idx, row, path (map of row to col represents successfully placed queens), n, ans.
     *         note: row is redundant;
     *  Recursion: At each recursion, I need to place a queen in the current row in any column that does not conflict
     *             existing placements. Conflicts are pruned. Valid columns are recursed.
     *
     * @param row
     * @param path
     * @param n
     * @param ans
     */
    private static void dfsV2(int row, Map<Integer, Integer> path, int n, List<List<String>> ans) {
        // base case/leaf
        // if row == n then record the path. and return;
        if (row == n) {
            List<String> board = new ArrayList<>();
            for (int r = 0; r < n; r++) {
                char[] rowChars = new char[n];
                Arrays.fill(rowChars,'.');
                rowChars[path.get(r)] = 'Q';
                board.add(new String(rowChars));
            }
            ans.add(board);
            return;
        }
        // choices: in row prune the columns [0..n]; if valid then recurse with idx+1,row+1, by pushing path(row,c);
        for (int c = 0; c < n; c++) {
            if (path.containsValue(c)) continue; // vertical position pruned.
            // prune diagonal positions;
            boolean foundDiagonal = false;
            for (Map.Entry<Integer,Integer> e : path.entrySet()) {
                if (Math.abs(row-e.getKey()) == Math.abs(c-e.getValue())) {
                    foundDiagonal = true;
                    break;
                }
            }
            if (foundDiagonal) continue;
            path.put(row, c);
            dfsV2(row+1, path, n, ans);
            path.remove(row);
        }
    }

    private static List<List<String>> arrangeV2(int n) {
        List<List<String>> ans = new ArrayList<>();
        dfsV2(0, new HashMap<>(), n, ans);
        return ans;
    }

    // Version one; messy and chaotic to debug. THIS IS NOT WORKING YET. DONT SEE.
    private static void updateQueenMoves(int r, int c, char[][] placements, char m, char onlyIf) {
        for (int row = 0; row < placements.length; row++) {
            if (placements[row][c] == onlyIf)
                placements[row][c] = m;
        }
        for (int col = 0; col < placements[r].length; col++) {
            if (placements[r][col] == onlyIf)
                placements[r][col] = m;
        }
        int row = r, col = c;
        while (row < placements.length && col < placements[r].length) {
            if (placements[row][col] == onlyIf) {
                placements[row][col] = m;
            }
            row++; col++;
        }
        row = r; col = c;
        while (row >= 0 && col >= 0) {
            if (placements[row][col] == onlyIf) {
                placements[row][col] = m;
            }
            row--; col--;
        }
        row = r; col = c;
        while (row >= 0 && col < placements[r].length) {
            if (placements[row][col] == onlyIf) {
                placements[row][col] = m;
            }
            row--; col++;
        }
        row = r; col = c;
        while (row < placements.length && col >= 0) {
            if (placements[row][col] == onlyIf) {
                placements[row][col] = m;
            }
            row++; col--;
        }
    }

    private static void uncommit(int r, int c, char[][] placements, char onlyIf) {
        updateQueenMoves(r,c,placements,'y', onlyIf);
        placements[r][c] = 'y';
    }

    private static void commit(int r, int c, char[][] placements, char marker) {
        updateQueenMoves(r,c,placements,marker, 'y');
        placements[r][c] = 'Q';
    }

    // q 1 1 1
    // 1 1 . .
    // 1 . 1 .
    // 1 . . 1

    // . . . .
    // . . . .
    // . . . .
    // . . . .

    // Each cell in the grid takes one of the following values
    //  Q - positioned
    //  n - non-positionable
    //  y - next potential position
    private static void dfs(int idx,  int r, int c, char[][] placements, int n, List<List<String>> ans) {
        //if (placements[r][c] != 'y') return; // this place is already filled, no placement possible here.

        // if idx == n, then we found an arrangement. record the path.
        if (idx == n) {
            List<String> a = new ArrayList<>();
            for (char[] row : placements) {
                a.add(new String(row)
                        .replaceAll("n",".")
                        .replaceAll("[0-9]", "."));
            }
            ans.add(a);
            uncommit(r,c,placements, String.valueOf(idx).charAt(0));
            return;
        }

        // prune: if all the placements non-positionable then return;
        // count next potential positions, if none available then no more states to recur further.
        int availableChoices = 0;
        for (int row = 0; row < placements.length; row++) {
            for (int col = 0; col < placements[row].length; col++) {
                if (placements[row][col] == 'y') availableChoices++;
            }
        }
        if (availableChoices > 0) {
            // commit a placement then explore the choices.
            commit(r, c, placements, String.valueOf(idx).charAt(0));
            for (int row = 0; row < placements.length; row++) {
                for (int col = 0; col < placements[row].length; col++) {
                    if (placements[row][col] == 'y') {
                        dfs(idx + 1, row, col, placements, n, ans);
                    }
                }
            }
            uncommit(r, c, placements, String.valueOf(idx).charAt(0));
        }
        //uncommit(r,c,placements, String.valueOf(idx).charAt(0));
        return;
        // commit(r,c):
        //    update all others in r to n
        //    update all others in c to n
        //    update all corners of [r,c] to n;
        // uncommit(r,c):
        //    update all others in r to y
        //    update all others in c to y
        //    update all corners of [r,c] to y;

        // idx, r, c, placements, n, ans
        // commit(idx); update placement[r,c]=x;

        // now for each empty cells
        //      recur
    }

    private static List<List<String>> arrangeV1(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] placements = new char[n][n];
        // fill the cells with 'y' to indicate potential fill positions.
        for (int r = 0; r < n; r++)
            for (int c = 0; c < n; c++)
                placements[r][c] = 'y';
        for (int r = 0; r < n; r++)
            for (int c = 0; c < n; c++)
                dfs(0, r, c, placements, n, ans);
        return ans;
    }

    public static List<List<String>> arrangeQueens(int n) {
        return arrangeV2(n);
    }
}
