package org.example.algomonster.priorityqueue_heap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class KClosestPointsTest {

    private static String key(List<Integer> p) {
        return p.get(0) + "," + p.get(1);
    }

    private static Set<String> toSet(List<List<Integer>> pts) {
        Set<String> s = new HashSet<>();
        for (List<Integer> p : pts) s.add(key(p));
        return s;
    }

    private static Map<String, Integer> toCountMap(List<List<Integer>> pts) {
        Map<String, Integer> m = new HashMap<>();
        for (List<Integer> p : pts) m.put(key(p), m.getOrDefault(key(p), 0) + 1);
        return m;
    }

    @Test
    public void testBasic() {
        List<List<Integer>> points = new ArrayList<>();
        points.add(List.of(1, 1));
        points.add(List.of(2, 2));
        points.add(List.of(3, 3));

        List<List<Integer>> ans = KClosestPoints.kClosestPoints(points, 1);

        assertEquals(1, ans.size());
        assertEquals("1,1", key(ans.get(0)));
    }

    @Test
    public void testKGreaterThanN() {
        List<List<Integer>> points = new ArrayList<>();
        points.add(List.of(1, 2));
        points.add(List.of(3, 4));
        points.add(List.of(-1, -1));

        List<List<Integer>> ans = KClosestPoints.kClosestPoints(points, 5);

        // should return all points (order may vary)
        assertEquals(3, ans.size());
        Set<String> got = toSet(ans);
        Set<String> want = Set.of("1,2", "3,4", "-1,-1");
        assertEquals(want, got);
    }

    @Test
    public void testZeroK() {
        List<List<Integer>> points = new ArrayList<>();
        points.add(List.of(5, 5));

        List<List<Integer>> ans = KClosestPoints.kClosestPoints(points, 0);
        assertTrue(ans.isEmpty());
    }

    @Test
    public void testEmptyPoints() {
        List<List<Integer>> points = new ArrayList<>();
        List<List<Integer>> ans = KClosestPoints.kClosestPoints(points, 3);
        assertTrue(ans.isEmpty());
    }

    @Test
    public void testNegativeCoordinates() {
        List<List<Integer>> points = new ArrayList<>();
        points.add(List.of(-1, -1)); // dist 2
        points.add(List.of(-2, -3)); // dist 13
        points.add(List.of(4, 4));   // dist 32

        List<List<Integer>> ans = KClosestPoints.kClosestPoints(points, 2);

        assertEquals(2, ans.size());
        Set<String> got = toSet(ans);
        Set<String> want = Set.of("-1,-1", "-2,-3");
        assertEquals(want, got);
    }

    @Test
    public void testDuplicatePoints() {
        List<List<Integer>> points = new ArrayList<>();
        points.add(List.of(1, 1));
        points.add(List.of(1, 1));
        points.add(List.of(2, 2));

        List<List<Integer>> ans = KClosestPoints.kClosestPoints(points, 2);

        // Expect two points: two occurrences of (1,1)
        assertEquals(2, ans.size());
        Map<String, Integer> got = toCountMap(ans);
        assertEquals(2, got.getOrDefault("1,1", 0));
    }

    @Test
    public void testTieCase() {
        List<List<Integer>> points = new ArrayList<>();
        points.add(List.of(1, 0)); // dist 1
        points.add(List.of(0, 1)); // dist 1
        points.add(List.of(2, 2)); // dist 8

        List<List<Integer>> ans = KClosestPoints.kClosestPoints(points, 1);

        assertEquals(1, ans.size());
        String got = key(ans.get(0));
        // either (1,0) or (0,1) is acceptable
        assertTrue(got.equals("1,0") || got.equals("0,1"));
    }
}

