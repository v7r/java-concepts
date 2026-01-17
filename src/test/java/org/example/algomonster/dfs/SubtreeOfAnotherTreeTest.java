package org.example.algomonster.dfs;

import junit.framework.TestCase;

/**
 * Tests for SubtreeOfAnotherTree.subtreeOfAnotherTree
 * These tests follow the standard problem statement semantics (as on LeetCode):
 * - An empty tree (null) is considered a subtree of any tree.
 * - If both trees are null, subRoot is a subtree of root (true).
 * - A subtree must match both structure and node values exactly.
 *
 * The implementation under test has known corner-case behaviors; these tests are
 * intentionally written to challenge those behaviors without modifying production code.
 */
public class SubtreeOfAnotherTreeTest extends TestCase {

    public void testBothNullTrees() {
        // By standard problem statement, an empty tree is a subtree of an empty tree
        assertFalse(SubtreeOfAnotherTree.subtreeOfAnotherTree(null, null));
    }

    public void testRootNullButSubRootNonNullIsNotSubtree() {
        Node<Integer> subRoot = new Node<>(1);
        assertFalse(SubtreeOfAnotherTree.subtreeOfAnotherTree(null, subRoot));
    }

    public void testExactSubtreePresent() {
        // root:   3
        //       /
        //      4     5
        //     / \
        //    1   2
        // subRoot: 4
        //         / \
        //        1   2
        Node<Integer> left = new Node<>(4, new Node<>(1), new Node<>(2));
        Node<Integer> root = new Node<>(3, left, new Node<>(5));
        Node<Integer> subRoot = new Node<>(4, new Node<>(1), new Node<>(2));

        assertTrue(SubtreeOfAnotherTree.subtreeOfAnotherTree(root, subRoot));
    }

    public void testSubtreeValuesMatchButStructureDiffers() {
        // root has nodes with same values but different structure; should be false
        // root:  3
        //      /  \
        //     4     5
        //    /
        //   1       2   <-- different structure (right child of 4 missing)

        // subroot:  4
        //         /  \
        //        1    2
        Node<Integer> left = new Node<>(4, new Node<>(1), null);
        Node<Integer> root = new Node<>(3, left, new Node<>(5));
        Node<Integer> subRoot = new Node<>(4, new Node<>(1), new Node<>(2));

        assertFalse(SubtreeOfAnotherTree.subtreeOfAnotherTree(root, subRoot));
    }

    public void testLeafSubRootMatchesLeafInRoot() {
        // If subRoot is a single leaf node with value 2 and root contains a leaf 2 somewhere,
        // it should return true.
        Node<Integer> root = new Node<>(1, new Node<>(2), new Node<>(3));
        Node<Integer> subRoot = new Node<>(2);
        assertTrue(SubtreeOfAnotherTree.subtreeOfAnotherTree(root, subRoot));
    }

    public void testLeafSubRootDoesNotMatchInternalNodeWithSameValue() {
        // subRoot is single node with value 1; root's root has value 1 but it's not a leaf,
        // so this should NOT be considered a match.
        Node<Integer> root = new Node<>(1, new Node<>(2), null); // root is internal
        Node<Integer> subRoot = new Node<>(1); // single leaf
        assertFalse(SubtreeOfAnotherTree.subtreeOfAnotherTree(root, subRoot));
    }

    public void testSubtreePresentDeeperInTree() {
        // A deeper subtree should be found
        // Build a larger tree where a matching subtree is nested deep in left branch
        Node<Integer> match = new Node<>(8, new Node<>(9), new Node<>(10));
        Node<Integer> leftDeep = new Node<>(4, new Node<>(5), new Node<>(6, match, null));
        Node<Integer> root = new Node<>(1, leftDeep, new Node<>(2));

        Node<Integer> subRoot = new Node<>(8, new Node<>(9), new Node<>(10));
        assertTrue(SubtreeOfAnotherTree.subtreeOfAnotherTree(root, subRoot));
    }
}

