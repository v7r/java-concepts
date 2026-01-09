package org.example.algomonster.twopointers;

/**
 * Find the middle node of a linked list.
 *
 * Input: 0 1 2 3 4
 *
 * Output: 2
 *
 * If the number of nodes is even, then return the second middle node.
 *
 * Input: 0 1 2 3 4 5
 *
 * Output: 3
 */
public class MiddleOfALinkedList {
    public static class Node<T> {
        public T val;
        public Node<T> next;

        public Node(T val) {
            this(val, null);
        }

        public Node(T val, Node<T> next) {
            this.val = val;
            this.next = next;
        }
    }
    // 0 1 2 3 4  ouput:
    // 0 1 2 3 4 5
    public static int middleOfLinkedList(Node<Integer> head) {
        Node<Integer> mid = head;
        Node<Integer> right = head;
        while (right != null && right.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        return mid.val;
    }
}
