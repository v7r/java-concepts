package org.example.algomonster.priorityqueue_heap;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not necessarily the kth distinct element.
 *
 * Example 1:
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElement {
    public static int findKthLargest(List<Integer> nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        for (Integer i : nums) {
            q.add(i);
        }
        int i = 0;
        while(!q.isEmpty()) {
            int v = q.poll();
            if (i == k - 1) {
                return v;
            }
            i++;
        }
        return -1;
    }
}
