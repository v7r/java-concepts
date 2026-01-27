package org.example.algomonster.dfs;

/**
 * Given a tree return the pre-order traversal values of nodes as a string.
 *
 * Example:
 *  input:  1
 *         / \
 *       2    3
 *  output: 1 2 3
 */
public class TreeTraversalPreOrder {
    public static String preOrderTraversal(Node<Integer> tree) {
        if (tree == null) return "";
        return (tree.val + " " + (preOrderTraversal(tree.left) + " " + preOrderTraversal(tree.right)).trim()).trim();
    }
}
