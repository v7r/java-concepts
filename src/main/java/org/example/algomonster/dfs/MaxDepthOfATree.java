package org.example.algomonster.dfs;

/**
 * Max depth of a binary tree is the longest root-to-leaf path. Given a binary tree, find its max depth. Here, we define
 * the length of the path to be the number of edges on that path, not the number of nodes.
 */
public class MaxDepthOfATree {


    public static int treeMaxDepth(Node<Integer> root) {
        // If the node is a leaf then the depth from that node is zero.
        if (root == null || (root.left == null && root.right == null)) return 0;

        // Since the node is not a leaf, it's depth is 1 + max depth of left or right
        return 1 + Math.max(treeMaxDepth(root.left), treeMaxDepth((root.right)));
    }
}