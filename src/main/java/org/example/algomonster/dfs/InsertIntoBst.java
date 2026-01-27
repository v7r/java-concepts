package org.example.algomonster.dfs;

/**
 * Given the root node of a valid BST and a value to insert into the tree, return a new root node representing the valid
 * BST with the addition of the new item. If the new item already exists in the binary search tree, do not insert
 * anything.
 *
 * You must expand on the original BST by adding a leaf node. Do not change the structure of the original BST.
 *
 * Input
 * bst: a binary tree representing the existing BST.
 * val: an integer representing the value to be inserted.
 * Output
 * A valid BST with the inserted number, or the same BST if the number already exists.
 *
 * Examples
 * Example 1:
 * Input:
 *
 * tree = <See explanation>
 * val = 7
 * Output: <See explanation>
 *
 * Explanation:
 *
 * Before insertion:
 * <img src="imgs/InsertIntoBstBefore.png" />
 *
 * After insert:
 * <img src="imgs/InsertIntoBstAfter.png" />
 */
public class InsertIntoBst {
    public static Node<Integer> insertBst(Node<Integer> bst, int val) {
        if (bst == null) return new Node<>(val);
        int compareVal = bst.val.compareTo(val);
        if (compareVal < 0) {
            bst.right = insertBst(bst.right,val);
        } else if (compareVal > 0) {
            bst.left = insertBst(bst.left,val);
        }
        return bst;
    }
}
