package org.example.algomonster.priorityqueue_heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given k sorted lists of numbers, merge them into one sorted list.
 *
 * Input: [[1, 3, 5], [2, 4, 6], [7, 10]]
 *
 * Output: [1, 2, 3, 4, 5, 6, 7, 10]
 */
public class MergeKSortedLists {
    private static class IndexedQueue {
        public int head;
        public List<Integer> list;
        public IndexedQueue(List<Integer> list) {
            this.head = 0;
            this.list = list;
        }

        public int peek() {
            if (!this.hasMore()) return Integer.MAX_VALUE;
            return this.list.get(this.head);
        }

        public int pop() {
            if (!this.hasMore()) return -1;
            return this.list.get(this.head++);
        }

        public boolean hasMore() {
            return this.head < this.list.size();
        }
    }
    public static List<Integer> mergeKSortedLists(List<List<Integer>> lists) {
        Queue<IndexedQueue> q = new PriorityQueue<>((i, j) -> {
            int iv = i.peek();
            int jv = j.peek();
            return Integer.compare(iv, jv);
        });
        for (List<Integer> l : lists) {
            if (l.isEmpty()) continue;
            q.add(new IndexedQueue(l));
        }
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            IndexedQueue iq = q.poll();
            ans.add(iq.pop());
            if (iq.hasMore()) q.add(iq);
        }
        return ans;
    }
}
