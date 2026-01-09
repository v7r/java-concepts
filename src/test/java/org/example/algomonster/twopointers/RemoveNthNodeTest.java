package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class RemoveNthNodeTest extends TestCase {

    private RemoveNthNode.Node<Integer> fromArray(int[] a) {
        if (a == null || a.length == 0) return null;
        RemoveNthNode.Node<Integer> head = new RemoveNthNode.Node<>(a[0]);
        RemoveNthNode.Node<Integer> cur = head;
        for (int i = 1; i < a.length; i++) {
            cur.next = new RemoveNthNode.Node<>(a[i]);
            cur = cur.next;
        }
        return head;
    }

    private List<Integer> toList(RemoveNthNode.Node<Integer> head) {
        List<Integer> out = new ArrayList<>();
        RemoveNthNode.Node<Integer> cur = head;
        while (cur != null) {
            out.add(cur.val);
            cur = cur.next;
        }
        return out;
    }

    public void testRemoveTail() {
        RemoveNthNode.Node<Integer> head = fromArray(new int[]{1,2,3,4});
        RemoveNthNode.Node<Integer> res = RemoveNthNode.removeNthFromEnd(head, 1);
        assertEquals(java.util.Arrays.asList(1,2,3), toList(res));
    }

    public void testRemoveSecondFromEnd() {
        RemoveNthNode.Node<Integer> head = fromArray(new int[]{1,2,3,4});
        RemoveNthNode.Node<Integer> res = RemoveNthNode.removeNthFromEnd(head, 2);
        assertEquals(java.util.Arrays.asList(1,2,4), toList(res));
    }

    public void testRemoveHead() {
        RemoveNthNode.Node<Integer> head = fromArray(new int[]{1,2,3,4});
        RemoveNthNode.Node<Integer> res = RemoveNthNode.removeNthFromEnd(head, 4);
        assertEquals(java.util.Arrays.asList(2,3,4), toList(res));
    }

    public void testSingleElement() {
        RemoveNthNode.Node<Integer> head = fromArray(new int[]{1});
        RemoveNthNode.Node<Integer> res = RemoveNthNode.removeNthFromEnd(head, 1);
        assertEquals(new ArrayList<Integer>(), toList(res));
    }

    public void testTwoElementsRemoveHead() {
        RemoveNthNode.Node<Integer> head = fromArray(new int[]{1,2});
        RemoveNthNode.Node<Integer> res = RemoveNthNode.removeNthFromEnd(head, 2);
        assertEquals(Collections.singletonList(2), toList(res));
    }

    public void testTwoElementsRemoveTail() {
        RemoveNthNode.Node<Integer> head = fromArray(new int[]{1,2});
        RemoveNthNode.Node<Integer> res = RemoveNthNode.removeNthFromEnd(head, 1);
        assertEquals(Collections.singletonList(1), toList(res));
    }

    public void testRemoveMiddleInOddLength() {
        RemoveNthNode.Node<Integer> head = fromArray(new int[]{1,2,3,4,5});
        RemoveNthNode.Node<Integer> res = RemoveNthNode.removeNthFromEnd(head, 3);
        assertEquals(java.util.Arrays.asList(1,2,4,5), toList(res));
    }

    public void testRemoveNthEqualsLength() {
        RemoveNthNode.Node<Integer> head = fromArray(new int[]{10,20,30});
        RemoveNthNode.Node<Integer> res = RemoveNthNode.removeNthFromEnd(head, 3);
        assertEquals(java.util.Arrays.asList(20,30), toList(res));
    }

}
