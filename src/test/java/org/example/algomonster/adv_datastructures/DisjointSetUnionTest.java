package org.example.algomonster.adv_datastructures;

import junit.framework.TestCase;
import org.example.algomonster.adv_datastructures.dsu.DisjointSetUnion;

/**
 * Tests for DisjointSetUnion.
 * These tests exercise typical and edge cases to challenge the DSU implementation:
 * - basic union/find
 * - union chains and expected root selection
 * - multiple disjoint sets
 * - self-union
 * - find on unseen elements
 * - mixing types (Strings) and duplicate unions
 *
 * Do NOT modify production logic.
 */
public class DisjointSetUnionTest extends TestCase {

    public void testFindOnUnseenReturnsItself() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        Integer x = 42;
        assertEquals(x, dsu.find(x));
    }

    public void testBasicUnionAndFind() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        dsu.union(1, 2);
        // after union(1,2) both should have same representative
        assertEquals(dsu.find(1), dsu.find(2));
    }

    public void testBasicUnionAndFind1() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        dsu.union(0, 1);
        dsu.union(0, 2);
        // after union(0,1) union(0,2) both should have same representative
        assertEquals(dsu.find(0), dsu.find(2));
    }

    public void testUnionChainProducesSingleRoot() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        dsu.union(1, 2);
        dsu.union(2, 3);

        dsu.find(1);

        dsu.union(3, 4);

        dsu.find(1);

        dsu.union(4, 5);

        dsu.find(1);

        dsu.find(2);

        // chain: 1->2, 2->3, 3->4, 4->5 => root should be 5 for all
        assertEquals(dsu.find(1), dsu.find(2));
        assertEquals(dsu.find(2), dsu.find(3));
        assertEquals(Integer.valueOf(5), dsu.find(1));
    }

    public void testMultipleDisjointSetsRemainSeparate() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        dsu.union(1, 2);
        dsu.union(3, 4);
        assertNotSame(dsu.find(1), dsu.find(3));
        // ensure internal representatives are stable
        assertEquals(dsu.find(1), dsu.find(2));
        assertEquals(dsu.find(3), dsu.find(4));
    }

    public void testSelfUnionDoesNotBreak() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        dsu.union(5, 5);
        assertEquals(Integer.valueOf(5), dsu.find(5));
    }

    public void testRepeatedUnionsIdempotent() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        dsu.union(6, 7);
        Integer r1 = dsu.find(6);
        dsu.union(6, 7);
        Integer r2 = dsu.find(6);
        assertEquals(r1, r2);
    }

    public void testStringElementsUnionAndFind() {
        DisjointSetUnion<String> dsu = new DisjointSetUnion<>();
        dsu.union("a", "b");
        dsu.union("b", "c");
        assertEquals(dsu.find("a"), dsu.find("b"));
        assertEquals(dsu.find("b"), dsu.find("c"));
        // root should be the last 'y' in chain (c)
        assertEquals("c", dsu.find("a"));
    }

    public void testMixedOperationsSequence() {
        DisjointSetUnion<Integer> dsu = new DisjointSetUnion<>();
        dsu.union(10, 11);
        dsu.union(12, 13);
        dsu.union(11, 12); // connect the two sets: 10->11->12->13 => root 13
        assertEquals(Integer.valueOf(13), dsu.find(10));
        assertEquals(Integer.valueOf(13), dsu.find(11));
        assertEquals(Integer.valueOf(13), dsu.find(12));
        assertEquals(Integer.valueOf(13), dsu.find(13));
    }
}

