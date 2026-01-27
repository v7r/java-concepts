package org.example.algomonster.dfs;

/**
 * Lowest common ancestor (LCA) of two nodes v and w in a tree is the lowest (i.e., deepest) node that has both v and w
 * as descendants. We also define each node to be a descendant of itself (so if v has a direct connection to w, w is the
 * lowest common ancestor).
 *
 * You can assume each node value in the tree is unique and that both target nodes are guaranteed to exist in the tree.
 *
 * Given two nodes of a binary tree, find their lowest common ancestor.
 *
 *
 *<h3>Solution Explanation:(don't peek)</h3>
 *
 *<h3>Intuition</h3>
 *Wouldn't it be great if we could work from the target nodes and push some information upwards that lets us find the LCA
 *easily? If we could do this we could do something like the following:


 *Sadly, this is impossible as we would need to know exactly where node1 and node2 are. Additionally, each node in the
 *binary tree would need a reference to their parent node in order to find the common ancestors of both nodes.
 *<br/>
 * <img src="imgs/LowestCommonAncesterOfaBinaryTreeSol1.png" />
 *<br/>
 *<br/>
 * <h3>Solution</h3>
 * We can, however, mimic this idea using DFS! Specifically, we will use a Postorder traversal method because it will go
 * all the way down from the root to the leaf nodes and work its way back up root again. Here's a short animation of a
 * postorder traversal visiting the leaf nodes of each subtree first:
 *<br/>
 * <img src="imgs/LowestCommonAncesterOfaBinaryTreeSol2.png" />
 *<br/>
 *<br/>
 *
 * How can we use postorder DFS traversal to help us? Once we finish processing a node-x and return to its parent node,
 * we will consider a few cases:
 *
 * <ol>
 * <li>The node-x is null</li>
 * <li>The node-x is either node1 or node2</li>
 * <li>The node-x is neither node1 nor node2</li>
 * </ol>
 *
 * <p>
 * The first two cases (1) and (2) are simple and we can simply return themselves immediately. That is, if the current
 * node is null, return null; and if the current node is either node1 or node2, immediately return node1 or node2
 * (whichever it is). We will see later why we can do this.</p>
 *
 * <p>
 * Case (3) is trickier in that it also involves some case work due to it getting returned values from both its
 * subtrees. These are the cases and their respective behaviors:</p>
 *
 * <ul>
 * <li>a. If both subtrees return non-null nodes: return the current node itself</li>
 * <li>b. If both subtrees return null nodes: return null</li>
 * <li>c. If exactly one of the subtrees returns a non-null node and the other returns a null node: return the non-null
 * node</li>
 * </ul>
 * <p>
 * Here's a figure of the uses of the cases above:
 * </p>
 *<br/>
 * <img src="imgs/LowestCommonAncesterOfaBinaryTreeSol3.png" />
 *<br/>
 *<br/>
 * <p>
 * Here's another example of why we would/could return immediately even if one of the target nodes exists in the subtree
 * of the other:
 * </p>
 * <br/>
 * <img src="imgs/LowestCommonAncesterOfaBinaryTreeSol4.png" />
 * <br/>
 * <br/>
 * <p>Finally, here are some examples of this LCA algorithm on a binary tree and the respective return values of each
 * node:</p>
 *   <br/>
 *   <img src="imgs/LowestCommonAncesterOfaBinaryTreeSol5.png" />
 *   <br/>
 *   <br/>
 *   <br/>
 *   <img src="imgs/LowestCommonAncesterOfaBinaryTreeSol6.png" />
 *   <br/>
 *   <br/>
 *   <br/>
 *   <img src="imgs/LowestCommonAncesterOfaBinaryTreeSol7.png" />
 *   <br/>
 *   <br/>
 *   <br/>
 *   <img src="imgs/LowestCommonAncesterOfaBinaryTreeSol8.png" />
 *   <br/>
 *   <br/>
 * */
public class LowestCommonAncesterOfaBinaryTree {
    public static Node<Integer> lca(Node<Integer> root, Node<Integer> node1, Node<Integer> node2) {
        if (root == null) return null;
        if (root.val.compareTo(node1.val) == 0) return root;
        if (root.val.compareTo(node2.val) == 0) return root;

        Node<Integer> leftLca = lca(root.left,node1,node2);
        Node<Integer> rightLca = lca(root.right,node1,node2);

        if (leftLca != null && rightLca != null) return root;
        if (leftLca != null) return leftLca;
        if (rightLca != null) return rightLca;

        // neither found on left nor right subtrees.
        return null;
    }
}


