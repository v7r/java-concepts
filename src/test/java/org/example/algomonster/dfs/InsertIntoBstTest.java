package org.example.algomonster.dfs;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests for InsertIntoBst.insertBst
 * These tests follow the problem statement: insert value into a valid BST as a leaf.
 * They challenge common mistakes: inserting duplicates, maintaining BST property,
 * handling null root, and boundary values.
 */
public class InsertIntoBstTest extends TestCase {

    public void testInsertIntoEmptyTree() {
        Node<Integer> root = null;
        Node<Integer> res = InsertIntoBst.insertBst(root, 7);
        assertNotNull(res);
        assertEquals(Integer.valueOf(7), res.val);
        assertNull(res.left);
        assertNull(res.right);
    }

    public void testInsertLeftOfRoot() {
        Node<Integer> root = new Node<>(10);
        Node<Integer> returned = InsertIntoBst.insertBst(root, 5);
        // should return same root instance
        assertSame(root, returned);
        assertNotNull(root.left);
        assertEquals(Integer.valueOf(5), root.left.val);
        // inorder should be [5,10]
        List<Integer> inorder = inorderList(root);
        assertEquals(2, inorder.size());
        assertEquals(Integer.valueOf(5), inorder.get(0));
        assertEquals(Integer.valueOf(10), inorder.get(1));
    }

    public void testInsertRightOfRoot() {
        Node<Integer> root = new Node<>(10);
        Node<Integer> returned = InsertIntoBst.insertBst(root, 15);
        assertSame(root, returned);
        assertNotNull(root.right);
        assertEquals(Integer.valueOf(15), root.right.val);
        List<Integer> inorder = inorderList(root);
        assertEquals(2, inorder.size());
        assertEquals(Integer.valueOf(10), inorder.get(0));
        assertEquals(Integer.valueOf(15), inorder.get(1));
    }

    public void testInsertExistingValueNoChange() {
        // build tree 10,5,15
        Node<Integer> root = new Node<>(10, new Node<>(5), new Node<>(15));
        List<Integer> before = inorderList(root);
        Node<Integer> returned = InsertIntoBst.insertBst(root, 15);
        assertSame(root, returned);
        List<Integer> after = inorderList(root);
        // inorder should be unchanged and no duplicate 15 added
        assertEquals(before, after);
    }

    public void testInsertMultipleValuesMaintainsBst() {
        Node<Integer> root = new Node<>(20);
        int[] toInsert = {10, 30, 5, 15, 25, 35};
        for (int v : toInsert) {
            root = InsertIntoBst.insertBst(root, v);
        }
        List<Integer> inorder = inorderList(root);
        // inorder should be sorted ascending and contain exactly the values
        List<Integer> expected = new ArrayList<>();
        expected.add(5);
        expected.add(10);
        expected.add(15);
        expected.add(20);
        expected.add(25);
        expected.add(30);
        expected.add(35);
        assertEquals(expected, inorder);
    }

    public void testInsertMinMaxValues() {
        Node<Integer> root = new Node<>(0);
        InsertIntoBst.insertBst(root, Integer.MIN_VALUE);
        InsertIntoBst.insertBst(root, Integer.MAX_VALUE);
        List<Integer> inorder = inorderList(root);
        assertEquals(Integer.valueOf(Integer.MIN_VALUE), inorder.get(0));
        assertEquals(Integer.valueOf(0), inorder.get(1));
        assertEquals(Integer.valueOf(Integer.MAX_VALUE), inorder.get(2));
    }

    // Helper to collect inorder traversal
    private List<Integer> inorderList(Node<Integer> root) {
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

