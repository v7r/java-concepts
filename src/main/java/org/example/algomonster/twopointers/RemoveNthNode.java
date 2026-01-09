package org.example.algomonster.twopointers;

/**
 * Problem
 * Given the head of a linked list and an integer n, remove the n-th node from the end of the list and return the head
 * of the modified list.
 *
 * Input:
 *
 * head: the head node of a singly linked list
 * n: an integer representing the position from the end (1-indexed)
 * Output:
 *
 * Return the head of the modified linked list
 * Constraints:
 *
 * The list has at least 1 node
 * n is a valid position from the end (1 <= n <= length of list)
 * Examples:
 *
 * Example 1:
 *
 * Input: head = [1, 2, 3, 4], n = 1
 * Output: [1, 2, 3]
 * Explanation: Remove the 1st node from the end (value 4)
 * Example 2:
 *
 * Input: head = [1, 2, 3, 4], n = 2
 * Output: [1, 2, 4]
 * Explanation: Remove the 2nd node from the end (value 3)
 * Example 3:
 *
 * Input: head = [1, 2, 3, 4], n = 4
 * Output: [2, 3, 4]
 * Explanation: Remove the 4th node from the end (the head, value 1)
 */
public class RemoveNthNode {
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
    // Recommended in algo.monster
    public static Node<Integer> removeNthFromEnd(Node<Integer> head, int n) {
        Node<Integer> l = new Node<Integer>(0, head);
        Node<Integer> r = head;
        for(int i=1;i<n;i++) {
            r = r.next;
        }
        if (r.next == null) return l.next.next;
        while(r.next != null) {
            r = r.next;
            l = l.next;
        }
        l.next = l.next.next;
        return head;
    }

    // Mysolution
    public static Node<Integer> removeNthFromEndMySolution(Node<Integer> head, int n) {
        Node<Integer> l = head, r = head;
        for (int i=1;i<n;i++) {
            r = r.next;
        }
        if (r.next == null) head = head.next;
        while (r.next != null) {
            r = r.next;
            if (r.next == null) {
                l.next = l.next.next;
            } else {
                l = l.next;
            }
        }
        return head;
    }
}
