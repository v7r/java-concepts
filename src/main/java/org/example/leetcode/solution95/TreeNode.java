package org.example.leetcode.solution95;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

        /*private List<Integer> asArray(List<Integer> array) {
            List<Integer> a = array;
            a.add(this.val);
            if (this.left == null) a.add(null);
            if (this.right == null) a.add(null);
            if (this.left != null) {
                a.add(this.left.val);
            }
            if (this.right != null) {
                a.add(this.right.val);
            }
            this.left.asArray(a);
            return a;
        }*/
}
