package org.example.algomonster.dfs;

import junit.framework.TestCase;

/**
 * Tests for InvertABinartyTree.invertBinaryTree
 * These tests challenge the inversion logic with several edge-cases and properties:
 * - null input
 * - single node
 * - simple left/right swap
 * - left-skewed tree becomes right-skewed (and double-invert restores original)
 * - method returns the same instance (in-place inversion)
 * - complex tree double-inversion property (invert twice == original)
 */
public class InvertABinaryTreeTest extends TestCase {

    public void testNullTree() {
        Node<Integer> root = null;
        Node<Integer> res = InvertABinartyTree.invertBinaryTree(root);
        assertNull(res);
    }

    public void testSingleNode() {
        Node<Integer> root = new Node<>(1);
        Node<Integer> out = InvertABinartyTree.invertBinaryTree(root);
        // same instance returned
        assertSame(root, out);
        assertNull(out.left);
        assertNull(out.right);
    }

    public void testSimpleSwap() {
        Node<Integer> left = new Node<>(2);
        Node<Integer> right = new Node<>(3);
        Node<Integer> root = new Node<>(1, left, right);

        Node<Integer> out = InvertABinartyTree.invertBinaryTree(root);
        // children should be swapped
        assertSame(root, out);
        assertNotNull(out.left);
        assertNotNull(out.right);
        assertEquals(Integer.valueOf(3), out.left.val);
        assertEquals(Integer.valueOf(2), out.right.val);
    }

    public void testLeftSkewedAndDoubleInvert() {
        // left skewed: 1 -> left 2 -> left 3
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n2 = new Node<>(2, n3, null);
        Node<Integer> root = new Node<>(1, n2, null);

        String before = serializePreorder(root);

        Node<Integer> inverted = InvertABinartyTree.invertBinaryTree(root);
        // after invert it should be right-skewed: root.right == 2
        assertNull(inverted.left);
        assertNotNull(inverted.right);
        assertEquals(Integer.valueOf(2), inverted.right.val);
        assertNotNull(inverted.right.right);
        assertEquals(Integer.valueOf(3), inverted.right.right.val);

        // double invert should restore original structure
        Node<Integer> restored = InvertABinartyTree.invertBinaryTree(inverted);
        String afterDouble = serializePreorder(restored);
        assertEquals(before, afterDouble);
    }

    public void testInvertReturnsSameInstance() {
        Node<Integer> left = new Node<>(2);
        Node<Integer> root = new Node<>(1, left, null);
        Node<Integer> out = InvertABinartyTree.invertBinaryTree(root);
        assertSame(root, out);
    }

    public void testComplexTreeDoubleInvertEquality() {
        // build a more complex tree
        //        1
        //      /
        //     2     3
        //    / \   / \
        //   4  5  6   7
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n5 = new Node<>(5);
        Node<Integer> n6 = new Node<>(6);
        Node<Integer> n7 = new Node<>(7);
        Node<Integer> n2 = new Node<>(2, n4, n5);
        Node<Integer> n3 = new Node<>(3, n6, n7);
        Node<Integer> root = new Node<>(1, n2, n3);

        String before = serializePreorder(root);
        InvertABinartyTree.invertBinaryTree(root);
        InvertABinartyTree.invertBinaryTree(root);
        String after = serializePreorder(root);
        // inverting twice should restore original structure and values
        assertEquals(before, after);
    }

    // Helper to serialize tree structure and values in preorder with null markers
    private String serializePreorder(Node<Integer> root) {
        StringBuilder sb = new StringBuilder();
        serializePreorderRec(root, sb);
        return sb.toString();
    }

    private void serializePreorderRec(Node<Integer> node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,");
            return;
        }
        sb.append(node.val).append(',');
        serializePreorderRec(node.left, sb);
        serializePreorderRec(node.right, sb);
    }
}

