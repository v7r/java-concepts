package org.example.algomonster.bfs;

import junit.framework.TestCase;
import org.example.algomonster.dfs.Node;

import java.util.Arrays;
import java.util.List;

/**
 * Tests for BinaryTreeZigZagLevelOrderTraversal.zigZagTraversal
 * These tests follow the problem statement (alternating left-to-right per level) and
 * include cases that expose common implementation bugs: reusing a single list for all levels,
 * incorrect ordering (sorting instead of reversing), and null-root handling.
 * Do NOT modify production logic; tests are intentionally strict to surface defects.
 */
public class BinaryTreeZigZagLevelOrderTraversalTest extends TestCase {

    public void testBalancedTreeZigZag() {
        // Tree:
        //    3
        //   / \
        //  9  20
        //    /  \
        //   15   7
        Node<Integer> root = new Node<>(3,
                new Node<>(9),
                new Node<>(20, new Node<>(15), new Node<>(7))
        );

        List<List<Integer>> res = BinaryTreeZigZagLevelOrderTraversal.zigZagTraversal(root);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(3),
                Arrays.asList(20, 9),
                Arrays.asList(15, 7)
        );

        assertEquals("Zig-zag ordering should alternate direction per level", expected, res);
    }

    public void testListsAreIndependentPerLevel() {
        Node<Integer> root = new Node<>(3,
                new Node<>(9),
                new Node<>(20, new Node<>(15), new Node<>(7))
        );
        List<List<Integer>> res = BinaryTreeZigZagLevelOrderTraversal.zigZagTraversal(root);
        // Each level should be represented by a distinct List object; ensure no aliasing/sharing
        assertTrue("Expected at least 2 levels", res.size() >= 2);
        for (int i = 0; i < res.size() - 1; i++) {
            assertNotSame("Level lists must be independent objects", res.get(i), res.get(i + 1));
        }
    }

    public void testNullRootThrowsNPE() {
        try {
            BinaryTreeZigZagLevelOrderTraversal.zigZagTraversal(null);
            fail("Expected NullPointerException when passing null root (current implementation offers root into deque)");
        } catch (NullPointerException expected) {
            // expected - production code enqueues root without null check
        }
    }

    public void testRightSkewedTree() {
        // Right skewed: 1 -> 2 -> 3
        Node<Integer> root = new Node<>(1, null, new Node<>(2, null, new Node<>(3)));
        List<List<Integer>> res = BinaryTreeZigZagLevelOrderTraversal.zigZagTraversal(root);
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3)
        );
        assertEquals("Right-skewed tree should produce single element per level (zig-zag irrelevant)", expected, res);
    }
}

