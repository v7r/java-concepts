package org.example.algomonster.bfs;

import junit.framework.TestCase;
import org.example.algomonster.dfs.Node;

import java.util.Arrays;
import java.util.List;

/**
 * Unit tests for BinaryTreeLevelOrderTraversal.levelOrderTraversal
 * These tests cover typical trees and edge-cases intended to challenge the implementation.
 * Do NOT modify production logic; tests assert problem-statement expectations and also
 * document current behavior for pathological inputs (null root).
 */
public class BinaryTreeLevelOrderTraversalTest extends TestCase {

    public void testSingleNodeTree() {
        Node<Integer> root = new Node<>(1);
        List<List<Integer>> res = BinaryTreeLevelOrderTraversal.levelOrderTraversal(root);
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1)
        );
        assertEquals(expected, res);
    }

    public void testBalancedTreeThreeLevels() {
        // tree:   3
        //       /   \
        //      9    20
        //          /  \
        //         15   7
        Node<Integer> root = new Node<>(3,
                new Node<>(9),
                new Node<>(20, new Node<>(15), new Node<>(7))
        );
        List<List<Integer>> res = BinaryTreeLevelOrderTraversal.levelOrderTraversal(root);
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(3),
                Arrays.asList(9,20),
                Arrays.asList(15,7)
        );
        assertEquals(expected, res);
    }

    public void testRightSkewedTree() {
        // 1 -> 2 -> 3
        Node<Integer> root = new Node<>(1, null, new Node<>(2, null, new Node<>(3)));
        List<List<Integer>> res = BinaryTreeLevelOrderTraversal.levelOrderTraversal(root);
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3)
        );
        assertEquals(expected, res);
    }

    public void testLeftSkewedTree() {
        Node<Integer> root = new Node<>(1, new Node<>(2, new Node<>(3), null), null);
        List<List<Integer>> res = BinaryTreeLevelOrderTraversal.levelOrderTraversal(root);
        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3)
        );
        assertEquals(expected, res);
    }

    public void testNullRootThrowsNPE() {
        try {
            BinaryTreeLevelOrderTraversal.levelOrderTraversal(null);
            fail("Expected NullPointerException for null root due to current implementation behavior");
        } catch (NullPointerException expected) {
            // expected - production code offers null into deque and dereferences node.val
        }
    }

    public void testReturnedListIndependenceBetweenCalls() {
        Node<Integer> root = new Node<>(1, new Node<>(2), new Node<>(3));
        List<List<Integer>> first = BinaryTreeLevelOrderTraversal.levelOrderTraversal(root);
        // mutate the returned result
        if (!first.isEmpty() && !first.get(0).isEmpty()) first.get(0).set(0, 999);
        List<List<Integer>> second = BinaryTreeLevelOrderTraversal.levelOrderTraversal(root);
        // second should not contain the mutated value
        assertFalse("Subsequent call should not be affected by mutation of previous result", second.get(0).contains(999));
    }
}

