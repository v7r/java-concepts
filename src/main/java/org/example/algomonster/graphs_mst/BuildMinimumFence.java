package org.example.algomonster.graphs_mst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * The Umbristan Department of Forestry (UDF) is tackling a rather difficult problem, and the Umbristan government has
 * assigned you as one of its best workers to go resolve the issue. When you arrive, you are quickly briefed on the
 * problem at hand. Inside the Umbristan National Park there exists an area that has been closed off as fencing needs
 * to be erected in the area. Your job is to split the area into different regions by connecting fences to the trees.
 * The department has set up some rules for the fences.
 *
 * The department has counted the number of trees in the area as well as determined all possible tree pairs where a
 * fence can be built between the pair.
 *
 * The fences need to be set up such that every tree in the area is connected to a fence.
 *
 * The fences should be connected to one another unless a connecting fence is unable to be built (You should be able to
 * visit all the trees by walking along the fence, except when it's impossible using the list provided by the
 * department).
 *
 * The department is on a very strict operating budget, so you need to minimize the metres of fencing required.
 *
 * Can you help them figure out the smallest amount of fencing required?
 *
 * It is possible that not all the nodes will be connected to one another depending on the tree pairs. Input will
 * consist of trees the number of trees in the area labelled from 1 to n as well as pairs, a list consisting of the
 * tuple [a, b, d] which denotes that a fence can be built between the trees a and b that will be d metres in length.
 *
 * Constraints
 * 1 <= trees <= 10^5
 *
 * Examples
 * Example 1:
 * Input: trees = 5, pairs = [[1, 2, 1], [2, 4, 2], [3, 5, 3], [4, 4, 4]]
 * Output: 6
 * Explanation:
 * We can erect fencing between trees for the following pairs, 1 and 2, 2 and 4, 3 and 5. With this every tree is
 * connected by a fence and we have used 6 metres of fencing.
 *
 *
 */
public class BuildMinimumFence {
    private static class Dsu {
        Map<Integer, Integer> treeSet = new HashMap<>();
        public Integer find(Integer t) {
            Integer s = treeSet.getOrDefault(t, t);
            if (!s.equals(t)) { // If t has a perent
                s = find(s); // Find parent of parent.
                treeSet.put(t, s); // Compress tree.
            }
            return s;
        }
        public void merge(Integer s, Integer t) {
            treeSet.put(find(s), find(t));
        }
    }

    public static int mstForest(int trees, List<List<Integer>> pairs) {
        PriorityQueue<List<Integer>> q =
                // Sor the pairs by it's weights(index=2) in ascending order;
                new PriorityQueue<List<Integer>>((l1, l2) -> Integer.compare(l1.get(2), l2.get(2)));
        q.addAll(pairs);

        // We keep track of connected nodes using Disjoint set-union ( refer ../adv.datastructures/dsu)
        Dsu dsu = new Dsu();

        // we keep incrementing count if we include an edge in the tree.
        //The tree is complete we have n-1 edges
        int edgeCount = 0;
        int edgeWeight = 0;
        while (!q.isEmpty()) {
            List<Integer> pair = q.poll();
            // If two nodes are from different sets they are disconnected.
            if (!dsu.find(pair.get(0)).equals(dsu.find(pair.get(1)))) {
                // we connect the disconnected nodes. and increment the edge count.
                dsu.merge(pair.get(0), pair.get(1));
                edgeCount++;
                edgeWeight += pair.get(2);
                // When edges are n-1 of a n node tree, they all the nodes are connected.
                if (edgeCount == trees - 1) break;
            }
        }
        return edgeWeight;
    }
}
