package org.example.algomonster.dfs;

import junit.framework.TestCase;

/**
 * Tests for LowestCommonAncesterOfaBinaryTree.lca
 * These tests follow the standard Lowest Common Ancestor problem statement and are
 * intentionally written to challenge the current implementation (which has a known bug
 * where the right subtree is not searched correctly).
 *
 * Do not modify production logic; tests assert the expected correct behavior.
 */
public class LowestCommonAncesterOfaBinaryTreeTest extends TestCase {

    public void testNullRootReturnsNull() {
        assertNull(LowestCommonAncesterOfaBinaryTree.lca(null, new Node<>(1), new Node<>(2)));
    }

    public void testLcaIsRootForNodesOnDifferentSides() {
        // build example tree: [6,2,8,0,4,7,9,null,null,3,5]
        Node<Integer> root = buildExampleTree();
        Node<Integer> n2 = root.left;
        Node<Integer> n8 = root.right;
        Node<Integer> res = LowestCommonAncesterOfaBinaryTree.lca(root, n2, n8);
        // expected LCA is root (6)
        assertSame(root, res);
    }

    public void testLcaInLeftSubtree() {
        Node<Integer> root = buildExampleTree();
        Node<Integer> n0 = root.left.left; // 0
        Node<Integer> n5 = root.left.right.right; // 5
        Node<Integer> expected = root.left; // 2
        Node<Integer> res = LowestCommonAncesterOfaBinaryTree.lca(root, n0, n5);
        assertSame(expected, res);
    }

    public void testLcaInRightSubtree() {
        Node<Integer> root = buildExampleTree();
        Node<Integer> n7 = root.right.left; // 7
        Node<Integer> n9 = root.right.right; // 9
        Node<Integer> expected = root.right; // 8
        Node<Integer> res = LowestCommonAncesterOfaBinaryTree.lca(root, n7, n9);
        assertSame(expected, res);
    }

    public void testOneNodeIsAncestorOfOther() {
        Node<Integer> root = buildExampleTree();
        Node<Integer> n2 = root.left; // 2
        Node<Integer> n4 = root.left.right; // 4
        Node<Integer> res = LowestCommonAncesterOfaBinaryTree.lca(root, n2, n4);
        // when one node is ancestor of the other, the ancestor is the LCA
        assertSame(n2, res);
    }

    public void testSameNodeAsBothTargets() {
        Node<Integer> root = buildExampleTree();
        Node<Integer> n3 = root.left.right.left; // 3
        Node<Integer> res = LowestCommonAncesterOfaBinaryTree.lca(root, n3, n3);
        assertSame(n3, res);
    }

    // Helper - builds the example binary tree used in many LCA problems
    private Node<Integer> buildExampleTree() {
        Node<Integer> n0 = new Node<>(0);
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n5 = new Node<>(5);
        Node<Integer> n4 = new Node<>(4, n3, n5);
        Node<Integer> n2 = new Node<>(2, n0, n4);
        Node<Integer> n7 = new Node<>(7);
        Node<Integer> n9 = new Node<>(9);
        Node<Integer> n8 = new Node<>(8, n7, n9);
        Node<Integer> root = new Node<>(6, n2, n8);
        return root;
    }
}

