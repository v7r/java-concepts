package org.example.algomonster.backtracking;

import junit.framework.TestCase;
import org.example.algomonster.backtracking.A_combinatorialsearch.TernaryTreePaths;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Tests for TernaryTreePaths.ternaryTreePaths
 * These tests cover null root, single node, varying numbers of children (1..3),
 * deep trees, and children lists that include null entries to surface edge behaviors.
 * Do not modify production logic.
 */
public class TernaryTreePathsTest extends TestCase {

    public void testNullRootReturnsEmptyList() {
        List<String> paths = TernaryTreePaths.ternaryTreePaths(null);
        assertNotNull(paths);
        assertTrue(paths.isEmpty());
    }

    public void testSingleNodeReturnsSinglePath() {
        Node<Integer> root = new Node<>(1);
        List<String> paths = TernaryTreePaths.ternaryTreePaths(root);
        assertEquals(1, paths.size());
        assertEquals("1", paths.get(0));
    }

    public void testNodeWithOneChild() {
        Node<Integer> child = new Node<>(2);
        List<Node<Integer>> children = new ArrayList<>();
        children.add(child);
        Node<Integer> root = new Node<>(1, children);

        List<String> paths = TernaryTreePaths.ternaryTreePaths(root);
        assertEquals(1, paths.size());
        assertEquals("1->2", paths.get(0));
    }

    public void testNodeWithThreeChildrenProducesThreePathsInOrder() {
        Node<Integer> c1 = new Node<>(2);
        Node<Integer> c2 = new Node<>(3);
        Node<Integer> c3 = new Node<>(4);
        List<Node<Integer>> children = Arrays.asList(c1, c2, c3);
        Node<Integer> root = new Node<>(1, children);

        List<String> paths = TernaryTreePaths.ternaryTreePaths(root);
        assertEquals(3, paths.size());
        assertEquals("1->2", paths.get(0));
        assertEquals("1->3", paths.get(1));
        assertEquals("1->4", paths.get(2));
    }

    public void testDeepTreeMultipleLevels() {
        // Build a tree:
        //        1
        //      / | \
        //     2  3  4
        //       / \
        //      5   6
        Node<Integer> n5 = new Node<>(5);
        Node<Integer> n6 = new Node<>(6);
        List<Node<Integer>> c3 = Arrays.asList(n5, n6);
        Node<Integer> n3 = new Node<>(3, c3);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> root = new Node<>(1, Arrays.asList(n2, n3, n4));

        List<String> paths = TernaryTreePaths.ternaryTreePaths(root);
        // expected paths in order: 1->2, 1->3->5, 1->3->6, 1->4
        List<String> expected = Arrays.asList("1->2", "1->3->5", "1->3->6", "1->4");
        assertEquals(expected, paths);
    }

    public void testChildrenListContainingNullsIsHandledGracefully() {
        // children list contains a null entry; flp should ignore null children without throwing
        Node<Integer> good = new Node<>(2);
        List<Node<Integer>> children = new ArrayList<>();
        children.add(good);
        children.add(null);
        Node<Integer> root = new Node<>(1, children);

        List<String> paths = TernaryTreePaths.ternaryTreePaths(root);
        // Should produce only the path to the valid child
        assertEquals(1, paths.size());
        assertEquals("1->2", paths.get(0));
    }

    public void testMultipleLeavesAtDifferentDepths() {
        // Build tree with leaves at different depths:
        //      1
        //     /
        //    2   (leaf)
        //   / \
        //  3   4
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n2 = new Node<>(2, Arrays.asList(n3, n4));
        Node<Integer> n5 = new Node<>(5); // leaf at same level as child of root
        Node<Integer> root = new Node<>(1, Arrays.asList(n2, n5));

        List<String> paths = TernaryTreePaths.ternaryTreePaths(root);
        // Possible ordering: iterate children list order => n2 then n5; for n2 children => n3 then n4
        List<String> expected = Arrays.asList("1->2->3", "1->2->4", "1->5");
        assertEquals(expected, paths);
    }
}

