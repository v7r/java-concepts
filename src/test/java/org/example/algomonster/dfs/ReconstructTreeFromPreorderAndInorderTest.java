package org.example.algomonster.dfs;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Tests for ReconstructTreeFromPreorderAndInorder.constructBinaryTree
 * These tests follow the standard problem statement and challenge the current logic with
 * typical inputs and edge cases. Some tests expect a correctly reconstructed tree (and will
 * fail if the implementation is broken), and one test documents current failure modes by
 * asserting that an exception is thrown for mismatched input sizes.
 */
public class ReconstructTreeFromPreorderAndInorderTest extends TestCase {

    public void testEmptyListsReturnsNull() {
        List<Integer> preorder = Arrays.asList();
        List<Integer> inorder = Arrays.asList();
        Node<Integer> root = ReconstructTreeFromPreorderAndInorder.constructBinaryTree(preorder, inorder);
        assertNull(root);
    }

    public void testSingleNode() {
        List<Integer> preorder = Arrays.asList(1);
        List<Integer> inorder = Arrays.asList(1);
        Node<Integer> root = ReconstructTreeFromPreorderAndInorder.constructBinaryTree(preorder, inorder);
        assertNotNull(root);
        assertEquals(Integer.valueOf(1), root.val);
        assertNull(root.left);
        assertNull(root.right);
    }

    public void testExampleTreeFromProblemStatement() {
        List<Integer> preorder = Arrays.asList(3, 9, 20, 15, 7);
        List<Integer> inorder = Arrays.asList(9, 3, 15, 20, 7);

        Node<Integer> root = ReconstructTreeFromPreorderAndInorder.constructBinaryTree(preorder, inorder);
        // If implementation is correct, traversals of the constructed tree should match inputs
        assertNotNull(root);
        assertEquals(preorder, toPreorderList(root));
        assertEquals(inorder, toInorderList(root));
    }

    public void testExampleTreeFromAlgoMonsterTestCases() {
        List<Integer> preorder = Arrays.asList(1,2,4,8,9,5,10,11,3,6,12,13,7,14);
        List<Integer> inorder = Arrays.asList(8,4,9,2,5,10,11,1,6,12,13,3,7,14);

        Node<Integer> root = ReconstructTreeFromPreorderAndInorder.constructBinaryTree(preorder, inorder);
        // If implementation is correct, traversals of the constructed tree should match inputs
        assertNotNull(root);
        assertEquals(preorder, toPreorderList(root));
        assertEquals(inorder, toInorderList(root));
    }

    public void testLeftSkewedTree() {
        // left skewed tree:   1
        //                   /
        //                  2
        //                 /
        //                3
        List<Integer> preorder = Arrays.asList(1,2,3);
        List<Integer> inorder = Arrays.asList(3,2,1);
        Node<Integer> root = ReconstructTreeFromPreorderAndInorder.constructBinaryTree(preorder, inorder);
        assertNotNull(root);
        assertEquals(preorder, toPreorderList(root));
        assertEquals(inorder, toInorderList(root));
    }

    public void testRightSkewedTree() {
        // right skewed tree: 1
        //                      \
        //                       2
        //                        \
        //                         3
        List<Integer> preorder = Arrays.asList(1,2,3);
        List<Integer> inorder = Arrays.asList(1,2,3);
        Node<Integer> root = ReconstructTreeFromPreorderAndInorder.constructBinaryTree(preorder, inorder);
        assertNotNull(root);
        assertEquals(preorder, toPreorderList(root));
        assertEquals(inorder, toInorderList(root));
    }

    public void testMismatchedSizesThrowsException() {
        List<Integer> preorder = Arrays.asList(1,2);
        List<Integer> inorder = Arrays.asList(1);
        try {
            ReconstructTreeFromPreorderAndInorder.constructBinaryTree(preorder, inorder);
            fail("Expected an exception (e.g., NullPointerException or IndexOutOfBounds) due to mismatched input sizes");
        } catch (Exception ex) {
            // Expected: current implementation may throw due to missing inorder index map population
            assertTrue(ex instanceof NullPointerException || ex instanceof IndexOutOfBoundsException || ex instanceof IllegalArgumentException);
        }
    }

    // Helpers to collect traversals from constructed tree
    private List<Integer> toPreorderList(Node<Integer> root) {
        List<Integer> out = new ArrayList<>();
        preorderRec(root, out);
        return out;
    }

    private void preorderRec(Node<Integer> node, List<Integer> out) {
        if (node == null) return;
        out.add(node.val);
        preorderRec(node.left, out);
        preorderRec(node.right, out);
    }

    private List<Integer> toInorderList(Node<Integer> root) {
        List<Integer> out = new ArrayList<>();
        inorderRec(root, out);
        return out;
    }

    private void inorderRec(Node<Integer> node, List<Integer> out) {
        if (node == null) return;
        inorderRec(node.left, out);
        out.add(node.val);
        inorderRec(node.right, out);
    }
}

