package org.example.algomonster.adv_datastructures.dsu;

import java.util.HashMap;
import java.util.Map;

/**
 * Count the disjoint set elements.
 *
 * Implement the operations merge(x,y) and count(x).
 *
 * Eg: 1
 *   merge(1,2)
 *   merge(2,3)
 *   count(3) == 3
 *   count(4) == 1
 *
 * Eg: 2
 *   merge(3,7)
 *   merge(3,4)
 *   merge(7,4)
 *   count(7) == 3
 */
public class DisjointUnionSetCount {
    private static class Dsu<T> {
        private Map<T, T> dsuTrees = new HashMap<>();
        private Map<T, Integer> countMap = new HashMap<>();
        public T find(T x) {
            // Look for the parent of x, if not found return x;
            T y = dsuTrees.getOrDefault(x, x);

            // If x has a parent
            if (!y.equals(x)) {
                // Find the parent of x's parent to compress the tree so that the next find will be faster.
                y = find(y);
                // record the parent of x's parent as x immediate parent. See the compression ?
                // ( a -> b -> ... -> c => a -> c )
                dsuTrees.put(x,y);
            }
            return y;
        }

        public void union(T x, T y) {
            // find the setID of x and setId of y.
            T xSetId = find(x);
            T ySetId = find(y);

            //Calculate the total unionCount of the setID of x count and setID of y count.
            int unionCount = countMap.getOrDefault(find(x), 1) + countMap.getOrDefault(find(y), 1);

            // Proceed to merge both the sets.
            dsuTrees.put(find(x), find(y));

            // of both the elements belong to the same set then we don't have to update the count.
            // else we should update the count of new setID( union of set-x and set-y ) with the unionCount we calculated earlier.
            if (!xSetId.equals(ySetId)) {
                countMap.put(find(x), unionCount); // note that find(x) at this line will be setId of union of both the sets.
            }
        }

        public Integer count(T x) {
            return this.countMap.getOrDefault(find(x), 1);
        }
    }

    private Dsu<Integer> dsu = new Dsu<>();
    public void merge(int x, int y) {
        dsu.union(x,y);
    }

    public int count(int x) {
        return dsu.count(x);
    }
}
