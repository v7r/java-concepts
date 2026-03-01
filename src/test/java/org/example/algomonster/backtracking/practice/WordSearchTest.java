package org.example.algomonster.backtracking.practice;

import junit.framework.TestCase;

/**
 * Unit tests for WordSearch.wordExists.
 * These tests follow the problem statement examples and add edge cases that should
 * expose navigation/visited-path bugs in the implementation (missing left-movement, incorrect neighbor coords,
 * not preventing reuse of the same cell, etc).
 *
 * Do NOT modify production logic; tests assert the problem-statement expected outcomes so
 * any incorrect implementation will fail these tests.
 */
public class WordSearchTest extends TestCase {

    public void testExample_ABCCED_returnsTrue() {
        char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        assertTrue("Expected ABCCED to be found", WordSearch.wordExists("ABCCED", board));
    }

    public void testExample_SEE_returnsTrue() {
        char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        assertTrue("Expected SEE to be found", WordSearch.wordExists("SEE", board));
    }

    public void testExample_ABCB_returnsFalse() {
        char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        assertFalse("Expected ABCB to NOT be found", WordSearch.wordExists("ABCB", board));
    }

    public void testSingleCellTrueAndLongerFalse() {
        char[][] board = new char[][]{{'A'}};
        assertTrue(WordSearch.wordExists("A", board));
        assertFalse(WordSearch.wordExists("AA", board));
    }

    public void testCannotReuseSameCell_multipleAs() {
        // This case ensures the algorithm does not reuse the same cell multiple times
        char[][] board = new char[][]{
                {'a','a'},
                {'a','a'}
        };
        // There are 4 'a' cells; "aaaa" should be findable only if traversal uses distinct cells,
        // but "aaaaa" (5 a's) must be false because only 4 cells exist.
        assertTrue("Expected 4-length 'aaaa' to be found using distinct cells", WordSearch.wordExists("aaaa", board));
        assertFalse("Expected 5-length 'aaaaa' to NOT be found because it would require reuse", WordSearch.wordExists("aaaaa", board));
    }

    public void testWordThatRequiresLeftMovement() {
        // ABCCED uses a left move (from E at (2,2) to D at (2,1)). This test is redundant with ABCCED
        // but kept here to highlight left-neighbor traversal specifically.
        char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        assertTrue("Expected ABCCED (which requires left movement) to be found", WordSearch.wordExists("ABCCED", board));
    }

    public void testWordNotPresent() {
        char[][] board = new char[][]{
                {'X','Y','Z'},
                {'P','Q','R'}
        };
        assertFalse(WordSearch.wordExists("HELLO", board));
    }
}

