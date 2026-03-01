package org.example.algomonster.backtracking.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem statement
 *
 * Given an m x n grid of characters board and a string word, return true if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or
 * vertically neighboring.
 *
 * The same cell may not be used more than once in a word.
 *
 * Examples
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * word = "ABCCED" → true
 * word = "SEE"    → true
 * word = "ABCB"   → false
 */
public class WordSearch {

    // Canonical logic; that chatgpt guided me.
    private static boolean dfs(int idx, int row, int col, boolean[][] visitedPath, String word, char[][] grid) {
        if (idx == word.length()) return true; // base case when idx reached to the end then we found the word.
        if (row >= grid.length || row < 0 || col >= grid[0].length || col < 0) return false; // prune beyond grid boundaries
        if (visitedPath[row][col]) return false; // prune visited paths.
        if (word.charAt(idx) != grid[row][col]) return false;
        visitedPath[row][col] = true;
        boolean found = dfs(idx+1, row+1, col, visitedPath, word, grid) ||
                dfs(idx+1, row-1, col, visitedPath, word, grid) ||
                dfs(idx+1, row, col+1, visitedPath, word, grid) ||
                dfs(idx+1, row, col-1, visitedPath, word, grid);
        visitedPath[row][col] = false;
        return found;
    }

    private static boolean findWord(String word, char[][] grid) {
        if (word == null || grid == null || word.length() > grid.length * grid[0].length) return false;
        boolean[][] visitedPath = new boolean[grid.length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (dfs(0, row, col, visitedPath, word, grid)) return true;
            }
        }
        return false;
    }

    // if (idx == word.length()) return true;
    // if tracing started (idx>0);
    //   if path(row,col) is already visited then return false; // pruning
    //   check if the char at row,col is equal to word.charAt(idx);
    //   if it matches, then record the visited path(row,col)
    //       next choices for idx+1 is to recurse with (row-1,col),(row+1,col),(row,col+1) and (row,col-1)
    //       if any returns true return true else return false;
    //   else return false;
    // else (i.e, idx==0)
    //   for each row
    //      for each colum
    //           check if word.charAt(idx) == grid[row][col] then
    //              start the word traversal;
    //              record path(row,col) as visited.
    //              next choices for idx+1 is to recurse with (row-1,col),(row+1,col),(row,col+1) and (row,col-1).
    //              clear path(row,col);
    //              if any return true then return true;
    //              else idx = 0;
    //
    // after the grid iteration return false;
    private static boolean dfsV2(int idx, int row, int col, Map<String, Boolean> visitedPath, String word, char[][] grid) {
        if (idx == word.length()) return true;

        String pathKey = String.join(",",String.valueOf(row),String.valueOf(col));

        // If tracing word started then do not revisit the same path;
        char currentChar = word.charAt(idx);

        if (idx > 0) {
            // Tracing started;
            // Visited paths are pruned;
            if (visitedPath.get(pathKey) != null && visitedPath.get(pathKey)) return false;

            //Beyond Row and Column borders are pruned;
            if (row >= grid.length || row < 0) return false;
            if (col >= grid[0].length || col < 0) return false;

            char gridChar = grid[row][col];
            if (currentChar == gridChar) {
                visitedPath.put(pathKey, Boolean.TRUE);
                // Next choices we have is to recurse with idx+1 on the adjacent cells.
                return dfsV2(idx+1, row+1, col, visitedPath, word, grid) ||
                        dfsV2(idx+1, row-1, col, visitedPath, word, grid) ||
                        dfsV2(idx+1, row, col+1, visitedPath, word, grid) ||
                        dfsV2(idx+1, row, col-1, visitedPath, word, grid);
            } else {
                // Grid character does not match with the current idx char
                return false;
            }
        }

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                boolean found = false;
                if (grid[r][c] == currentChar) {
                    pathKey = String.join(",",String.valueOf(r),String.valueOf(c));
                    // Track traversed path to prevent re-visiting in the tree;
                    visitedPath.put(pathKey,true);
                    // if current char at idx matches then I
                    found = dfsV2(idx+1,r-1, c, visitedPath, word,grid) ||
                            dfsV2(idx+1,r+1, c, visitedPath, word,grid) ||
                            dfsV2(idx+1,r, c+1, visitedPath, word,grid) ||
                            dfsV2(idx+1,r, c-1, visitedPath, word,grid);
                    if (found) return true;
                    visitedPath.clear();
                }
            }
        }
        return false;
    }

    private static boolean dfsV1(int idx, int row, int col, Map<String, Boolean> visitedPath, String word, char[][] grid) {
        if (idx == word.length()) return true;
        String pathKey = String.join(",",String.valueOf(row),String.valueOf(col));

        // Skip visited paths.
        if (visitedPath.get(pathKey) != null && visitedPath.get(pathKey)) return false;

        if (row >= grid.length || row < 0) return false;
        if (col >= grid[0].length || col < 0) return false;

        char currentChar = word.charAt(idx);

        if (grid[row][col] == currentChar) {
            // Track traversed path to prevent re-visiting in the tree;
            visitedPath.put(pathKey,true);
            // if current char at idx matches then I
            return dfsV1(idx+1,row-1, col, visitedPath, word,grid) ||
                    dfsV1(idx+1,row+1, col, visitedPath, word,grid) ||
                    dfsV1(idx+1,row, col+1, visitedPath, word,grid) ||
                    dfsV1(idx+1,row, col-1, visitedPath, word,grid);
        } else {
            // Increment to next cell in the grid;
            if (col+1 < grid[0].length) {
                col++;
            } else {
                row++; col = 0;
            }
            // reset visited path;
            visitedPath.clear();
            return dfsV1(0, row, col, visitedPath, word, grid);
        }
    }

    public static boolean wordExists(String word, char[][] grid) {
        //return dfsV2(0, 0, 0, new HashMap<>(), word, grid);
        return findWord(word, grid);
    }
}
