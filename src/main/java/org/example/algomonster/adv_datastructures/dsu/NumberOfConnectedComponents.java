package org.example.algomonster.adv_datastructures.dsu;

import java.util.*;

/**
 * For this question we start with n nodes in a graph that are all independent of each other. We will then begin
 * connecting nodes in the graph. After each connection connecting two different nodes we ask you to compute the number
 * of connected components in a graph. A connected component is a series of node such that there exists a path between
 * any two nodes in the graph. Nodes will be 0-indexed and will be integers from 0 to n - 1.
 *
 * Example
 * Input: n = 5, connections = [[1, 2], [2, 3], [1, 3], [0, 4], [0, 4]]
 * Output: [4, 3, 3, 2, 2]
 *  <img src="imgs/NumberOfConnectedComponents.png" />
 *
 */
public class NumberOfConnectedComponents {
    private static class Dsu {
        private Map<Integer, Integer> treeSet = new HashMap<>();
        private Map<Integer, Set<Integer>> setIdElMap = new HashMap<>();

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
            Set<Integer> xSetIdEls = setIdElMap.getOrDefault(xSetId, Set.of(x));
            Set<Integer> ySetIdEls = setIdElMap.getOrDefault(ySetId, Set.of(y));
            Set<Integer> union = new HashSet<>();
            union.addAll(xSetIdEls);
            union.addAll(ySetIdEls);

            treeSet.put(find(x), find(y));
            setIdElMap.remove(xSetId);
            if (!xSetId.equals(ySetId)) {
                setIdElMap.put(find(x), union);
            } else {
                setIdElMap.put(find(x), xSetIdEls);
            }
        }

        public Integer countSets() {
            return setIdElMap.size();
        }
    }


    public static List<Integer> numberOfConnectedComponents(int n, List<List<Integer>> connections) {
        List<Integer> ans = new LinkedList<>();
        Dsu dsu = new Dsu();
        for (int i = 0; i < n; i++) {
            dsu.merge(i, i);
        }
        int i = 0;
        for (List<Integer> connection : connections) {
            dsu.merge(connection.get(0), connection.get(1));
            ans.add(dsu.countSets());
        }
        return ans;
    }

}