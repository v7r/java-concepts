package org.example.algomonster.adv_datastructures.dsu;

import junit.framework.TestCase;

/**
 * Tests for DijsointUnionSetCount (the disjoint-union-count utility).
 * These tests exercise chaining, merge-order independence, duplicate merges, self-merge,
 * and counts on unseen elements to challenge the implementation.
 *
 * Do NOT modify production logic; tests assert expected behavior from the problem statement.
 */
public class DisjointUnionSetCountTest extends TestCase {

    public void testSimpleMergeChain() {
        DisjointUnionSetCount ds = new DisjointUnionSetCount();
        ds.merge(1, 2);
        ds.merge(2, 3);
        // All three should belong to same set of size 3
        assertEquals(3, ds.count(1));
        assertEquals(3, ds.count(2));
        assertEquals(3, ds.count(3));
        // An unseen element should be size 1
        assertEquals(1, ds.count(4));
    }

    public void testMergeOrderIndependence() {
        DisjointUnionSetCount ds = new DisjointUnionSetCount();
        ds.merge(3, 7);
        ds.merge(3, 4);
        ds.merge(7, 4);
        // Regardless of merge ordering, the three elements should form a set of size 3
        assertEquals(3, ds.count(3));
        assertEquals(3, ds.count(7));
        assertEquals(3, ds.count(4));
    }

    public void testRepeatedMergeIdempotent() {
        DisjointUnionSetCount ds = new DisjointUnionSetCount();
        ds.merge(5, 6);
        int before = ds.count(5);
        // Repeating the same merge should not change the set size
        ds.merge(5, 6);
        int after = ds.count(5);
        assertEquals(before, after);
    }

    public void testSelfMerge() {
        DisjointUnionSetCount ds = new DisjointUnionSetCount();
        ds.merge(8, 8);
        // Merging an element with itself should leave its set size as 1
        assertEquals(1, ds.count(8));
    }

    public void testMultipleSetsAndThenConnect() {
        DisjointUnionSetCount ds = new DisjointUnionSetCount();
        // two separate sets
        ds.merge(10, 11);
        ds.merge(12, 13);
        assertEquals(2, ds.count(10));
        assertEquals(2, ds.count(12));
        // connect them
        ds.merge(11, 12);
        // now all four should be in same set
        assertEquals(4, ds.count(10));
        assertEquals(4, ds.count(11));
        assertEquals(4, ds.count(12));
        assertEquals(4, ds.count(13));
    }

    public void testUnseenElementCountIsOne() {
        DisjointUnionSetCount ds = new DisjointUnionSetCount();
        assertEquals(1, ds.count(999));
    }
}

