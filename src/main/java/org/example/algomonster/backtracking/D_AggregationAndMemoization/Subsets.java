package org.example.algomonster.backtracking.D_AggregationAndMemoization;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Subsets {

    private static void dfs(int idx, List<Integer> path, List<Integer> nums, List<List<Integer>> ans) {
        if (idx == nums.size()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        path.add(nums.get(idx));
        dfs(idx+1, path, nums, ans);
        path.remove(path.size()-1);
        dfs(idx+1, path, nums, ans);
    }

    public static List<List<Integer>> subsets(List<Integer> nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(0,new ArrayList<>(), nums, ans);
        return ans;
    }
}
