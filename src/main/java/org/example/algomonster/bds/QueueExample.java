package org.example.algomonster.bds;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        queueExample();
        stackExample();
    }

    public static void queueExample() {
        Queue<String> q = new ArrayDeque<>();
        System.out.println("Adding element to queue: Apple");
        q.add("Apple");
        System.out.println("Adding element to queue: Banana");
        q.add("Banana");
        System.out.println("Adding element to queue: Orange");
        q.add("Orange");

        System.out.println("Peek first element "+ q.peek());
        System.out.println("Pool first element "+ q.poll());
        System.out.println("Pee1k current first element "+ q.peek());
        System.out.println("");
    }

    public static void stackExample() {
        Deque<String> stack = new ArrayDeque<>();
        System.out.println("Adding element to stack: Apple");
        stack.addFirst("Apple");
        System.out.println("Adding element to stack: Banana");
        stack.addFirst("Banana");
        System.out.println("Adding element to stack: Orange");
        stack.addFirst("Orange");

        System.out.println("Top element " + stack.removeFirst());
        System.out.println("Top element " + stack.removeFirst());
        System.out.println("Top element " + stack.removeFirst());
    }
}
