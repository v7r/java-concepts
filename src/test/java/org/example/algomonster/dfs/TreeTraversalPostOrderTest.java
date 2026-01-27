package org.example.algomonster.dfs;

import junit.framework.TestCase;

/**
 * Tests for TreeTraversalPostOrder.preOrderTraversal (post-order traversal implementation).
 * These tests follow the problem statement (left-right-root) and challenge spacing and
 * structure handling (null children, skewed trees, duplicates).
 *
 * Note: the production method is named preOrderTraversal but actually implements post-order.
 */
public class TreeTraversalPostOrderTest extends TestCase {

    public void testEmptyTreeReturnsEmptyString() {
        assertEquals("", TreeTraversalPostOrder.preOrderTraversal(null));
    }

    public void testSingleNodeNoExtraSpaces() {
        Node<Integer> root = new Node<>(1);
        String out = TreeTraversalPostOrder.preOrderTraversal(root);
        assertEquals("1", out);
        assertFalse(out.startsWith(" "));
        assertFalse(out.endsWith(" "));
    }

    public void testBalancedThreeNodeTree() {
        //    1
        //   / \
        //  2   3
        // post-order: 2 3 1
        Node<Integer> root = new Node<>(1, new Node<>(2), new Node<>(3));
        String out = TreeTraversalPostOrder.preOrderTraversal(root);
        assertEquals("2 3 1", out);
        assertFalse(out.contains("  "));
    }

    public void testLeftSkewedTree() {
        // left skewed: 1 -> left 2 -> left 3  -> post-order: 3 2 1
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n2 = new Node<>(2, n3, null);
        Node<Integer> root = new Node<>(1, n2, null);
        String out = TreeTraversalPostOrder.preOrderTraversal(root);
        assertEquals("3 2 1", out);
        assertFalse(out.startsWith(" "));
        assertFalse(out.endsWith(" "));
    }

    public void testRightSkewedTree() {
        // right skewed: 1 -> right 2 -> right 3  -> post-order: 3 2 1
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n2 = new Node<>(2, null, n3);
        Node<Integer> root = new Node<>(1, null, n2);
        String out = TreeTraversalPostOrder.preOrderTraversal(root);
        assertEquals("3 2 1", out);
        assertFalse(out.contains("  "));
    }

    public void testTreeWithDuplicateValues() {
        //   2
        //  / \
        // 2   2  -> post-order: 2 2 2
        Node<Integer> root = new Node<>(2, new Node<>(2), new Node<>(2));
        String out = TreeTraversalPostOrder.preOrderTraversal(root);
        assertEquals("2 2 2", out);
    }

    public void testComplexTreeNormalizedSpacing() {
        // Build a moderate-sized tree and ensure we get normalized single-space separation
        Node<Integer> n7 = new Node<>(7);
        Node<Integer> n6 = new Node<>(6);
        Node<Integer> n5 = new Node<>(5, n6, n7);
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n3 = new Node<>(3, n4, n5);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> root = new Node<>(1, n2, n3);

        String out = TreeTraversalPostOrder.preOrderTraversal(root);
        // expected post-order: 2 4 6 7 5 3 1
        assertEquals("2 4 6 7 5 3 1", out);
        assertFalse(out.startsWith(" "));
        assertFalse(out.endsWith(" "));
        assertFalse(out.contains("  "));
    }
}

