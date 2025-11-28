package org.example.algomonster.binarysearch;

import java.util.List;

public class VanillaBinarySearch {
    public static int binarySearch(List<Integer> arr, int target) {
        int left = 0;
        int right = arr.size() - 1;

        while (left <= right) { // <= here because left and right could point to the same element, < would miss it
            int mid = left + (right - left) / 2; // use `(right - left) / 2` to prevent `left + right` potential overflow
            // found target, return its index
            if (arr.get(mid) == target) return mid;
            if (arr.get(mid) < target) {
                // middle less than target, discard left half by making left search boundary `mid + 1`
                left = mid + 1;
            } else {
                // middle greater than target, discard right half by making right search boundary `mid - 1`
                right = mid - 1;
            }
        }
        return -1; // if we get here we didn't hit above return so we didn't find target
    }
}
