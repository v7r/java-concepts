package org.example.algomonster.adv_datastructures.dsu;

import java.util.*;

/**
 * You are the new Director of Infrastructure at Algo Monster. Your primary task is to systematically dismantle the
 * roads connecting cities in a province, with plans for future reconstruction.
 *
 * Follow this procedure:
 *
 * Dismantle the roads in the sequence provided in the input. After dismantling each road, calculate and record the
 * number of city clusters remaining. A "cluster" is defined as a group of cities where it's possible to travel between
 * any two cities within that group using the roads that remain. For example, if there's a path from city A to city B
 * using existing roads, then A and B are in the same cluster.
 *
 * Input:
 *
 * A number 'n', representing the total number of cities. A list detailing the roads to be dismantled. Each road is
 * shown as a pair of integers. For instance, [1,2] means there's a road between city 1 and city 2. Output:
 *
 * A list of integers showing the number of city clusters after each road is dismantled.
 *
 * Examples
 * Example 1:
 * Input: n = 4, breaks = [[1, 2], [2, 3], [3, 4], [1, 4], [2, 4]]
 * Output: [1, 1, 2, 3, 4]
 *
 * <img src="imgs/ReverseUnionFind.gif" />
 */
public class ReverseUnionFind {
    private static class Dsu {
        private Map<Integer, Integer> treeSet = new HashMap<>();
        private Map<Integer, Set<Integer>> setElMap = new HashMap<>();

        public Integer find(Integer x) {
            Integer y = treeSet.getOrDefault(x, x);
            if (!y.equals(x)) {
                y = find(y);
                treeSet.put(x, y);
            }
            return y;
        }

        public void merge(Integer x, Integer y) {
            Integer xSetId = find(x);
            Integer ySetId = find(y);
            Set<Integer> xSetEls = setElMap.getOrDefault(xSetId, Set.of(x));
            Set<Integer> ySetEls = setElMap.getOrDefault(ySetId, Set.of(y));
            Set<Integer> union = new HashSet<>();
            union.addAll(xSetEls);
            union.addAll(ySetEls);

            treeSet.put(find(x), find(y));
            setElMap.remove(xSetId);
            if (!xSetId.equals(ySetId)) {
                setElMap.put(find(x), union);
            } else {
                setElMap.put(find(x), xSetEls);
            }
        }

        public Integer size() {
            return setElMap.size();
        }
    }

    //Input: n = 4, breaks = [[1, 2], [2, 3], [3, 4], [1, 4], [2, 4]]
    // [3, 2, 1, 1, 1]
    // [1, 1, 2, 3, 4]
    //Output: [1, 1, 2, 3, 4]

    /**
     * The key observation is that in the very end, all of the cities are disconnected from one another, so that the
     * number of clusters is n in the very end. We can work backwards from the disconnected graph and maintain a
     * disjoint-set union data structure that helps counting the clusters. It is a lot easier to connect edges
     * than it is to break edges and so we keep track all our answers while working backwards and return a reverse
     * of that list.
     */
    public static List<Integer> umbristan(int n, List<List<Integer>> breaks) {
        List<Integer> ans = new ArrayList<>();
        Dsu dsu = new Dsu();
        for (int i = 1; i <= n; i++) {
            dsu.merge(i, i);
        }

        for (int i = breaks.size() - 1; i >= 0; i--) {
            ans.add(dsu.size());
            dsu.merge(breaks.get(i).get(0), breaks.get(i).get(1));
        }
        Collections.reverse(ans);
        return ans;
    }
}
