package org.example.leetcode.solution95;

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

/***
 * This is my solution to the LeetCode problem
 * https://leetcode.com/problems/unique-binary-search-trees-ii/
 */
public class Solution95 {

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> trees = generateUniqueBetween(1, n, 1);
        return trees;
    }

    private List<TreeNode> generateUniqueBetween(int i, int n, int start) {
        List<TreeNode> trees = new ArrayList<>();

        int iRoot = start;
        while(iRoot <= n) {
            List<TreeNode> sTrees = new ArrayList<>();
            int root = start;
            TreeNode rootNode = new TreeNode(root);

            if (i == root && root == n) {
                sTrees.add(rootNode);
                trees.addAll(sTrees);
                break;
            }

            List<TreeNode> leftTrees = new ArrayList<>();
            final List<TreeNode> rightTrees = new ArrayList<>();
            if (i < root) {
                leftTrees = generateUniqueBetween(i, root-1, root-1);
            }
            if (root < n) {
                rightTrees.addAll(generateUniqueBetween(root+1, n, root+1));
            }
            if (!leftTrees.isEmpty()) {
                sTrees = leftTrees
                        .stream()
                        .flatMap(l -> {
                            List<TreeNode> combiTree = new ArrayList<TreeNode>();
                            TreeNode leftPaddedTree = new TreeNode(rootNode.val, l, null);
                            if (!rightTrees.isEmpty()) {
                                List<TreeNode> rightPaddedTrees = rightTrees.stream().map(r -> new TreeNode(
                                        leftPaddedTree.val, leftPaddedTree.left, r)
                                ).toList();
                                combiTree.addAll(rightPaddedTrees);
                            } else {
                                combiTree.add(leftPaddedTree);
                            }
                            return combiTree.stream();
                        })
                        .toList();
            } else {
                sTrees = rightTrees
                        .stream()
                        .flatMap(r -> {
                            List<TreeNode> combiTree = new ArrayList<TreeNode>();
                            TreeNode rightPaddedTree = new TreeNode(rootNode.val, null, r);
                            combiTree.add(rightPaddedTree);
                            return combiTree.stream();
                        })
                        .toList();
            }
            trees.addAll(sTrees);
            iRoot++;
        } // end While
        return trees;
    }

    public static void main(String[] args) {
        Solution95 s = new Solution95();
        List<TreeNode> treeNodes = s.generateTrees(3);
        System.out.println("Tree nodes"+treeNodes);
    }
}
