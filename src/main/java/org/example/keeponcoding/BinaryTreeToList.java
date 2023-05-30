package org.example.keeponcoding;

import org.example.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given the root of a binary tree, return a list of lists where each list represents each layer of the tree.
 * A tree with depth D will have D lists.
 * Eg:
 *   Input Tree
 *                  10
 *               /     \
 *              5      15
 *            /  \    /   \
 *           1    7  12   20
 *   Output:
 *      [10]
 *      [5,15]
 *      [1,7,12,20]
 */
public class BinaryTreeToList {

    private List<List<Integer>> treeToList(TreeNode root) {
        return listAtLevel(Arrays.asList(root), new ArrayList<List<Integer>>());
    }

    private List<List<Integer>> listAtLevel(List<TreeNode> l, List<List<Integer>> m) {
        if (l.isEmpty()) return m;
        List<TreeNode> n = new ArrayList<>();
        List<Integer> o = new ArrayList<>();
        for (TreeNode t : l) {
            if (t.left != null) n.add(t.left);
            if (t.right != null) n.add(t.right);
            o.add(t.val);
        }
        m.add(o);
        return listAtLevel(n, m);
    }

    public static void main(String[] args) {
        Integer[] tl = new Integer[]{10,5,15,1,7,12,20};
        TreeNode root = TreeNode.asTree(tl);
        List<List<Integer>> levelList = new BinaryTreeToList().listAtLevel(
                Arrays.asList(root), new ArrayList<List<Integer>>()
        );
        System.out.println("LevelList "+levelList);
    }
}
