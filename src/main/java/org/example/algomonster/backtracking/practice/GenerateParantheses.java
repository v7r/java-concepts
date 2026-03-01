package org.example.algomonster.backtracking.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, generate all combinations of well-formed parentheses consisting of n pairs.
 *
 * Return the combinations in any order.
 *
 * Examples
 *
 * n = 1 → ["()"]
 *
 * n = 2 → ["(())", "()()"]
 *
 * n = 3 → ["((()))","(()())","(())()","()(())","()()()"]
 *
 * Constraints
 *
 * 1 <= n <= 8
 */
public class GenerateParantheses {
    //               0
    //           (          ) x
    //        ((        ()
    // x(((      (()    ()(   ())x
    //     x(()(  *(())  *()()
    //
    private static void dfs(int idx, int lCount, int rCount, List<String> path, List<String> ans, int n) {
        if (rCount > lCount || lCount > n) return;
        if (idx == n*2) {
            ans.add(String.join("",path));
            return;
        }

        path.add("(");
        dfs(idx+1, lCount+1, rCount, path, ans, n);
        path.remove(path.size() - 1);

        path.add(")");
        dfs(idx+1, lCount, rCount+1, path, ans, n);
        path.remove(path.size() - 1);
    }

    public static List<String> generateParantheses(int n) {
        List<String> ans = new ArrayList<>();
        dfs(0,0,0, new ArrayList<>(), ans, n);
        return ans;
    }
}
