package org.example.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution95LowMemory {
    private List<TreeNode>[][] memo;

    public List<TreeNode> generateTrees(int n) {
        memo = (List<TreeNode>[][]) new List[n + 1][n + 1];
        helper(1, n);
        System.gc();
        return memo[1][n];
    }

    public List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }

        if (memo[start][end] != null)
            return memo[start][end];

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = helper(start, i - 1);
            List<TreeNode> right = helper(i + 1, end);

            for (TreeNode lChild : left) {
                for (TreeNode rChild : right) {
                    TreeNode temp = new TreeNode(i, lChild, rChild);
                    res.add(temp);
                }
            }
        }
        memo[start][end] = res;
        return res;
    }
}
