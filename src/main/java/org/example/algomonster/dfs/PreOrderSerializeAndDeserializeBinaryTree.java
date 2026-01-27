package org.example.algomonster.dfs;

import java.util.*;

/**
 * Given a binary tree, write a serialize function that converts the tree into a string and a deserialize function that
 * converts a string back to a binary tree. You may serialize the tree into any string representation you want, as long
 * as it can be deserialized properly.
 */
public class PreOrderSerializeAndDeserializeBinaryTree {

    // Tree:
    //       1
    //    /     \
    //   2       3
    //  / \     /
    // 6   4   5

    // Inorder
    // Serialise: x 6 x 2 x 4 x 1 x 5 x 3 x
    // DESERIALS: CANNOT UNIQUELY DESERIALIZED BACK INTO THE ORIGINAL TREE !!!

    // Preorder
    // Serialize: 1 2 6 x x 4 x x 3 5 x x x
    // Deserial : 1 left is 2 left is 6 with null left and right then back to node 2 with right 4 with null l and r
    //            then back to 1 with right as 3 with left as 5 with null l and r then back to 3 with null right
    // Note: You can deserialize back to original tree uniquely as long as the serialized string has null nodes.

    // Postorder
    // Serialize: x x 6 x x 4 2 x x 5 x 3 1
    // Deserial : left null right null for 6, left null right null for 4 then 2 as node for 6 and 4 then ...
    // Note: You can deserialize back to original tree uniquely as along as the serialized string has null nodes.

    private static void preorderSeralize(Node<Integer> root, StringJoiner s) {
        if (root == null) {
            s.add("x");
        } else {
            s.add(String.valueOf(root.val));
            preorderSeralize(root.left,s);
            preorderSeralize(root.right,s);
        }
    }

    private static Node<Integer> preorderDeserialize(Iterator<String> i) {
        String sVal = i.next();
        if (sVal.equals("x")) return null;
        return new Node<Integer>(Integer.parseInt(sVal),preorderDeserialize(i),preorderDeserialize(i));
    }

    public static String serializeTree(Node<Integer> root) {
        StringJoiner s = new StringJoiner(" ");
        preorderSeralize(root,s);

        return s.toString();
    }


    public static Node<Integer> deserializeTree(String s) {
        return preorderDeserialize(Arrays.asList(s.split(" ")).iterator());
    }
}
