package org.example.algomonster.dfs;

/**
 * Given a binary tree, invert it and return the new value. You may invert it in-place.
 *
 * To "invert" a binary tree, switch the left subtree and the right subtree, and invert them both. Inverting an empty
 * tree does nothing.
 *
 * Input
 * tree: a binary tree that needs to be inverted.
 * Output
 * The inverted binary tree.
 *
 * Examples
 * Example 1:
 * Input:
 *
 * tree = <See explanation>
 * Output: <See explanation>
 *
 * Explanation:
 *
 * Original tree:
 * <img src="imgs/InvertABinaryTreeInput.png" />
 *
 * Inverted Tree:
 * <img src="imgs/InvertABinaryTreeOutput.png" />
 */
public class InvertABinartyTree {
    private static void invert(Node<Integer> tree) {
        if (tree == null) return;
        Node<Integer> t = tree.left;
        tree.left = tree.right;
        tree.right = t;
        invert(tree.left);
        invert(tree.right);
    }

    public static Node<Integer> invertBinaryTree(Node<Integer> tree) {
        invert(tree);
        return tree;
    }
}
