package org.example.algomonster.backtracking.practice;

import junit.framework.TestCase;

/**
 * Tests for SudokuSolver.sudokuSolver
 * These tests follow the standard 9x9 Sudoku solver problem statement and are
 * designed to exercise correctness (solving the given puzzle) and robustness.
 * Do NOT modify production logic; tests assert expected behavior so failures will
 * indicate areas in need of fixes in the implementation.
 */
public class SudokuSolverTest extends TestCase {

    public void testSolveClassicExample() {
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        boolean solved = SudokuSolver.sudokuSolver(board);
        assertTrue("Solver should return true for the provided valid puzzle", solved);

        char[][] expected = new char[][]{
                {'5','3','4','6','7','8','9','1','2'},
                {'6','7','2','1','9','5','3','4','8'},
                {'1','9','8','3','4','2','5','6','7'},
                {'8','5','9','7','6','1','4','2','3'},
                {'4','2','6','8','5','3','7','9','1'},
                {'7','1','3','9','2','4','8','5','6'},
                {'9','6','1','5','3','7','2','8','4'},
                {'2','8','7','4','1','9','6','3','5'},
                {'3','4','5','2','8','6','1','7','9'}
        };

        assertBoardsEqual(expected, board);
    }

    public void testAlreadySolvedBoardReturnsTrueAndRemainsSame() {
        char[][] solved = new char[][]{
                {'5','3','4','6','7','8','9','1','2'},
                {'6','7','2','1','9','5','3','4','8'},
                {'1','9','8','3','4','2','5','6','7'},
                {'8','5','9','7','6','1','4','2','3'},
                {'4','2','6','8','5','3','7','9','1'},
                {'7','1','3','9','2','4','8','5','6'},
                {'9','6','1','5','3','7','2','8','4'},
                {'2','8','7','4','1','9','6','3','5'},
                {'3','4','5','2','8','6','1','7','9'}
        };
        char[][] copy = deepCopy(solved);
        boolean res = SudokuSolver.sudokuSolver(copy);
        assertTrue("Solver should return true for an already-solved board", res);
        assertBoardsEqual(solved, copy);
    }

    public void testSingleEmptyCell() {
        char[][] board = new char[][]{
                {'5','3','4','6','7','8','9','1','2'},
                {'6','7','2','1','9','5','3','4','8'},
                {'1','9','8','3','4','2','5','6','7'},
                {'8','5','9','7','6','1','4','2','3'},
                {'4','2','6','8','5','3','7','9','1'},
                {'7','1','3','9','2','4','8','5','6'},
                {'9','6','1','5','3','7','2','8','4'},
                {'2','8','7','4','1','9','6','3','5'},
                {'3','4','5','2','8','6','1','7','.'}
        };
        boolean res = SudokuSolver.sudokuSolver(board);
        assertTrue("Solver should fill the single empty cell and return true", res);
        // the last cell must be '9' to complete the solved board
        assertEquals('9', board[8][8]);
    }

    // Helpers
    private void assertBoardsEqual(char[][] expected, char[][] actual) {
        assertNotNull(actual);
        assertEquals("Board must be 9 rows", 9, actual.length);
        for (int r = 0; r < 9; r++) {
            assertEquals("Each row must have 9 columns", 9, actual[r].length);
            for (int c = 0; c < 9; c++) {
                assertEquals("Mismatch at cell ("+r+","+c+")", expected[r][c], actual[r][c]);
            }
        }
    }

    private char[][] deepCopy(char[][] src) {
        char[][] out = new char[src.length][src[0].length];
        for (int i = 0; i < src.length; i++) System.arraycopy(src[i], 0, out[i], 0, src[i].length);
        return out;
    }
}

