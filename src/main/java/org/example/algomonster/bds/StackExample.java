package org.example.algomonster.bds;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {

        // follows Last In First Out (LIFO)
        Stack<String> stack = new Stack<>();

        // push element
        stack.push("Apple");
        stack.push("Banana");
        stack.push("Orange");

        // peek will be the element that is read but not removed.
        System.out.println("Last element is "+stack.peek());

        // pop will return last element and also removes from stack.
        System.out.println("Pop element is "+ stack.pop());

        // peek to see the next element
        System.out.println("Last element is "+ stack.peek());
    }
}
