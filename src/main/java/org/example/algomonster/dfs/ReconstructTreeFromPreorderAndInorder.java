package org.example.algomonster.dfs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reconstruct Binary Tree from Preorder and Inorder Traversal
 *
 * Given two arrays representing the preorder and inorder traversals of the same binary tree with unique values, reconstruct the original tree.
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7].
 *
 * Output: The binary tree structure:
 *
 *  <img src="imgs/ReconstructTreeFromPreorderAndInorder.png" />
 */
public class ReconstructTreeFromPreorderAndInorder {

    private static Node<Integer> buildTree(List<Integer> preorder, Map<Integer,Integer> inorderIdxMap, int preorderStart, int inorderStart, int size) {
        if (size <= 0) return null;
        Integer rootValue = preorder.get(preorderStart);
        Integer rootIndex = inorderIdxMap.get(rootValue);

        int leftPreorderStart = preorderStart + 1;
        int leftInorderStart = inorderStart;
        int leftSize = rootIndex - inorderStart;

        int rightPreorderStart = preorderStart + leftSize + 1;
        int rightInorderStart = rootIndex + 1;
        int rightSize = size - leftSize - 1;

        Node<Integer> leftNode = buildTree(preorder, inorderIdxMap, leftPreorderStart, leftInorderStart, leftSize);
        Node<Integer> rightNode = buildTree(preorder, inorderIdxMap, rightPreorderStart, rightInorderStart, rightSize);

        return new Node<Integer>(rootValue, leftNode, rightNode);
    }

    public static Node<Integer> constructBinaryTree(List<Integer> preorder, List<Integer> inorder) {
        Map<Integer,Integer> inorderIdxMap = new HashMap<>();
        for (int i = 0;i<inorder.size();i++) {
            inorderIdxMap.put(inorder.get(i),i);
        }
        return buildTree(preorder, inorderIdxMap, 0, 0, preorder.size());
    }
}
