package org.example.algomonster.dfs;

import junit.framework.TestCase;

/**
 * Tests for MaxDepthOfATree.treeMaxDepth(Node).
 * The implementation defines depth as number of edges on the longest root-to-leaf path.
 */
public class MaxDepthOfATreeTest extends TestCase {

    public void testNullTree() {
        // empty tree should have depth 0 according to current implementation
        assertEquals(0, MaxDepthOfATree.treeMaxDepth(null));
    }

    public void testSingleNode() {
        Node<Integer> root = new Node<>(1);
        // single node -> 0 edges
        assertEquals(0, MaxDepthOfATree.treeMaxDepth(root));
    }

    public void testLeftChildOnly() {
        Node<Integer> left = new Node<>(2);
        Node<Integer> root = new Node<>(1, left, null);
        // one edge to left child
        assertEquals(1, MaxDepthOfATree.treeMaxDepth(root));
    }

    public void testRightChildOnly() {
        Node<Integer> right = new Node<>(3);
        Node<Integer> root = new Node<>(1, null, right);
        // one edge to right child
        assertEquals(1, MaxDepthOfATree.treeMaxDepth(root));
    }

    public void testLeftSkewedTree() {
        // chain of three nodes on the left: depth should be 2 edges
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n2 = new Node<>(2, n3, null);
        Node<Integer> root = new Node<>(1, n2, null);
        assertEquals(2, MaxDepthOfATree.treeMaxDepth(root));
    }

    public void testBalancedTree() {
        // left subtree height 2 edges, right subtree height 2 edges -> overall 2
        Node<Integer> l2 = new Node<>(4);
        Node<Integer> r2 = new Node<>(5);
        Node<Integer> l1 = new Node<>(2, l2, null);
        Node<Integer> r1 = new Node<>(3, null, r2);
        Node<Integer> root = new Node<>(1, l1, r1);
        assertEquals(2, MaxDepthOfATree.treeMaxDepth(root));
    }

    public void testComplexUnbalancedTree() {
        // left side has 3 edges deep, right side has 1 -> expect 3
        Node<Integer> deep = new Node<>(6);
        Node<Integer> d2 = new Node<>(5, deep, null);
        Node<Integer> d1 = new Node<>(4, d2, null);
        Node<Integer> left = new Node<>(2, d1, null);
        Node<Integer> right = new Node<>(3);
        Node<Integer> root = new Node<>(1, left, right);
        assertEquals(4, MaxDepthOfATree.treeMaxDepth(root));
    }
}

