package org.example.algomonster.dfs;

import junit.framework.TestCase;

/**
 * Tests for SerializeAndDeserializeBinaryTree.serializeTree and deserializeTree.
 * These tests exercise round-trip behavior and edge-cases (null tree, negative and boundary values,
 * invalid input string, and moderate-depth trees) to challenge the implementation.
 *
 * Do not modify production logic.
 */
public class PostOrderSerializeAndDeserializeBinaryTreeTest extends TestCase {

    public void testSerializeNullReturnsXAndDeserializeToNull() {
        String s = PostOrderSerializeAndDeserializeBinaryTree.serializeTree(null);
        assertEquals("x", s);
        Node<Integer> n = PostOrderSerializeAndDeserializeBinaryTree.deserializeTree(s);
        assertNull(n);
    }

    public void testSimpleTreeSerializeFormatAndRoundTrip() {
        // tree:   1
        //       /   \
        //      2     3
        Node<Integer> root = new Node<>(1, new Node<>(2), new Node<>(3));
        String expected = "x x 2 x x 3 1";
        String ser = PostOrderSerializeAndDeserializeBinaryTree.serializeTree(root);
        assertEquals(expected, ser);

        Node<Integer> des = PostOrderSerializeAndDeserializeBinaryTree.deserializeTree(ser);
        String ser2 = PostOrderSerializeAndDeserializeBinaryTree.serializeTree(des);
        assertEquals(expected, ser2);
    }

    public void testComplexTreeRoundTrip() {
        // build a more complex tree
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n5 = new Node<>(5, new Node<>(8), null);
        Node<Integer> n2 = new Node<>(2, n4, n5);
        Node<Integer> n3 = new Node<>(3, null, new Node<>(7));
        Node<Integer> root = new Node<>(1, n2, n3);

        String s1 = PostOrderSerializeAndDeserializeBinaryTree.serializeTree(root);
        Node<Integer> d1 = PostOrderSerializeAndDeserializeBinaryTree.deserializeTree(s1);
        String s2 = PostOrderSerializeAndDeserializeBinaryTree.serializeTree(d1);
        assertEquals(s1, s2);
    }

    public void testNegativeAndBoundaryValuesPreserved() {
        Node<Integer> nMin = new Node<>(Integer.MIN_VALUE);
        Node<Integer> nMax = new Node<>(Integer.MAX_VALUE);
        Node<Integer> root = new Node<>(0, nMin, nMax);

        String s = PostOrderSerializeAndDeserializeBinaryTree.serializeTree(root);
        Node<Integer> d = PostOrderSerializeAndDeserializeBinaryTree.deserializeTree(s);
        String s2 = PostOrderSerializeAndDeserializeBinaryTree.serializeTree(d);
        assertEquals(s, s2);
    }

    public void testDeserializeEmptyStringThrows() {
        try {
            PostOrderSerializeAndDeserializeBinaryTree.deserializeTree("");
            fail("Expected NumberFormatException or NoSuchElementException for empty input");
        } catch (Exception ex) {
            // Implementation uses split and Integer.parseInt which will throw NumberFormatException
            // or Iterator.next() may throw NoSuchElementException. Accept either.
            assertTrue(ex instanceof NumberFormatException || ex instanceof java.util.NoSuchElementException);
        }
    }

    public void testModerateDepthLeftSkewedRoundTrip() {
        // build a left-skewed tree of depth 20
        Node<Integer> cur = new Node<>(20);
        for (int i = 19; i >= 0; i--) {
            cur = new Node<>(i, cur, null);
        }
        String s = PostOrderSerializeAndDeserializeBinaryTree.serializeTree(cur);
        Node<Integer> d = PostOrderSerializeAndDeserializeBinaryTree.deserializeTree(s);
        String s2 = PostOrderSerializeAndDeserializeBinaryTree.serializeTree(d);
        assertEquals(s, s2);
    }

    public void testRoundTripIdempotenceMultipleTimes() {
        Node<Integer> root = new Node<>(10, new Node<>(5, new Node<>(3), null), new Node<>(15, null, new Node<>(20)));
        String s = PostOrderSerializeAndDeserializeBinaryTree.serializeTree(root);
        Node<Integer> a = PostOrderSerializeAndDeserializeBinaryTree.deserializeTree(s);
        String s2 = PostOrderSerializeAndDeserializeBinaryTree.serializeTree(a);
        Node<Integer> b = PostOrderSerializeAndDeserializeBinaryTree.deserializeTree(s2);
        String s3 = PostOrderSerializeAndDeserializeBinaryTree.serializeTree(b);
        assertEquals(s, s2);
        assertEquals(s2, s3);
    }
}

