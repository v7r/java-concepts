package org.example.algomonster.adv_datastructures.dsu;

import java.util.HashMap;
import java.util.Map;

/**
 * Now we will start with an introductory problem to get you familiar with the data structure. Complete the class below
 * to support the following two operations:
 *
 * merge(x, y) merges the sets to which x and y belong,
 * is_same(x, y) determines if x and y belong to the same set. If so return true, otherwise false.
 *
 * Example:
 * merge(1, 2)
 * merge(2, 3)
 * is_same(1, 3) => true
 * is_same(2, 4) => false
 * Explanation:
 * We merge elements 1 and 2 then we merge the set of 1 and 2 with the element 3, so we should now have 2 sets:
 * [1, 2, 3] and [4]. Therefore 1 and 3 are in the same set, while 2 and 4 are in different sets.
 *
 */
public class DisjointSetUnionIntroductoryProblem {
    public static class SameSet {
        Map<Integer, Integer> dsuMap = new HashMap<>();

        private Integer find(Integer i) {
            Integer j = dsuMap.getOrDefault(i, i);
            if (j != i) {
                // i already has a parent.
                j = find(j); // attempt to get to the root;
                dsuMap.put(i, j); // set the root as parent to i;
            }
            return j;
        }

        public void merge(int x, int y) {
            dsuMap.put(find(x), find(y));
        }

        public boolean isSame(int x, int y) {
            return find(x).equals(find(y));
        }
    }
}
