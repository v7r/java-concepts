package org.example.algomonster.dfs;

import junit.framework.TestCase;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Tests for NumberOfVisibleNodes.visibleTreeNode and dfs.
 * These tests challenge edge cases: null root for dfs, equal values, negative values,
 * and more complex unbalanced trees to validate the "visible" counting logic.
 */
public class NumberOfVisibleNodesTest extends TestCase {

    public void testSingleNode() {
        Node<Integer> root = new Node<>(5);
        assertEquals(1, NumberOfVisibleNodes.visibleTreeNode(root));
    }

    public void testSimpleTree() {
        Node<Integer> root = new Node<>(5, new Node<>(3), new Node<>(8));
        // visible nodes: 5 (root), 8 (right) -> 2
        assertEquals(2, NumberOfVisibleNodes.visibleTreeNode(root));
    }

    public void testEqualValuesAllVisible() {
        // all nodes have same value as root; since logic uses >= they should all be visible
        Node<Integer> left = new Node<>(5, new Node<>(5), null);
        Node<Integer> right = new Node<>(5);
        Node<Integer> root = new Node<>(5, left, right);
        assertEquals(4, NumberOfVisibleNodes.visibleTreeNode(root));
    }

    public void testComplexUnbalancedTree() {
        // build a tree where both left and right branches have visible nodes
        // root = 2
        // left = 3 -> left.left = 1 (not visible), left.right = 4 (visible)
        // right = 2 -> right.right = 5 (visible)
        Node<Integer> leftLeft = new Node<>(1);
        Node<Integer> leftRight = new Node<>(4);
        Node<Integer> left = new Node<>(3, leftLeft, leftRight);

        Node<Integer> rightRight = new Node<>(5);
        Node<Integer> right = new Node<>(2, null, rightRight);

        Node<Integer> root = new Node<>(2, left, right);

        // Visible nodes: root(2), left(3), left.right(4), right(2), right.right(5) => 5
        assertEquals(5, NumberOfVisibleNodes.visibleTreeNode(root));
    }

    public void testNegativeValues() {
        // root -1, left -2 (not visible), right -3 (not visible), right.left -1 (visible because >= max on path)
        Node<Integer> rightLeft = new Node<>(-1);
        Node<Integer> right = new Node<>(-3, rightLeft, null);
        Node<Integer> left = new Node<>(-2);
        Node<Integer> root = new Node<>(-1, left, right);

        // visible nodes: root(-1), right.left(-1) => 2
        assertEquals(2, NumberOfVisibleNodes.visibleTreeNode(root));
    }

    public void testDfsWithNullRootDoesNotIncreaseCount() {
        AtomicInteger count = new AtomicInteger(0);
        // dfs should be safe to call with null root and simply return
        NumberOfVisibleNodes.dfs(null, Integer.MIN_VALUE, count);
        assertEquals(0, count.get());
    }

    public void testVisibleTreeNodeWithNullRootThrows() {
        try {
            NumberOfVisibleNodes.visibleTreeNode(null);
            fail("visibleTreeNode(null) should throw NullPointerException because it accesses root.val as initial max");
        } catch (NullPointerException npe) {
            // expected
        }
    }
}

