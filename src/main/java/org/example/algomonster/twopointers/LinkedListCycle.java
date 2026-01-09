package org.example.algomonster.twopointers;

import java.util.Objects;

public class LinkedListCycle {
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

    public static boolean hasCycle(Node<Integer> nodes) {
        Node<Integer> s = nodes;
        Node<Integer> f = nodes;
        while (s.next != null ) {
            s = s.next;
            if (f.next != null && f.next.next != null) f = f.next.next;
            else break;
            if (Objects.equals(s.val, f.val)) return true;
        }
        return false;
    }
}
