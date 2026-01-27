package org.example.algomonster.dfs;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor is defined between two nodes p and q as
 * the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Input
 * bst: a binary tree representing the existing BST.
 * p: the value of node p as described in the question
 * q: the value of node q as described in the question
 * Output
 * The value of the LCA between nodes p and q
 *
 * Examples
 * Example 1:
 * Input:
 *
 * bst = [6,2,8,0,4,7,9,x,x,3,5]
 * p = 2
 * q = 8
 * Output: 6
 *
 * Explanation:
 *  <img src="imgs/LowestCommonAncestorOfBstExample.png" />
 *
 * The ancestors of node p with value 2 are node 2 and node 6. The ancestors of node q with value 8 are node 8 and
 * node 6.
 *
 * The lowest common ancestors between these two nodes is 6.
 */
public class LowestCommonAncestorOfBst {

    public static int lcaOnBst(Node<Integer> bst, int p, int q) {
        if (p < bst.val && q < bst.val) {
            // both the values are on the left subtree
            return lcaOnBst(bst.left,p,q);
        } else if (p > bst.val && q > bst.val) {
            // both the values are on the right subtree
            return lcaOnBst(bst.right,p,q);
        } else {
            // Then either of these two satisfy; in such case the current node is the least common ancestor.
            //  a) p on left and q on right
            //  or
            //  b) p == bst.val or q == bst.val
            return bst.val;
        }
    }
}

/**
 * Solution:
 *
 * Trees are naturally recursive data structures and as such we will use recursion to find our answer. For Binary Search
 * Trees specifically, we often break down each recursive call into cases. The reason we do this is because for every
 * node we can decide whether to traverse down the left subtree or right subtree based on the value of the current node.
 *
 * To find the Lowest Common Ancestor of two nodes in a BST, we break our search into 3 common cases:
 *
 * 1. If nodes p and q are on the left of the current node, then continue search the left side
 * 2. If nodes p and q are on the right of the current node, then continue search the right side
 * 3. If nodes p and q are split (one is on the left, the other is on the right), then we can return the current node as
 * the LCA.
 *
 * Note that there is a special case when either p == cur.val or q == cur.val, since we have defined that a node can be
 * its own descedent, this falls in case 3.
 */
