package org.example.algomonster.dfs;

import junit.framework.TestCase;

/**
 * Tests for ValidBinarySearchTree.validBst
 * These tests follow the standard problem statement: an in-order traversal of a valid BST
 * yields a strictly increasing sequence. Tests are written to challenge common implementation
 * mistakes (incorrect bound handling, allowing duplicates, null handling).
 *
 * NOTE: Do not modify production logic; the tests intentionally assert the problem-statement
 * expected behavior even if the current implementation doesn't satisfy it.
 */
public class ValidBinarySearchTreeTest extends TestCase {

    public void testNullTreeIsValid() {
        assertTrue(ValidBinarySearchTree.validBst(null));
    }

    public void testSingleNodeIsValid() {
        Node<Integer> root = new Node<>(1);
        assertTrue(ValidBinarySearchTree.validBst(root));
    }

    public void testSimpleValidBst() {
        //    2
        //   / \
        //  1   3
        Node<Integer> root = new Node<>(2, new Node<>(1), new Node<>(3));
        assertTrue(ValidBinarySearchTree.validBst(root));
    }

    public void testInvalidBecauseLeftHasGreaterValue() {
        //    2
        //   /
        //  5   <-- invalid: left child > root
        Node<Integer> root = new Node<>(2, new Node<>(5), null);
        assertFalse(ValidBinarySearchTree.validBst(root));
    }

    public void testInvalidBecauseRightHasLesserValue() {
        //  2
        //   \
        //    1  <-- invalid: right child < root
        Node<Integer> root = new Node<>(2, null, new Node<>(1));
        assertFalse(ValidBinarySearchTree.validBst(root));
    }

    public void testDuplicateValuesAreNotAllowed() {
        //  2
        // / \
        //2   3  -> duplicate '2' should make it invalid (strictly increasing required)
        Node<Integer> root = new Node<>(2, new Node<>(2), new Node<>(3));
        assertFalse("Duplicates should invalidate BST (inorder must be strictly increasing)", ValidBinarySearchTree.validBst(root));
    }

    public void testComplexInvalidSubtree() {
        // Build a tree where a deeper node violates the BST property
        //       10
        //      /  \
        //     5    15
        //         /  \
        //        6    20   <-- 6 in right subtree of 10 invalid
        Node<Integer> right = new Node<>(15, new Node<>(6), new Node<>(20));
        Node<Integer> root = new Node<>(10, new Node<>(5), right);
        assertFalse(ValidBinarySearchTree.validBst(root));
    }
}

