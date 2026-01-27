package org.example.algomonster.backtracking.C_BackgrackingAdditionalStates;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, generate all strings with n matching parentheses. "matching" parentheses mean
 *
 * there is equal number of opening and closing parentheses.
 * each opening parenthesis has matching closing parentheses.
 * For example, () is a valid string but )( is not a valid string because ) has no matching parenthesis before it and (
 * has no matching parenthesis after it.
 *
 * Input
 * n: number of matching parentheses
 * Output
 * all valid strings with n matching parentheses
 *
 * Examples
 * Example 1:
 * Input:
 *
 * n = 2
 * Output: (()) ()()
 *
 * Explanation:
 *
 * There are two ways to create a string with 2 matching parentheses.
 *
 * Example 2:
 * Input:
 *
 * n = 3
 * Output: ((())) (()()) (())() ()(()) ()()()
 *
 * Explanation:
 *
 * There are 5 ways to create a string with 3 matching parentheses.
 *
 *
 * Solution:
 *
 *   <img src="../imgs/GenerateAllValidParentheses.png" />
 *
 */
public class GenerateAllValidParentheses {
    private static void dfs(int n, int l, int r, List<String> res, List<String> path) {
        if (l > n) return; // left parenthesis count > n then abort the subtree;
        if (l < r) return; // right parenthesis count is greater than left then abort the subtree;
        if (path.size() == 2*n) { // if path is having equal amount of left and right paranthesis (n pairs = 2*n path size)
            res.add(String.join("", path));
            return;
        }

        path.add("(");
        dfs(n,l+1,r,res,path);
        path.remove(path.size()-1);

        path.add(")");
        dfs(n,l,r+1,res,path);
        path.remove(path.size()-1);
    }

    public static List<String> generateParentheses(int n) {
        List<String> res = new ArrayList<>();
        dfs(n,0,0,res,new ArrayList<>());
        return res;
    }
}
