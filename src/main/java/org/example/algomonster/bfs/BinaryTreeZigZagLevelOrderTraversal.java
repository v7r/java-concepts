package org.example.algomonster.bfs;

import org.example.algomonster.dfs.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a binary tree, return its level order traversal but in alternating left-to-right order.
 *
 * <img src="imgs/BinaryTreeZigZagLevelOrderTraversal.png" />
 */
public class BinaryTreeZigZagLevelOrderTraversal {
    public static List<List<Integer>> zigZagTraversal(Node<Integer> root) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean flipOrder = false;
        ArrayDeque<Node<Integer>> deque = new ArrayDeque<>();
        List<Integer> levelList = new ArrayList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int n = deque.size();
            for (int i = 0; i < n; i++) {
                Node<Integer> node = deque.pop();
                levelList.add(node.val);
                if (node.left != null) deque.add(node.left);
                if (node.right != null) deque.add(node.right);
            }
            if (flipOrder) {
                Collections.sort(levelList, Collections.reverseOrder());
            }
            ans.add(levelList);
            levelList = new ArrayList<>();
            flipOrder = !flipOrder;
        }
        return ans;
    }
}
