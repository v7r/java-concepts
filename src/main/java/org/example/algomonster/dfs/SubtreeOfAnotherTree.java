package org.example.algomonster.dfs;

/**
 * Given two binary trees root and subRoot, determine if subRoot is a subtree of root. A subtree of a binary tree is a
 * tree that consists of a node in the tree and all of its descendants.
 *
 * input:
 *  <img src="imgs/SubtreeOfAnotherTree.png" />
 *
 */
public class SubtreeOfAnotherTree {
    // IMPORTANT: LOOK BELOW FOR RECOMMENDED SOLUTION BY ALGO.MONSTER
    // My approach, but may not be accurate. So recommended is the Algo.monster solution below.
    private static boolean dfs(Node<Integer> root, Node<Integer> subRoot) {
        if (root == null || subRoot == null) return false;
        int rVal = root != null && root.val != null ? root.val : -1;
        int sVal = subRoot != null && subRoot.val != null ? subRoot.val : -1;

        boolean isSubRootLeaf = subRoot != null && subRoot.left == null && subRoot.right == null;
        boolean isRootLeaf = root != null && root.left == null && root.right == null;
        if (rVal == sVal && isSubRootLeaf && isRootLeaf) return true;
        if (rVal == sVal && isSubRootLeaf != isRootLeaf) return false;

        if (rVal == sVal) {
            boolean isLSubTree = root.left == null && subRoot.left == null || dfs(root.left, subRoot.left);
            boolean isRSubTree = root.right == null && subRoot.right == null || dfs(root.right, subRoot.right);
            return isLSubTree && isRSubTree;
        } else {
            boolean isLSubTree = dfs(root.left, subRoot);
            boolean isRSubTree = dfs(root.right, subRoot);
            return isLSubTree || isRSubTree;
        }
    }

    public static boolean subtreeOfAnotherTree(Node<Integer> root, Node<Integer> subRoot) {
        return dfs(root, subRoot);
    }


    // The following is the recommended solution by Algo.monster
    // This is brilliant, simple and concise :)
    private static boolean isSameTree(Node<Integer> root1, Node<Integer> root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        return root1.val == root2.val
                && isSameTree(root1.left, root2.left)
                && isSameTree(root1.right, root2.right);
    }

    public static boolean subtreeOfAnotherTreeByAlgoMonster(Node<Integer> root, Node<Integer> subRoot) {
        if (root == null) return false;
        return isSameTree(root, subRoot)
                || subtreeOfAnotherTreeByAlgoMonster(root.left, subRoot)
                || subtreeOfAnotherTreeByAlgoMonster(root.right, subRoot);
    }

}

/*
GPT-5 Analysis of both the solutions:

Comparison of implementations: subtreeOfAnotherTree() vs subtreeOfAnotherTreeByAlgoMonster()

Summary
- subtreeOfAnotherTree() (custom dfs):
  - Traverses `root`, attempting to match `subRoot` at nodes where values match, using
    additional heuristics (leaf checks and null-value shortcuts) to short-circuit checks.
  - Returns false immediately when either `root` or `subRoot` is null at the top of dfs,
    and treats null node values as -1 in comparisons.
  - Uses extra branching and special-case logic intended to reduce work in some cases.

- subtreeOfAnotherTreeByAlgoMonster() (algo.monster):
  - Canonical approach: for each node in `root`, call `isSameTree(rootNode, subRoot)`;
    `isSameTree` performs a strict structural + value equality check of two trees.
  - Simpler, easier to reason about, and less likely to contain subtle correctness bugs.

Time & Space Complexity
- Worst-case time complexity for both: O(n * m)
  - n = number of nodes in `root`, m = number of nodes in `subRoot`.
  - In the worst case, many `isSameTree`/match attempts may be performed and each can traverse up to m nodes.
- Space: O(H) recursion stack (H = height of the tree); worst-case O(n) for skewed trees.

Practical performance and constants
- Algo.monster approach has smaller constant factors and minimal branching; it is typically faster in practice
  and much easier to maintain.
- The custom dfs may avoid some work in very specific shaped inputs, but its additional branching,
  leaf-special-casing, and null-value hacks increase code complexity and can hurt performance
  and correctness on many inputs.

Correctness and robustness
- Algo.monster: clear correctness for structural + value equality. One small robustness note: the
  provided method returns `false` when `root == null` without checking `subRoot == null` first;
  according to many problem statements, `subRoot == null` should be considered a subtree of any tree.
  This is a small, fixable null-handling issue.

- Custom dfs: contains correctness concerns:
  - It returns false when either `root` or `subRoot` is null at the top of `dfs`. Standard semantics
    usually define `subRoot == null` as `true` (empty subtree) and `root == null && subRoot != null` as `false`.
  - The code uses `isSubRootLeaf != isRootLeaf` to reject a match early when one node is a leaf and the
    other isn't. That can cause the algorithm to miss valid matches deeper in `root` when a value
    coincidentally matches at a non-leaf node. This makes the implementation incorrect for some valid inputs.

Recommendation
- Prefer `subtreeOfAnotherTreeByAlgoMonster()` (the Algo.monster version):
  - It's simpler, less error-prone, and has comparable asymptotic complexity with better constants.
- Small, low-risk fixes:
  - Make `subtreeOfAnotherTreeByAlgoMonster` handle `subRoot == null` as `true` and `root == null` as `false`.
  - Remove or fix the heuristic shortcuts in `dfs` if you plan to rely on it; otherwise deprecate `dfs`.

Possible improvements for asymptotic performance (if needed)
- Serialize both trees (preorder with explicit null markers) and use a linear substring search (KMP)
  to check whether serialized `subRoot` is a substring of serialized `root`. This reduces time to O(n + m).
- Compute robust subtree hashes (Merkle hashing) bottom-up to detect equal subtrees in near-linear time.

*/
