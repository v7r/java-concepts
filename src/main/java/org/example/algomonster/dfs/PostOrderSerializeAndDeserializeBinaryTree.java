package org.example.algomonster.dfs;

import java.util.*;

/**
 * Given a binary tree, write a serialize function that converts the tree into a string and a deserialize function that
 * converts a string back to a binary tree. You may serialize the tree into any string representation you want, as long
 * as it can be deserialized properly.
 */
public class PostOrderSerializeAndDeserializeBinaryTree {

    private static void postorderSerialize(Node<Integer> root, StringJoiner s) {
        if (root == null) {
            s.add("x");
        } else {
            postorderSerialize(root.left,s);
            postorderSerialize(root.right,s);
            s.add(String.valueOf(root.val));
        }
    }

    //    1
    //   / \
    //  2   3
    // postOrder: x x 2 x x 3 1
    // iterate reverse (start at end element) order to construct the tree.
    private static Node<Integer> postorderDeserialize(Iterator<String> i) {
        String sVal = i.next();
        if (sVal.equals("x")) return null;
        Node<Integer> right = postorderDeserialize(i);
        Node<Integer> left = postorderDeserialize(i);
        return new Node<Integer>(Integer.parseInt(sVal), left, right);
    }

    public static String serializeTree(Node<Integer> root) {
        StringJoiner s = new StringJoiner(" ");
        postorderSerialize(root,s);
        return s.toString();
    }

    public static Node<Integer> deserializeTree(String s) {
        List<String> elements = Arrays.asList(s.split(" "));
        Collections.reverse(elements);
        return postorderDeserialize(elements.iterator());
    }
}
