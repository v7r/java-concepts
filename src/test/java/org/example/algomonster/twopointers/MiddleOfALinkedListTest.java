package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.List;
import java.util.Optional;

public class MiddleOfALinkedListTest extends TestCase {

    private static MiddleOfALinkedList.Node<Integer> prepLinkedList(List<Integer> list) {
        MiddleOfALinkedList.Node<Integer> head = null;
        MiddleOfALinkedList.Node<Integer> tail = null;
        for (Integer i : list) {
            if (head == null) {
                head = new MiddleOfALinkedList.Node<>(i);
                tail = head;
            } else {
                tail.next = new MiddleOfALinkedList.Node<>(i);
                tail = tail.next;
            }
        }
        return head;
    }
    public void testCase1() {
        List<Integer> input = List.of(0,1,2,3,4);
        Integer mid = MiddleOfALinkedList.middleOfLinkedList(prepLinkedList(input));
        assertEquals("Expected mid is incorrect for the input "+input, Optional.of(2), Optional.of(mid));
    }

    public void testCase2() {
        List<Integer> input = List.of(0,1,2,3,4,5);
        Integer mid = MiddleOfALinkedList.middleOfLinkedList(prepLinkedList(input));
        assertEquals("Expected mid is incorrect for the input "+input, Optional.of(3), Optional.of(mid));
    }

    public void testCase3() {
        List<Integer> input = List.of(1,2,3,4,5,6,7);
        Integer mid = MiddleOfALinkedList.middleOfLinkedList(prepLinkedList(input));
        assertEquals("Expected mid is incorrect for the input "+input, Optional.of(4), Optional.of(mid));
    }
    public void testCase4() {
        List<Integer> input = List.of(1);
        Integer mid = MiddleOfALinkedList.middleOfLinkedList(prepLinkedList(input));
        assertEquals("Expected mid is incorrect for the input "+input, Optional.of(1), Optional.of(mid));
    }
    public void testCase5() {
        List<Integer> input = List.of(1,2);
        Integer mid = MiddleOfALinkedList.middleOfLinkedList(prepLinkedList(input));
        assertEquals("Expected mid is incorrect for the input "+input, Optional.of(2), Optional.of(mid));
    }
}
