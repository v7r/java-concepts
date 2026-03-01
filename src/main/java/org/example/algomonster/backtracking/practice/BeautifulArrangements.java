package org.example.algomonster.backtracking.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Suppose you have n integers labeled 1 through n. A permutation of those n integers perm (1-indexed) is considered a
 * beautiful arrangement if for every i (1 <= i <= n), either of the following is true:
 *
 * perm[i] is divisible by i.
 * i is divisible by perm[i].
 * Given an integer n, return the number of the beautiful arrangements that you can construct.
 *
 * Example 1:
 *
 * Input: n = 2
 *
 * Output: 2
 *
 * Explanation:
 *
 * The first beautiful arrangement is [1,2]: perm[1] = 1 is divisible by i = 1. perm[2] = 2 is divisible by i = 2.
 * The second beautiful arrangement is [2,1]: perm[1] = 2 is divisible by i = 1. i = 2 is divisible by perm[2] = 1.
 *
 * Example 2:
 *
 * Input: n = 1
 *
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= n <= 15
 */
public class BeautifulArrangements {

    private static void dfs(int n, boolean[] visited, List<Integer> path, List<List<Integer>> ans) {
        if (path.size() == n) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            int pi = path.size() + 1; // next index in path. +1 to make it 1 based index;
            if (pi % i == 0 || i % pi == 0) {
                visited[i] = true;
                path.add(i);
                dfs(n, visited, path, ans);
                visited[i] = false;
                path.remove(path.size()-1);
            }
        }
    }

    public static int arrange(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(n, new boolean[n+1], new ArrayList<Integer>(), ans);
        return ans.size();
    }
}
