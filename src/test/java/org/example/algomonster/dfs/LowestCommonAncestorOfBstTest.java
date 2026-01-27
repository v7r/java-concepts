package org.example.algomonster.dfs;

import junit.framework.TestCase;

/**
 * Tests for LowestCommonAncestorOfBst.lcaOnBst
 * These tests follow the standard BST LCA problem statement and include cases that
 * exercise edge-cases and likely failure modes in the current implementation.
 * Do not modify production logic.
 */
public class LowestCommonAncestorOfBstTest extends TestCase {

    public void testExampleTreeBasic() {
        // bst = [6,2,8,0,4,7,9,x,x,3,5]
        Node<Integer> root = buildExampleTree();
        assertEquals(6, LowestCommonAncestorOfBst.lcaOnBst(root, 2, 8));
    }

    public void testAncestorWhenOneIsAncestorOfOther() {
        Node<Integer> root = buildExampleTree();
        // p = 2 is ancestor of q = 4 -> LCA = 2
        assertEquals(2, LowestCommonAncestorOfBst.lcaOnBst(root, 2, 4));
    }

    public void testBothOnLeftSubtree() {
        Node<Integer> root = buildExampleTree();
        // p=0, q=5 both under left subtree of 6, LCA should be 2
        assertEquals(2, LowestCommonAncestorOfBst.lcaOnBst(root, 0, 5));
    }

    public void testBothOnRightSubtree() {
        Node<Integer> root = buildExampleTree();
        // p=7, q=9 both under right subtree of 6, LCA should be 8
        assertEquals(8, LowestCommonAncestorOfBst.lcaOnBst(root, 7, 9));
    }

    public void testSwappedInputsOrder() {
        Node<Integer> root = buildExampleTree();
        // order shouldn't matter
        assertEquals(6, LowestCommonAncestorOfBst.lcaOnBst(root, 8, 2));
    }

    public void testSameNodeValues() {
        Node<Integer> root = buildExampleTree();
        // p==q==2 -> LCA should be 2
        assertEquals(2, LowestCommonAncestorOfBst.lcaOnBst(root, 2, 2));
    }

    public void testNullRootThrows() {
        try {
            LowestCommonAncestorOfBst.lcaOnBst(null, 1, 2);
            fail("Expected NullPointerException when BST root is null");
        } catch (NullPointerException ex) {
            // expected
        }
    }

    public void testSkewedTreeLca() {
        // create right-skewed BST: 1 -> 2 -> 3 -> 4
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n3 = new Node<>(3, null, n4);
        Node<Integer> n2 = new Node<>(2, null, n3);
        Node<Integer> root = new Node<>(1, null, n2);
        // LCA of 3 and 4 is 3
        assertEquals(3, LowestCommonAncestorOfBst.lcaOnBst(root, 3, 4));
        // LCA of 1 and 4 is 1
        assertEquals(1, LowestCommonAncestorOfBst.lcaOnBst(root, 1, 4));
    }

    // Helper: builds the example tree used in problem statement
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

