package org.example.leetcode;


import java.util.Optional;

/**
 * Problem statement:
 *
 * Given an integer n, return the number of structurally unique BST's (binary search trees)
 * which has exactly n nodes of unique values from 1 to n.
 * Ref: https://leetcode.com/problems/unique-binary-search-trees/description/
 *
 *
 */
public class Solution97 {
    public int numTrees(int n) {
        return countUniqueTrees(1, n);
    }

    /*
      fn(m,n)
        count <- 0
        i in m..n
        ? m < i
            left <- fn(m, i-1)
        ? i < n
            right <- fn(i+1, n)
        ? left && right
            count += left * right
        ? left
            count += left
        ? right
            count += right
        end;
    */
    private int countUniqueTrees(int m, int n) {
        int count = 0;
        if (m <= 0 || n <= 0 || m > n) return 0;
        if (m == n) return 1;
        for (int j = m; j <= n; j++) {
            int leftCount = (j-1 < m) ? 0 : countUniqueTrees(m, j-1);
            int rightCount = (j+1 > n ) ? 0 : countUniqueTrees(j+1, n);
            count += leftCount*rightCount > 0 ? (leftCount*rightCount) : (leftCount+rightCount);
        }

        return count;
    }
}
