package org.example.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution91QuickTime {
    private List<TreeNode>[][] memo;

    public List<TreeNode> generateTrees(int n) {
        memo = (List<TreeNode>[][])new List[n+1][n+1];
        return createTree(1, n);
    }

    private List<TreeNode> createTree(int start, int end) {
        List<TreeNode> res = new ArrayList<>();

        if(start > end) {
            // base case
            res.add(null);
            return res;
        }

        if(memo[start][end] != null) {
            return memo[start][end];
        }

        for(int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = createTree(start, i - 1);
            List<TreeNode> rightTrees = createTree(i + 1, end);

            // left/right subtrees combinations
            for(int li = 0; li < leftTrees.size(); li++) {
                for(int ri = 0; ri < rightTrees.size(); ri++) {
                    TreeNode tree = new TreeNode(i, leftTrees.get(li), rightTrees.get(ri));
                    res.add(tree);
                }
            }
        }

        memo[start][end] = res;
        return res;
    }
}
