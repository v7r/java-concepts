package org.example.algomonster.twopointers;

import java.util.List;

/**
 * Given an array representing heights of vertical lines, find the maximum area of water trapped between two lines.
 *
 * Input: [1,8,6,2,5,4,8,3,7].
 *
 * Output: 49.
 */
public class ContainerWithMostWater {
    public static int containerWithMostWater(List<Integer> arr) {
        int maxVol = 0;
        int l = 0, r = arr.size() - 1;
        while (l<r) {
            if ((r-l)* Math.min(arr.get(l),arr.get(r)) > maxVol) {
                maxVol = (r-l)* Math.min(arr.get(l),arr.get(r));
            }
            if (arr.get(l)>=arr.get(r)) {
                r--;
            } else {
                l++;
            }
        }
        return maxVol;
    }
}
