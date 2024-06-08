package org.example.ctci;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Page # 224 in ctci book
 *
 * Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list.
 */
public class ReturnKthToTheLast {

    public static void main(String[] args) {
        List<String> l = new LinkedList<String>();
        l.add("Bob");
        l.add("Alice");
        l.add("Monica");
        l.add("Veronica");
        l.add("Kushka");

        int k = 3;
        StringBuffer kthEelement = new StringBuffer();
        Iterator<String> i = l.iterator();

        findKthElement(i, k, kthEelement);
        System.out.println(k + "(Kth) element to last is "+kthEelement);
    }

    static int findKthElement(Iterator<String> i, int k, StringBuffer kthElement) {
        if (!i.hasNext()) {
            return 0;
        }

        String currentElement = i.next();
        int currentLevel = 1 + findKthElement(i, k, kthElement);
        if (currentLevel == k) {
            kthElement.setLength(0);
            kthElement.append(currentElement);
        }
        return currentLevel;
    }
}
