package org.example.ctci;

import java.util.*;

/**
 * Page # 95 in Ctci book
 *
 * Sum Lists: You have two numbers represented by a linked list, where each node contains a single
 * digit. The digits are stored in reverse order, such that the 1's digit is at the head of the list.
 * Write a function that adds the two numbers and returns the sum as a linked list.
 * EXAMPLE
 * Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295.
 * Output: 2 -> 1 -> 9. That is,912.
 *
 * FOLLOW UP
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
 * Output: 9 -> 1 -> 2.That is, 912.
 */
public class SumLists {
    public static void main(String[] args) {
        Queue<Integer> n1 = new LinkedList();
        Queue<Integer> n2 = new LinkedList();
        n1.addAll(Arrays.asList(7, 1, 6));
        n2.addAll(Arrays.asList(5, 9, 2));

        /**
         * Following is my solution. Refer the book for other solutions.
         *
         * 'Followup' can be implemented by changing the order of recursive function call
         */

        Integer num1 = asNumber(n1.iterator(), 0);
        Integer num2 = asNumber(n2.iterator(), 0);
        Queue<Integer> r = new LinkedList<>();
        numberToList(num1 + num2, r);
        System.out.println(""+r);
    }

    private static Queue<Integer> numberToList(int i, Queue r) {
        if (i == 0) return r;
        r.offer(i % 10);
        numberToList((int)i / 10 , r);
        return r;
    }

    private static Integer asNumber(Iterator<Integer> it, int k) {
        if (!it.hasNext()) {
            return 0;
        }
        Integer num = it.next();
        return (int)Math.pow(10, k) * num + asNumber(it, ++k);
    }
}
