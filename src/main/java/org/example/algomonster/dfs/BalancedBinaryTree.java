package org.example.algomonster.dfs;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A binary tree is considered balanced if, for every node in the tree, the difference in the height (or depth) of its
 * left and right subtrees is at most 1.
 *
 * In other words, the depth of the two subtrees for every node in the tree differs by no more than one.
 *
 * The height (or depth) of a tree is defined as the number of edges on the longest path from the root node to any leaf
 * node.
 *
 * Note: An empty tree is considered balanced by definition.
 *
 * In that case, given a binary tree, determine if it is balanced.
 *
 * Parameter
 * tree: A binary tree.
 * Result
 * A boolean representing whether the tree given is balanced.
 * Examples
 * Example 1
 * Input:
 *    <img src="imgs/BalancedBinaryTreeInput1.png" />
 *
 *
 * Output: true
 *
 * Explanation: By definition, this is a balanced binary tree.
 *
 * Example 2
 * Input:
 *   <img src="imgs/BalancedBinaryTreeInput2.png" />
 *
 *
 * Output: false
 *
 * Explanation: The subtrees of the node labelled 3 has a height difference of 2 (right subtree has height 2 and left subtree is empty with height 0), so it is not balanced.
 */
public class BalancedBinaryTree {
    private static int dfs(Node<Integer> tree, AtomicInteger count) {
        if (tree == null) return 0;
        int ldepth = dfs(tree.left, count);
        int rdepth = dfs(tree.right, count);
        if (Math.abs(ldepth-rdepth) > 1) count.incrementAndGet();
        return Math.max(ldepth,rdepth) + 1;
    }


    public static boolean isBalanced(Node<Integer> tree) {
        AtomicInteger count = new AtomicInteger(0);
        dfs(tree,count);
        return count.get() == 0;
    }
}
