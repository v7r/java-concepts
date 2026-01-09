package org.example.algomonster.twopointers;

import junit.framework.TestCase;

public class LinkedListCycleTest extends TestCase {

    // helper to create a linked list from values; returns head
    private LinkedListCycle.Node<Integer> buildList(int[] vals) {
        if (vals == null || vals.length == 0) return null;
        LinkedListCycle.Node<Integer> head = new LinkedListCycle.Node<>(vals[0]);
        LinkedListCycle.Node<Integer> cur = head;
        for (int i = 1; i < vals.length; i++) {
            cur.next = new LinkedListCycle.Node<>(vals[i]);
            cur = cur.next;
        }
        return head;
    }

    // helper to get node at index (0-based)
    private LinkedListCycle.Node<Integer> getNode(LinkedListCycle.Node<Integer> head, int idx) {
        LinkedListCycle.Node<Integer> cur = head;
        int i = 0;
        while (cur != null && i < idx) {
            cur = cur.next;
            i++;
        }
        return cur;
    }

    public void testSingleNodeNoCycle() {
        LinkedListCycle.Node<Integer> n = new LinkedListCycle.Node<>(1);
        assertFalse(LinkedListCycle.hasCycle(n));
    }

    public void testSingleNodeSelfCycle() {
        LinkedListCycle.Node<Integer> n = new LinkedListCycle.Node<>(1);
        n.next = n;
        assertTrue(LinkedListCycle.hasCycle(n));
    }

    public void testSimpleNoCycle() {
        LinkedListCycle.Node<Integer> head = buildList(new int[]{1,2,3,4});
        assertFalse(LinkedListCycle.hasCycle(head));
    }

    public void testSimpleCycleAtMiddle() {
        LinkedListCycle.Node<Integer> head = buildList(new int[]{1,2,3,4});
        // create cycle: node 4 -> node 2
        LinkedListCycle.Node<Integer> node2 = getNode(head, 1);
        LinkedListCycle.Node<Integer> node4 = getNode(head, 3);
        node4.next = node2;
        assertTrue(LinkedListCycle.hasCycle(head));
    }

    public void testTwoNodesNoCycle() {
        LinkedListCycle.Node<Integer> head = buildList(new int[]{1,2});
        assertFalse(LinkedListCycle.hasCycle(head));
    }

    public void testTwoNodesCycle() {
        LinkedListCycle.Node<Integer> n1 = new LinkedListCycle.Node<>(1);
        LinkedListCycle.Node<Integer> n2 = new LinkedListCycle.Node<>(2);
        n1.next = n2;
        n2.next = n1;
        assertTrue(LinkedListCycle.hasCycle(n1));
    }

    public void testDuplicateValuesNoCycle() {
        // two nodes with same value but different nodes -> should not be detected as cycle
        LinkedListCycle.Node<Integer> n1 = new LinkedListCycle.Node<>(1);
        LinkedListCycle.Node<Integer> n2 = new LinkedListCycle.Node<>(2);
        LinkedListCycle.Node<Integer> n3 = new LinkedListCycle.Node<>(1); // same value as n1
        n1.next = n2; n2.next = n3;
        assertFalse(LinkedListCycle.hasCycle(n1));
    }

    public void testDuplicateValuesWithCycle() {
        LinkedListCycle.Node<Integer> n1 = new LinkedListCycle.Node<>(1);
        LinkedListCycle.Node<Integer> n2 = new LinkedListCycle.Node<>(2);
        LinkedListCycle.Node<Integer> n3 = new LinkedListCycle.Node<>(1);
        LinkedListCycle.Node<Integer> n4 = new LinkedListCycle.Node<>(3);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n2; // cycle back to n2
        assertTrue(LinkedListCycle.hasCycle(n1));
    }

    public void testLongListNoCycle() {
        int[] vals = new int[50];
        for (int i = 0; i < vals.length; i++) vals[i] = i % 10; // repeating values
        LinkedListCycle.Node<Integer> head = buildList(vals);
        assertTrue(LinkedListCycle.hasCycle(head));
    }

    public void testLongListWithLateCycle() {
        int[] vals = new int[50];
        for (int i = 0; i < vals.length; i++) vals[i] = i;
        LinkedListCycle.Node<Integer> head = buildList(vals);
        LinkedListCycle.Node<Integer> tail = getNode(head, vals.length - 1);
        LinkedListCycle.Node<Integer> mid = getNode(head, 10);
        tail.next = mid;
        assertTrue(LinkedListCycle.hasCycle(head));
    }
}

