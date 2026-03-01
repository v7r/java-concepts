package org.example.algomonster.graphs_practice;

/**
 * There is an infrastructure of n cities with some number of roads connecting these cities. Each roads[i] = [ai, bi]
 * indicates that there is a bidirectional road between cities ai and bi.
 *
 * The network rank of two different cities is defined as the total number of directly connected roads to either city.
 * If a road is directly connected to both cities, it is only counted once.
 *
 * The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.
 *
 * Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.
 *
 * Example 1:
 *  <img src="./imgs/MaximalNetworkRankEg1.png" />
 *
 *
 * Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
 * Output: 4
 * Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads that are connected to either 0 or 1. The
 * road between 0 and 1 is only counted once.
 *
 * Example 2:
 *  <img src="./imgs/MaximalNetworkRankEg2.png" />
 *
 *
 * Input: n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
 * Output: 5
 * Explanation: There are 5 roads that are connected to cities 1 or 2.
 *
 * Example 3:
 * Input: n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
 * Output: 5
 * Explanation: The network rank of 2 and 5 is 5. Notice that all the cities do not have to be connected.
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * 0 <= roads.length <= n * (n - 1) / 2
 * roads[i].length == 2
 * 0 <= ai, bi <= n-1
 * ai != bi
 * Each pair of cities has at most one road connecting them.
 */
public class MaximalNetworkRank {
    /**
     * Explanations:
     * The best approach is brute force. We are asked to find a pair of vertices, but the two vertices are not
     * necessarily connected. The other approaches may not be as efficient, as we are not looking for a path or
     * ordering, the edges in this question is only a counter.
     *
     * We wish to compare the rank for each pair of cities. First, we find the rank (degree) of each node. Then the
     * rank for each pair is rank[i] + rank[j] or rank[i] + rank[j] -1 depending on whether there is an edge between i
     * and j. We compare the ranks of each pair to find the maximum.
     *
     * Implementation
     * def maximalNetworkRank(self, n: int, roads: List[List[int]]) -> int:
     *     ranks = defaultdict(int)
     *
     *     for road in roads:
     *         ranks[road[0]] += 1
     *         ranks[road[1]] += 1
     *
     *     maxrank = 0
     *     for i in range(n):
     *         for j in range(i + 1,  n):
     *             newrank = ranks[i] + ranks[j]
     *             if newrank > maxrank:
     *                 maxrank = newrank - (1 if [i, j] in roads or [j, i] in roads else 0)
     *     return maxrank
     * Problem link: https://leetcode.com/problems/maximal-network-rank/
     */
}
