package org.example.algomonster.bfs;

import org.example.algomonster.dfs.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return its level order traversal. The input is the root node of the tree. The output should be
 * a list of lists of integers, with the ith list containing the values of nodes on level i, from left to right.
 *
 * For example:
 *  <img src="imgs/BinaryTreeLevelOrderTraversal.png" />
 */
public class BinaryTreeLevelOrderTraversal {
    public static List<List<Integer>> levelOrderTraversal(Node<Integer> root) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();
        ArrayDeque<Node<Integer>> deque = new ArrayDeque<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int n = deque.size();
            for (int i = 0; i < n; i++) {
                Node<Integer> node = deque.pop();
                levelList.add(node.val);
                if (node.left != null) deque.add(node.left);
                if (node.right != null) deque.add(node.right);
            }
            ans.add(levelList);
            levelList = new ArrayList<>();
        }
        return ans;
    }
}
