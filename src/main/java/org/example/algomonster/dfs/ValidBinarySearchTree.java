package org.example.algomonster.dfs;

/**
 * A binary search tree is a binary tree with the property that for any node, the value of this node is greater than
 * any node in its left subtree and less than any node's value in its right subtree. In other words, an inorder
 * traversal of a binary search tree yields a list of values that is monotonically increasing (strictly increasing).
 *
 * Given a binary tree, determine whether it is a binary search tree.
 *
 *  <img src="imgs/ValidBinarySearchTree.png" />
 *
 */
public class ValidBinarySearchTree {

    private static boolean bstCheck(Node<Integer> root, Integer min, Integer max) {
        if (root == null) return true;
        if (!(root.val > min && root.val < max)) return false;
        return bstCheck(root.left,min,root.val) && bstCheck(root.right, root.val, max);
    }

    public static boolean validBst(Node<Integer> root) {
        return bstCheck(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
