package org.example.algomonster.priorityqueue_heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a list of points on a 2D plane. Find k closest points to the origin (0, 0).
 *
 * Input: [(1, 1), (2, 2), (3, 3)], 1
 *
 * Output: [(1, 1)]
 */
public class KClosestPoints {
    private static class Point {
        public int x;
        public int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static List<List<Integer>> kClosestPoints(List<List<Integer>> points, int k) {
        PriorityQueue<Point> q = new PriorityQueue<>(
                (a, b) -> Integer.compare(
                        (a.x * a.x + a.y * a.y),
                        (b.x * b.x + b.y * b.y)
                ));
        for (List<Integer> p : points) {
            q.add(new Point(p.get(0), p.get(1)));
        }
        List<List<Integer>> ans = new ArrayList<>();
        int i = 0;
        while (i < k && !q.isEmpty()) {
            Point p = q.poll();
            ans.add(List.of(p.x, p.y));
            i++;
        }
        return ans;
    }
}
