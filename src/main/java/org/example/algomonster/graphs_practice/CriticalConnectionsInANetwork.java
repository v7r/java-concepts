package org.example.algomonster.graphs_practice;

import java.util.*;

/**
 * There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.
 *
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 *
 * Return all critical connections in the network in any order.
 *
 *
 *
 * Example 1:
 *  <img src="imgs/CriticalConnectionsInANetworkEg1.png" />
 *
 *
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 * Example 2:
 *
 * Input: n = 2, connections = [[0,1]]
 * Output: [[0,1]]
 *
 *
 * Constraints:
 *
 * 2 <= n <= 105
 * n - 1 <= connections.length <= 105
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * There are no repeated connections.
 */
public class CriticalConnectionsInANetwork {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        //return new MySolution().criticalConnections(n,connections);
        return new TarjansStronglyConnectedComponentsAlgo().criticalConnections(n,connections);
    }
}

/**
 * Basic idea is Tarjan's algorithm and during the dfs we identify the edge that forms a bridge between two components.
 * We collect all such bridges as the answer to the problem.
 *
 * For visual flow of how this lgorithm works,
 * Refer: https://www.youtube.com/watch?v=wUgWX0nc4NY
 */
class TarjansStronglyConnectedComponentsAlgo {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> connection : connections) {
            graph.computeIfAbsent(connection.get(0), i -> new ArrayList<>()).add(connection.get(1));
            graph.computeIfAbsent(connection.get(1), i -> new ArrayList<>()).add(connection.get(0));
        }
        int[] idMap = new int[n+1];
        int[] lowMap = new int[n+1];
        Arrays.fill(idMap, -1); // -1 represents unvisited
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (idMap[i] == -1) dfs(i, -1, idMap, lowMap, graph, ans);
        }

        return ans;
    }

    //             /------------\
    // 1(1) -c-> 2(2) -c-> 3(3)  |
    //             \---> 4(2) --/
    //
    // Legend:
    //   a(b): a is the ID of the node, b is the lowest node that a can reach. Eg: 4(2) means 4 can reach a lowest node 2.
    //           Similarly 3(3) means node 3 can reach itself as the lowest node.
    //   -c- : Critical path. If this edge is disconnected connected component count increases.
    private void dfs(int node, int parent, int[] idMap, int[] lowMap, Map<Integer,
            List<Integer>> graph, List<List<Integer>> ans) {
        idMap[node] = lowMap[node] = node;
        for (Integer edge : graph.getOrDefault(node, List.of())) {
            if (parent == edge) continue;
            if (idMap[edge] == -1) {
                // Unvisited edge
                dfs(edge, node, idMap, lowMap, graph, ans);
                lowMap[node] = Math.min(lowMap[node], lowMap[edge]);
                //if (lowMap[node] < lowMap[edge]) {
                if (lowMap[edge] > idMap[node]) {
                    ans.add(List.of(node, edge));
                }
            } else {
                // Visited edge in the dfs path. This edge might form a cycle.
                lowMap[node] = Math.min(lowMap[node], idMap[edge]);
            }
        }
    }
}

/**
 * Bruteforce solution. Where for each edge, two sets of connected components are calculated for each vertex. If
 * no common vertex found in both the sets then that edge is a critical connection. Connected component of a vertex is
 * calculated by BFS starting from that vertex.
 *
 */
class MySolution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> connection : connections) {
            graph.computeIfAbsent(connection.get(0), i -> new ArrayList<>()).add(connection.get(1));
            graph.computeIfAbsent(connection.get(1), i -> new ArrayList<>()).add(connection.get(0));
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (List<Integer> connection : connections) {
            Set<Integer> left = collect(connection.get(0), new HashMap<>(Map.of(connection.toString(), Boolean.TRUE)), graph);
            Set<Integer> right = collect(connection.get(1), new HashMap<>(Map.of(connection.toString(), Boolean.TRUE)), graph);
            if (!hasAnyCommon(left, right)) ans.add(connection);
        }
        return ans;
    }

    private static boolean hasAnyCommon(Set<Integer> left, Set<Integer> right) {
        return left.stream().anyMatch(i -> right.contains(i));
    }

    private static Set<Integer> collect(Integer r, Map<String, Boolean> visited, Map<Integer, List<Integer>> graph) {
        Set<Integer> ans = new HashSet<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(r);
        while (!q.isEmpty()) {
            Integer n = q.poll();
            ans.add(n);
            for (Integer e : graph.getOrDefault(n, List.of())) {
                if (visited.getOrDefault(List.of(n, e).toString(), Boolean.FALSE)) continue;
                if (visited.getOrDefault(List.of(e, n).toString(), Boolean.FALSE)) continue;
                visited.put(List.of(n,e).toString(), Boolean.TRUE);
                q.add(e);
            }
        }
        return ans;
    }
}
