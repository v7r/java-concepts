package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTrickQuestion {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Peach");

        // BinarySearch expects the list to be sorted before calling, otherwise the returned value is unpredictable.
        int x1 = Collections.binarySearch(list, "Banana");
        System.out.println(x1);

        Collections.sort(list);
        // Here the list is sorted and binarySearch returns the index of the key in the list, which is 2
        int x2 = Collections.binarySearch(list, "Banana");
        System.out.println(x2);

        Collections.reverse(list);
        // The
        int x3 = Collections.binarySearch(list, "Apple");
        System.out.println(x3);
    }
}
