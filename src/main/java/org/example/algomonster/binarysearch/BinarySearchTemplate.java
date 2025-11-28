package org.example.algomonster.binarysearch;

import java.util.List;

/**
 * Monotonic function:
 * A monotonic function is a function that is either non-decreasing or non-increasing. Given x1 and x2 where x1 > x2,
 * we should have f(x1) >= f(x2).
 *
 * A sorted array is monotonic because the value increases or stays the same as the index increases.
 *
 * If f(x) only contains Boolean values True and False and we think of true as 1 and false as 0, then a sorted Boolean
 * array would consist of consecutive 0s and then consecutive 1s. For example, FFFFTTTTT.
 *
 *
 * Feasible function:
 * The precondition for binary search is to find a monotonic function f(x) that returns either True or False. Then the
 * problem becomes Find the First True in a Sorted Boolean Array that we already know how to solve using binary search.
 * We will call the function feasible to signify whether the element at the current index is feasible (True) or not (False)
 * to meet the problem constraints.
 *
 *  [ ......... ]
 *   apply feasible(i) for each item in the array above.
 *  [f,f,f,t,t,t]
 *
 * Now, the problem has become finding the feasible function and then mechanically applying the template.
 *
 * In the Find the First True in a Sorted Boolean Array problem, the feasible function is simply arr[mid] == True.
 * Finding the feasible function can be trickier in other problems. Let's look at a few examples.
 *
 *
 */
public class BinarySearchTemplate {
    public static int binarySearch(List<Integer> arr, int target) {
        int left = 0;
        int right = arr.size() - 1;
        int firstTrueIndex = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (feasible(mid)) {
                firstTrueIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return firstTrueIndex;
    }

    // Write your feasible function for the problem statement.
    private static boolean feasible(int mid) {
        return false;
    }
}
