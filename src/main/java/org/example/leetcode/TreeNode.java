package org.example.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * Returns a Root Node of a tree for the given array of elements.
     * Eg:
     *   Input Graph:
     *              10
     *             /  \
     *           5     15
     *         / \    /  \
     *        1   7  12  20
     *
     *   Output: [10,5,15,1,7,12,20];
     */
    private List<Integer> toArray(List<TreeNode> level, List<Integer> array) {
        if (level.isEmpty() || level.stream().allMatch(e -> e == null)) return array;
        List<TreeNode> nextLevel = new ArrayList<>();
        level.forEach(node -> {
            if (node == null) {
                array.add(null);
                nextLevel.add(null);
                nextLevel.add(null);
            } else {
                array.add(node.val);
                nextLevel.add(node.left);
                nextLevel.add(node.right);
            }
        });
        return toArray(nextLevel, array);
    }

    /**
     *
     */
    private static void testToArray() {

    }

    /**
     * Returns a Root Node of a tree for the given array of elements.
     * Eg:
     *   [10,5,15,1,7,12,20];
     *   Graph:
     *              10
     *             /  \
     *           5     15
     *         / \    /  \
     *        1   7  12  20
     *
     *   [5,1,4,null,null,3,6];
     *   Graph:
     *            5
     *           / \
     *         1    4
     *             / \
     *            3   6
     *
     *   [5,1,4,null,null,3,6,null,null,null,null,null,7];
     *   Graph:
     *            5
     *           / \
     *         1    4
     *             / \
     *            3   6
     *             \
     *              7
     *   Notes:
     *   For a node at index i, it's left node will be at 2*i + 1 and it's right node will be at 2*i + 2.
     *   Also, we can find parent of a node at index i at index derived from (i-1)/2
     *
     *   Ref: https://www.prepbytes.com/blog/tree/array-representation-of-binary-tree
     *
     * @param elements
     * @return
     */
    public static TreeNode asTree(Integer[] elements) {
        if (elements == null || elements.length == 0) return null;
        if (elements.length == 1) return new TreeNode(elements[0]);
        int i = 0;
        TreeNode root = new TreeNode(elements[0]);
        List<TreeNode> n = new ArrayList<>(elements.length);
        n.add(root);
        TreeNode ni;
        while (i*2+2 < elements.length) {
            ni = n.get(i);
            if (elements[2*i+1] != null) n.add(new TreeNode(elements[2*i+1])); else n.add(null);
            if (elements[2*i+2] != null) n.add(new TreeNode(elements[2*i+2])); else n.add(null);
            if (ni == null) {
                i++; continue;
            }
            ni.left = n.get(2*i+1);
            ni.right = n.get(2*i+2);
            i++;
        }
        return root;
    }

    private static void testTreeFrom() {
        var s1 = new Integer[]{10,5,15,1,7,12,20};
        TreeNode root = TreeNode.asTree(s1);
        System.out.println("Tree "+root+", for array: "+s1);
        List<Integer> so = root.toArray(Arrays.asList(root), new ArrayList<>());
        System.out.println("Tree as Array: "+so);

        s1 = new Integer[]{5,1,4,null,null,3,6};
        root = TreeNode.asTree(s1);
        System.out.println("Tree "+root+", for array: "+s1);
        so = root.toArray(Arrays.asList(root), new ArrayList<>());
        System.out.println("Tree as Array: "+so);

        s1 = new Integer[]{5,1,4};
        root = TreeNode.asTree(s1);
        System.out.println("Tree "+root+", for array: "+s1);
        so = root.toArray(Arrays.asList(root), new ArrayList<>());
        System.out.println("Tree as Array: "+so);

        s1 = new Integer[]{5,1,4,null,null,6,null,null,null,null,null,8,9};
        /* Visualization of the above Tree.
        Index: {0,1,2,3    4,   5,6,   7,   8,   9,   10,   11, 12}
        Nodes: {5,1,4,null,null,6,null,null,null,null,null, 8,  9}
        Graph:
                     5
                    / \
                   1   4
                      /
                     6
                    / \
                   8   9
         */
        root = TreeNode.asTree(s1);
        System.out.println("Tree "+root+", for array: "+s1);
        so = root.toArray(Arrays.asList(root), new ArrayList<>());
        System.out.println("Tree as Array: "+so);
    }

    public static void main(String[] args) {
        testTreeFrom();
    }
}
















