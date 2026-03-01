package org.example.algomonster.backtracking.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given two integers n and k, return an array of all the integers of length n where the difference between every two
 * consecutive digits is k. You may return the answer in any order.
 *
 * Note that the integers should not have leading zeros. Integers as 02 and 043 are not allowed.
 *
 * Example 1:
 *
 * Input: n = 3, k = 7
 *
 * Output: [181,292,707,818,929]
 *
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 *
 * Example 2:
 *
 * Input: n = 2, k = 1
 *
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 * Constraints:
 *
 * 2 <= n <= 9
 * 0 <= k <= 9
 */
public class NumbersWithSameConseqDifference {

    // base case: if idx == n capture the number in path;
    // state: idx, path, int k;
    // body: path contains the digits until [1..idx] that satisfy consequent difference of k. Find the digit at idx with
    //       k difference and recurse from idx+1;
    private static void dfs(int idx, List<Integer> path, int n, int k, List<Integer> ans) {
        if (idx == n) {
            int val = 0;
            for (int i = 0; i < path.size(); i++) {
                val += (int)Math.pow(10.0, n-i-1) * path.get(i);
            }
            ans.add(val);
            return;
        }
        int lastDigit = path.isEmpty() ? 0 : path.get(path.size()-1);
        for (int i = 0; i < 10; i++) {
            if (idx == 0 && i > 0) {
                path.add(i);
                dfs(idx+1, path, n, k, ans);
                path.remove(path.size()-1);
            }
            if (idx > 0 && Math.abs(lastDigit - i) == k) {
                path.add(i);
                dfs(idx+1, path, n, k, ans);
                path.remove(path.size()-1);
            }
        }
    }


    public static List<Integer> findNumbers(int n, int k) {
        List<Integer> ans = new ArrayList<>();
        dfs(0,new ArrayList<>(), n, k, ans);
        return ans;
    }

}
