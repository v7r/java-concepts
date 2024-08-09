package org.example.ctci;

import java.util.*;

/**
 * Page # 95 in ctci book
 *
 * Palindrome: Implement a function to check if a linked list is a palindrome.
 */
public class PalindrameList {
    public static void main(String[] args) {

        /**
         * This is my solution for other solutions refer the book.
         *
         * My solution is more clear and elegant than the recursive solution in the book :)
         */

        Queue<Integer> input = new LinkedList();
        //input 1
        input.addAll(Arrays.asList(0));
        System.out.println("is "+input+" palindrome ? ");
        System.out.println(checkPalendrome(input, new Stack<Integer>(),0, input.size()));

        //input 1
        input.addAll(Arrays.asList(0,1,2,1,0));
        System.out.println("is "+input+" palindrome ? ");
        System.out.println(checkPalendrome(input, new Stack<Integer>(),0, input.size()));
        //input 2
        input = new LinkedList();
        input.addAll(Arrays.asList(0,1,2,2,1,0));
        System.out.println("is "+input+" palindrome ? ");
        System.out.println(checkPalendrome(input, new Stack<Integer>(),0, input.size()));

        //input 3
        input = new LinkedList();
        input.addAll(Arrays.asList(1,1,2,2,1,0));
        System.out.println("is "+input+" palindrome ? ");
        System.out.println(checkPalendrome(input, new Stack<Integer>(),0, input.size()));
    }

    private static boolean checkPalendrome(Queue<Integer> i, Stack<Integer> mi, int counter, int iSize) {
        Integer idata = i.poll();
        if (idata == null) {
            return true;
        }
        boolean isMatch = true;
        if (counter < iSize/2) {
            mi.push(idata);
        } else if (iSize % 2 == 1 && counter == iSize / 2) {
            // if odd size list and for the middle element
            isMatch = true;
        } else {
            Integer imData = mi.pop();
            if (idata != imData) isMatch = false;
        }
        return isMatch && checkPalendrome(i, mi, ++counter, iSize);
    }
}
