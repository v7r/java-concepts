package org.example.algomonster.dfs;

import junit.framework.TestCase;

/**
 * Tests for TreeTraversalPreOrder.preOrderTraversal
 * These tests follow the standard pre-order traversal (root-left-right) and ensure
 * output is values separated by single spaces with no leading/trailing spaces.
 *
 * The implementation concatenates strings and trims, which can leave double spaces
 * when one subtree is empty and the other is non-empty; tests below are designed to
 * catch that behavior.
 */
public class TreeTraversalPreOrderTest extends TestCase {

    public void testEmptyTreeReturnsEmptyString() {
        assertEquals("", TreeTraversalPreOrder.preOrderTraversal(null));
    }

    public void testSingleNode() {
        Node<Integer> root = new Node<>(1);
        String out = TreeTraversalPreOrder.preOrderTraversal(root);
        assertEquals("1", out);
        assertFalse(out.startsWith(" "));
        assertFalse(out.endsWith(" "));
    }

    public void testBalancedThreeNodeTree() {
        //    1
        //   / \
        //  2   3
        Node<Integer> root = new Node<>(1, new Node<>(2), new Node<>(3));
        String out = TreeTraversalPreOrder.preOrderTraversal(root);
        assertEquals("1 2 3", out);
        assertFalse(out.contains("  "));
    }

    public void testLeftSkewedPreorder() {
        // left skewed: 1 -> left 2 -> left 3 => preorder: 1 2 3
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n2 = new Node<>(2, n3, null);
        Node<Integer> root = new Node<>(1, n2, null);
        String out = TreeTraversalPreOrder.preOrderTraversal(root);
        assertEquals("1 2 3", out);
    }

    public void testRightSkewedPreorder() {
        // right skewed: 1 -> right 2 -> right 3 => preorder: 1 2 3
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n2 = new Node<>(2, null, n3);
        Node<Integer> root = new Node<>(1, null, n2);
        String out = TreeTraversalPreOrder.preOrderTraversal(root);
        // This case often exposes double-space bug: root + " " + "" + " " + "2 3" -> "1  2 3"
        // Expectation per problem statement: single spaces
        assertEquals("1 2 3", out);
        assertFalse("Preorder output should not contain consecutive spaces", out.contains("  "));
    }

    public void testNodeWithOnlyRightChildExposesDoubleSpace() {
        // root with only right child should yield "1 2" (no double spaces)
        Node<Integer> root = new Node<>(1, null, new Node<>(2));
        String out = TreeTraversalPreOrder.preOrderTraversal(root);
        assertEquals("1 2", out);
        assertFalse(out.contains("  "));
    }

    public void testTreeWithDuplicateValues() {
        //    1
        //   / \
        //  1   1
        Node<Integer> root = new Node<>(1, new Node<>(1), new Node<>(1));
        String out = TreeTraversalPreOrder.preOrderTraversal(root);
        assertEquals("1 1 1", out);
    }

    public void testComplexTreeNormalizedSpacing() {
        // Build a complex tree and ensure preorder output is normalized
        Node<Integer> n7 = new Node<>(7);
        Node<Integer> n6 = new Node<>(6);
        Node<Integer> n5 = new Node<>(5, n6, n7);
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n3 = new Node<>(3, n4, n5);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> root = new Node<>(1, n2, n3);

        // preorder expected: 1 2 3 4 5 6 7
        String out = TreeTraversalPreOrder.preOrderTraversal(root);
        assertEquals("1 2 3 4 5 6 7", out);
        assertFalse(out.startsWith(" "));
        assertFalse(out.endsWith(" "));
        assertFalse(out.contains("  "));
    }
}

