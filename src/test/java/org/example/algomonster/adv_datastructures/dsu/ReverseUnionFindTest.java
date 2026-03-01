package org.example.algomonster.adv_datastructures.dsu;

import junit.framework.TestCase;

import java.util.*;

/**
 * Tests for ReverseUnionFind.umbristan
 * These tests cover the provided example and additional edge cases to challenge the reverse-union logic:
 * - example from the class comment
 * - empty breaks list
 * - redundant breaks (duplicate edges)
 * - self-loop breaks
 * - a deterministic "random" case validated by a simple reference union-find
 *
 * Do NOT modify production logic.
 */
public class ReverseUnionFindTest extends TestCase {

    public void testExampleFromComment() {
        int n = 4;
        List<List<Integer>> breaks = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(3, 4),
                Arrays.asList(1, 4),
                Arrays.asList(2, 4)
        );
        List<Integer> out = ReverseUnionFind.umbristan(n, breaks);
        List<Integer> expected = Arrays.asList(1, 1, 2, 3, 4);
        assertEquals(expected, out);
    }

    public void testEmptyBreaksReturnsEmptyList() {
        int n = 5;
        List<List<Integer>> breaks = Collections.emptyList();
        List<Integer> out = ReverseUnionFind.umbristan(n, breaks);
        assertNotNull(out);
        assertTrue(out.isEmpty());
    }

    public void testRedundantBreaksHandledGracefully() {
        int n = 3;
        List<List<Integer>> breaks = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(1,2),
                Arrays.asList(2,3)
        );
        List<Integer> out = ReverseUnionFind.umbristan(n, breaks);
        List<Integer> expected = referenceReverseUnionCounts(n, breaks);
        assertEquals(expected, out);
    }

    public void testSelfLoopBreaksIgnored() {
        int n = 3;
        List<List<Integer>> breaks = Arrays.asList(
                Arrays.asList(1,1),
                Arrays.asList(2,3)
        );
        List<Integer> out = ReverseUnionFind.umbristan(n, breaks);
        List<Integer> expected = referenceReverseUnionCounts(n, breaks);
        assertEquals(expected, out);
    }

    public void testDeterministicCaseMatchesReference() {
        int n = 6;
        List<List<Integer>> breaks = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(2,3),
                Arrays.asList(4,5),
                Arrays.asList(1,3),
                Arrays.asList(3,4)
        );
        List<Integer> out = ReverseUnionFind.umbristan(n, breaks);
        List<Integer> expected = referenceReverseUnionCounts(n, breaks);
        assertEquals(expected, out);
    }

    // Helper: compute expected counts by performing the same reverse process with a simple integer-based DSU
    private List<Integer> referenceReverseUnionCounts(int n, List<List<Integer>> breaks) {
        List<Integer> ans = new ArrayList<>();
        // simple parent-based union find
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;
        int components = n;

        for (int i = breaks.size() - 1; i >= 0; i--) {
            ans.add(components);
            List<Integer> edge = breaks.get(i);
            int a = edge.get(0);
            int b = edge.get(1);
            // ignore invalid indices
            if (a < 1 || a > n || b < 1 || b > n) continue;
            int ra = find(parent, a);
            int rb = find(parent, b);
            if (ra != rb) {
                parent[ra] = rb;
                components--;
            }
        }
        Collections.reverse(ans);
        return ans;
    }

    private int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        parent[x] = find(parent, parent[x]);
        return parent[x];
    }
}

