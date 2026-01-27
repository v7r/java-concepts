package org.example.algomonster.dfs;

/**
 * Given a tree return the inorder traversal values of nodes as a string.
 *
 * Example:
 *  input:  1
 *         / \
 *       2    3
 *  output: 2 1 3
 */
public class TreeTraversalInorder {

    public static String inorderTraversal(Node<Integer> tree) {
        if (tree == null) return "";
        return (inorderTraversal(tree.left) + " " + tree.val + " " + inorderTraversal(tree.right)).trim();
    }
}
