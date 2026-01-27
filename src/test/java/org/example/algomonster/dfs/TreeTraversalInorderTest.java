package org.example.algomonster.dfs;

import junit.framework.TestCase;

/**
 * Tests for TreeTraversalInorder.inorderTraversal
 * These tests follow the problem statement: inorder traversal should produce node values
 * in the left-root-right order separated by single spaces, with no leading/trailing spaces.
 *
 * The implementation under test concatenates strings with spaces recursively which can
 * produce extra/multiple spaces; these tests are designed to catch that and other issues.
 */
public class TreeTraversalInorderTest extends TestCase {

    public void testEmptyTreeReturnsEmptyString() {
        assertEquals("", TreeTraversalInorder.inorderTraversal(null));
    }

    public void testSingleNodeNoExtraSpaces() {
        Node<Integer> root = new Node<>(1);
        String out = TreeTraversalInorder.inorderTraversal(root);
        assertEquals("1", out);
        assertFalse("Should not start with space", out.startsWith(" "));
        assertFalse("Should not end with space", out.endsWith(" "));
    }

    public void testSimpleBalancedTree() {
        //    1
        //   / \
        //  2   3
        Node<Integer> root = new Node<>(1, new Node<>(2), new Node<>(3));
        String out = TreeTraversalInorder.inorderTraversal(root);
        assertEquals("2 1 3", out);
        assertFalse("Should not contain double spaces", out.contains("  "));
    }

    public void testLeftSkewedTree() {
        // left skewed: 1 -> left 2 -> left 3  -> inorder: 3 2 1
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n2 = new Node<>(2, n3, null);
        Node<Integer> root = new Node<>(1, n2, null);
        String out = TreeTraversalInorder.inorderTraversal(root);
        assertEquals("3 2 1", out);
        assertFalse(out.startsWith(" "));
        assertFalse(out.endsWith(" "));
    }

    public void testRightSkewedTree() {
        // right skewed: 1 -> right 2 -> right 3  -> inorder: 1 2 3
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n2 = new Node<>(2, null, n3);
        Node<Integer> root = new Node<>(1, null, n2);
        String out = TreeTraversalInorder.inorderTraversal(root);
        assertEquals("1 2 3", out);
        assertFalse(out.contains("  "));
    }

    public void testTreeWithDuplicateValues() {
        //   2
        //  / \
        // 2   2  -> inorder: 2 2 2
        Node<Integer> root = new Node<>(2, new Node<>(2), new Node<>(2));
        String out = TreeTraversalInorder.inorderTraversal(root);
        assertEquals("2 2 2", out);
    }

    public void testDeepTreeProducesNormalizedSpacing() {
        // Build a moderate-sized tree and ensure we get normalized single-space separation
        Node<Integer> n7 = new Node<>(7);
        Node<Integer> n6 = new Node<>(6);
        Node<Integer> n5 = new Node<>(5, n6, n7);
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n3 = new Node<>(3, n4, n5);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> root = new Node<>(1, n2, n3);

        String out = TreeTraversalInorder.inorderTraversal(root);
        // expected inorder: 2 1 4 3 6 5 7
        assertEquals("2 1 4 3 6 5 7", out);
        // also ensure no leading/trailing spaces
        assertFalse(out.startsWith(" "));
        assertFalse(out.endsWith(" "));
        // ensure single space separation
        assertFalse(out.contains("  "));
    }
}

