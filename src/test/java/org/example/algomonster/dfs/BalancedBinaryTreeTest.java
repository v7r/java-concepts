package org.example.algomonster.dfs;

import junit.framework.TestCase;

/**
 * Unit tests for BalancedBinaryTree.isBalanced
 * These tests challenge the implementation with edge-cases and tricky structures:
 * - null/empty tree
 * - single node
 * - simple balanced tree
 * - left-heavy trees with differences > 1
 * - a tree where an internal node is imbalanced but the root appears balanced
 * - multiple imbalanced nodes
 *
 * Do not modify production logic; tests assert expected behavior per problem statement.
 */
public class BalancedBinaryTreeTest extends TestCase {

    public void testEmptyTree() {
        // An empty tree is considered balanced
        assertTrue(BalancedBinaryTree.isBalanced(null));
    }

    public void testSingleNodeIsBalanced() {
        Node<Integer> root = new Node<>(1);
        assertTrue(BalancedBinaryTree.isBalanced(root));
    }

    public void testSimpleBalancedTree() {
        //       1
        //     /
        //    2     3
        //   /
        //  4        5
        Node<Integer> left = new Node<>(2, new Node<>(4), null);
        Node<Integer> right = new Node<>(3, null, new Node<>(5));
        Node<Integer> root = new Node<>(1, left, right);
        // left subtree height = 2, right subtree height = 2 -> balanced
        assertTrue(BalancedBinaryTree.isBalanced(root));
    }

    public void testLeftHeavyByMoreThanOneIsNotBalanced() {
        // left chain of length 4 vs right null -> clearly unbalanced
        Node<Integer> n4 = new Node<>(5);
        Node<Integer> n3 = new Node<>(4, n4, null);
        Node<Integer> n2 = new Node<>(3, n3, null);
        Node<Integer> n1 = new Node<>(2, n2, null);
        Node<Integer> root = new Node<>(1, n1, null);
        assertFalse(BalancedBinaryTree.isBalanced(root));
    }

    public void testInternalImbalanceButRootAppearsBalanced() {
        // Construct a tree where a deep internal node is imbalanced (difference > 1),
        // but the left and right subtree heights of the root are equal so the root itself
        // would look balanced -- the algorithm must still consider the internal imbalance.
        // Build left side with a deeply imbalanced internal node whose subtree height = 4
        Node<Integer> deep3 = new Node<>(7);
        Node<Integer> deep2 = new Node<>(6, deep3, null);
        Node<Integer> deep1 = new Node<>(5, deep2, null);
        Node<Integer> imbalanced = new Node<>(4, deep1, null); // this node has left depth 3, right 0 -> diff 3 >1

        // Build right subtree of root with same overall height (4) so root's children appear balanced
        Node<Integer> r3 = new Node<>(11);
        Node<Integer> r2 = new Node<>(10, r3, null);
        Node<Integer> r1 = new Node<>(9, r2, null);
        Node<Integer> right = new Node<>(8, r1, null);

        Node<Integer> root = new Node<>(1, imbalanced, right);

        // Even though root's left and right heights are equal, a deeper node is imbalanced,
        // so the tree is not balanced overall.
        assertFalse(BalancedBinaryTree.isBalanced(root));
    }

    public void testMultipleImbalancedNodes() {
        // Create a tree with several imbalanced nodes to ensure counting/short-circuit isn't hiding them
        // Left subtree: imbalanced
        Node<Integer> a3 = new Node<>(20);
        Node<Integer> a2 = new Node<>(19, a3, null);
        Node<Integer> a1 = new Node<>(18, a2, null);
        Node<Integer> left = new Node<>(17, a1, null);

        // Right subtree: also imbalanced in a different place
        Node<Integer> bLeaf = new Node<>(30);
        Node<Integer> bRight = new Node<>(29, bLeaf, null);
        Node<Integer> right = new Node<>(28, null, bRight);

        Node<Integer> root = new Node<>(10, left, right);

        assertFalse(BalancedBinaryTree.isBalanced(root));
    }
}

