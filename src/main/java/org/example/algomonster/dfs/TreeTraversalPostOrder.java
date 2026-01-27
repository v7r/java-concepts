package org.example.algomonster.dfs;

/**
 * Given a tree return the post-order traversal values of nodes as a string.
 *
 * Example:
 *  input:  1
 *         / \
 *       2    3
 *  output: 2 3 1
 */
public class TreeTraversalPostOrder {
    public static String preOrderTraversal(Node<Integer> tree) {
        if (tree == null) return "";
        return ((preOrderTraversal(tree.left) + " " + preOrderTraversal(tree.right)).trim()+ " " + tree.val).trim();
    }
}
