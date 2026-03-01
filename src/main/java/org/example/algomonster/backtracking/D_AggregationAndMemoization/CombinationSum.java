package org.example.algomonster.backtracking.D_AggregationAndMemoization;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations
 * of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
 * frequency of at least one of the chosen numbers is different.
 *
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the
 * given input.
 *
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 *
 * Output: [[2,2,3],[7]]
 *
 * Explanation: 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 *
 * 7 is a candidate, and 7 = 7.
 *
 * These are the only two combinations.
 *
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 *
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * Example 3:
 * Input: candidates = [2], target = 1
 *
 * Output: []
 *
 * Example 4:
 * Input: candidates = [1], target = 1
 *
 * Output: [[1]]
 *
 * Example 5:
 * Input: candidates = [1], target = 2
 *
 * Output: [[1, 1]]
 *
 * Constrains:
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * All elements of candidates are distinct.
 * 1 <= target <= 500
 *
 *
 * Solution:
 *  <img src="../imgs/CombinationSum.png" />
 */
public class CombinationSum {

    //[2,3,5], target = 6
    // |-- add 2 ( currentSum <= 6 )
    //      |-- add 2 ( current sum <= 6 )
    //           |-- add 2 ( current sum <= 6 )
    //                 |-- sum == 6; record [2,2,2]
    //           |-- skip 2
    //                 |-- skip 3 ( current sum 7 > 6; break )
    //      |-- skip 2
    //           |-- add 3 ( current sum 5 < 6 )
    //                 |-- skip 3 ( current sum 8 > 6 )
    //                 |-- skip 5 ( current sum 10 > 6 )
    //           |-- skip 3
    // |-- skip 2
    //      |-- add 3 ( current sum 3 <= 6 )
    //           |-- add 3 ( current sum 6 <= 6 )
    //                 |-- sum == 6; record [3,3]
    //      |-- skip 3
    //           |-- add 5 ( current sum 5 <= 6 )
    //                 | -- skip 5 ( current sum 10 > 6 )
    //                 | -- add

    // ChatGPT recommended logic.
    private static void dfsPractice1(int idx, List<Integer> path, int sum, List<Integer> candidates, int target,
                                     List<List<Integer>> ans) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (sum > target || idx == candidates.size()) return;

        // Decision 1: take current element (reuse allowed)
        path.add(candidates.get(idx));
        dfsPractice1(idx, path, sum + candidates.get(idx), candidates, target, ans);
        path.remove(path.size()-1);

        // Decision 2: skip current element
        dfsPractice1(idx+1, path, sum, candidates, target, ans);
    }

    public static List<List<Integer>> combinationSumPractice1(List<Integer> candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        dfsPractice1(0,new ArrayList<>(), 0, candidates, target, ans);
        return ans;
    }

    // My Intuition. Not performant, refer Algo.monster solution. Refer the solution diagram.
    private static void dfs(List<Integer> path, int pathSum, List<Integer> candidates, int target,
                            List<List<Integer>> ans, Map<String,Boolean> history) {
        if (pathSum == target) {
            // Leaf node; capture the path if unique otherwise discard;
            List<Integer> spath = new ArrayList<>(path);
            Collections.sort(spath);
            String pathKey = spath.stream().map(String::valueOf).collect(Collectors.joining());
            history.compute(pathKey, (k,v) -> {
                if (v == null || !v) {
                    ans.add(new ArrayList<>(path));
                    return Boolean.TRUE;
                } else {
                    return v;
                }
            });
            return;
        }
        for (Integer i : candidates) {
            if (pathSum + i <= target) {
                path.add(i);
                dfs(path, pathSum + i, candidates, target, ans, history);
                path.remove(path.size() - 1);
            }
        }
    }

    private static List<List<Integer>> combinationSumMyIntuition(List<Integer> candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(new ArrayList<>(), 0, candidates, target, ans, new HashMap<>());
        return ans;
    }
    // End of my intuition. Refer Algo.Monster solution that is efficient.

    // Algo.monster solution. This is recommended. My intuition has performance penalty. Notice the subtle difference.
    private static void dfsV2(int idx, int sum, List<Integer> path, List<Integer> candidates, int target,
                              List<List<Integer>> ans) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }

        if (sum > target) return;

        for (int i = idx; i < candidates.size(); i++) {
            if (sum + candidates.get(i) <= target) {
                path.add(candidates.get(i));
                dfsV2(i,sum + candidates.get(i),path,candidates,target,ans);
                path.remove(path.size()-1);
            }
        }
    }

    private static List<List<Integer>> combinationSumAlgoMonster(List<Integer> candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Collections.sort(candidates);
        dfsV2(0, 0, new ArrayList<>(), candidates, target, ans);
        return ans;
    }

    public static List<List<Integer>> combinationSum(List<Integer> candidates, int target) {
        return combinationSumPractice1(candidates, target);
        //return combinationSumAlgoMonster(candidates, target);
    }
}
