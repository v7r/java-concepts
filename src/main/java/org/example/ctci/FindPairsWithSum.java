package org.example.ctci;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Page # 72 in ctci book
 *
 *  Find all pairs with sum k within an array (assuming
 * all distinct elements)
 *
 * Example
 *   [1,2,3,4,5,6,7,8,9,10] k=5
 *   (1,4),(2,3),(3,2),(1,4),(4,1)
 *
 *   [8, 10, 20, 3, 13, 11, 6, 14, 17, 16] k=30
 *   (10,20), (20,10), (13,17), (14,16), (17,13), (16,14)
 */
public class FindPairsWithSum {
    public static void main(String[] args) {

        Integer[] a = new Integer[] {8, 10, 20, 3, 13, 11, 6, 14, 17, 16};
        int k = 30;

        // Solution 1; time complexity O(n log n)
        long startTime = System.nanoTime();
        List<Integer> intArray = Arrays.asList(a);
        for (int i = 0; i < intArray.size(); i++) {
            Integer le = intArray.get(i);
            Integer re = le < k ? k - le : -1;
            if (intArray.contains(re)) {
                System.out.println(" "+le+", "+re);
            }
        }
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Solution one elapsed time(nano seconds): "+estimatedTime);

        // Solution 2; Time complexity O(n) where n is the length of intMap.
        startTime = System.nanoTime();
        Map<Integer, Integer> intMap = Stream.of(a).collect(Collectors.toMap(Function.identity(), Function.identity()));
        intMap.entrySet().stream().forEach(e -> {
            Integer le = e.getKey();
            Integer re = le < k ? k - le : -1;
            if (intMap.get(re) != null) {
                System.out.println(" "+le+", "+re);
            }
        });
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("Solution two elapsed time(nano seconds): "+estimatedTime);
    }
}
