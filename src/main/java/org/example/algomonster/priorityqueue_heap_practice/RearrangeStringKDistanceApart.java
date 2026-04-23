package org.example.algomonster.priorityqueue_heap_practice;

import java.util.*;

/**
 * Rearrange String k Distance Apart
 *
 * Given a string s and an integer k, rearrange the string so that the same characters are at least k distance apart.
 *
 * If it is not possible, return an empty string.
 *
 * Example 1
 *
 * Input:
 *
 * s = "aabbcc", k = 3
 * Output:
 * abcabc
 *
 * Example 2
 *
 * Input:
 *
 * s = "aaabc", k = 3
 *
 * Output:
 *
 * ""
 *
 * Example 3
 *
 * Input:
 *
 * s = "aaadbbcc", k = 2
 *
 * Output:
 *
 * abacabcd
 */
public class RearrangeStringKDistanceApart {
    private static class ElementGroup {
        char element;
        int count;
        int nextInterval;

        ElementGroup(char element, int count, int nextInterval) {
            this.element = element;
            this.count = count;
            this.nextInterval = nextInterval;
        }
    }

    public static String rearrange(String s, int k) {
        if (k <= 1) return s;

        Map<Character, ElementGroup> elGroups = new HashMap<>();
        for (char c : s.toCharArray()) {
            elGroups.compute(c, (ch, v) -> {
                if (v == null) return new ElementGroup(ch, 1, 0);
                v.count++;
                return v;
            });
        }

        Queue<ElementGroup> q = new PriorityQueue<>(
                (e1, e2) -> Integer.compare(e2.count, e1.count)
        );

        for (ElementGroup eg : elGroups.values()) {
            q.add(eg);
        }

        Queue<ElementGroup> limiterQ = new ArrayDeque<>();
        int interval = 0;
        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty() || !limiterQ.isEmpty()) {
            while (!limiterQ.isEmpty() && limiterQ.peek().nextInterval <= interval) {
                q.add(limiterQ.poll());
            }

            if (q.isEmpty()) return "";

            ElementGroup eg = q.poll();
            sb.append(eg.element);
            eg.count--;
            interval++;

            if (eg.count > 0) {
                eg.nextInterval = interval + k - 1;
                limiterQ.add(eg);
            }
        }

        return sb.toString();
    }
}
