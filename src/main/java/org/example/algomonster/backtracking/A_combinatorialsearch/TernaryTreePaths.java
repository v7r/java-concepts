package org.example.algomonster.backtracking.A_combinatorialsearch;

import org.example.algomonster.backtracking.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Given a ternary tree (each node of the tree can have up to three children), find all the root-to-leaf paths.
 *
 * Input/output
 * <img src="../imgs/TernaryTreePaths.png"
 */
public class TernaryTreePaths {

    // Find leaf path
    private static void flp(Node<Integer> root, List<String> path, List<String> pathList) {
        if (root == null) return;
        path.add(String.valueOf(root.val));
        if (root.children == null || root.children.isEmpty()) {
            // Leaf node;
            pathList.add(String.join("->",path.toArray(new String[0])));
            path.remove(path.size()-1);
            return;
        }
        for (Node<Integer> n : root.children) {
            flp(n, path, pathList);
        }
        path.remove(path.size()-1);
    }

    // Find leaf path; memory inefficient as we end up creating multiple StringJoiner objects.
    private static void flpV1(Node<Integer> root, StringJoiner path, List<String> pathList) {
        if (root == null) return;
        path.add(String.valueOf(root.val));
        if (root.children == null || root.children.isEmpty()) {
            // Leaf node;
            pathList.add(path.toString());
            return;
        }
        for (Node<Integer> n : root.children) {
            flpV1(n, new StringJoiner("->").add(path.toString()), pathList);
        }
    }

    public static List<String> ternaryTreePaths(Node<Integer> root) {
        List<String> pathList = new ArrayList<>();
        flp(root, new ArrayList<>(),pathList);
        return pathList;
    }
}
