package org.example.algomonster.dfs;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * In a binary tree, a node is labeled as "visible" if, on the path from the root to that node, there isn't any node
 * with a value higher than this node's value.
 *
 * The root is always "visible" since there are no other nodes between the root and itself. Given a binary tree, count
 * the number of "visible" nodes.
 *
 * Input:
 *  <img src="imgs/NumberOfVisibleNodes.png" />
 *
 *
 * Output: 3
 *
 * For example: Node 4 is not visible since 5>4, similarly Node 3 is not visible since both 5>3 and 4>3. Node 8 is
 * visible since all 5<=8, 4<=8, and 8<=8.
 */
public class NumberOfVisibleNodes {
    public static void dfs(Node<Integer> root, Integer max, AtomicInteger count) {
        if (root == null) return;
        if (root.val >= max) count.incrementAndGet();
        dfs(root.left, Math.max(max, root.val), count);
        dfs(root.right, Math.max(max, root.val), count);
    }

    public static int visibleTreeNode(Node<Integer> root) {
        AtomicInteger count = new AtomicInteger(0);
        dfs(root,root.val,count);
        return count.get();
    }
}
