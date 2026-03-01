package org.example.algomonster.backtracking.D_AggregationAndMemoization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given coins of different denominations and an amount. Write a function to compute the fewest number of coins
 * that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins,
 * return -1.
 *
 * Example 1:
 * Input: coins = [1, 2, 5], amount = 11
 *
 * Output: 3
 *
 * Explanation:
 * 11 = 5 + 5 + 1, 3 total coins
 *
 * Example 2:
 * Input: coins = [3], amount = 1
 *
 * Output: -1
 *
 * Solution:
 *  <img src="../imgs/MinNumberOfCoinsToMakeupAGiveValue.png" />
 */
public class MinNumberOfCoinsToMakeupAGiveValue {
    private static int coinCount(int sum, int count, List<Integer> coins, int targetAmount, Map<String,Integer> m) {
        String key = String.join("",String.valueOf(sum),String.valueOf(count));
        if (m.get(key) != null) return m.get(key);
        if (sum == targetAmount) {
            m.put(key,count);
            return count;
        }
        int nodeMin = Integer.MAX_VALUE;
        for (Integer c : coins) {
            if (sum + c > targetAmount) continue;
            int subtreeMin = coinCount(sum + c, count + 1, coins, targetAmount,m);
            if (subtreeMin == -1) continue;
            nodeMin = Math.min(nodeMin, subtreeMin);
        }
        if (nodeMin == Integer.MAX_VALUE) nodeMin = -1;
        m.put(key,nodeMin);
        return nodeMin;
    }

    public static int coinChange(List<Integer> coins, int amount) {
        return coinCount(0, 0, coins, amount, new HashMap());
    }
}
