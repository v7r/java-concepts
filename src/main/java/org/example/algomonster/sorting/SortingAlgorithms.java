package org.example.algomonster.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingAlgorithms {
    public static void main(String[] args) {
        //List<Integer> input = Arrays.asList(7,3,4,2,1,6,3,8,9,10,11,12);
        List<Integer> input = Arrays.asList(1,2,3,4,5);

        System.out.println("Bubble Sort: " + bubbleSort(new ArrayList<Integer>(input)));
        System.out.println("Insertion Sort: " + insertionSort(new ArrayList<Integer>(input)));
        System.out.println("Selection Sort: " + selectionSort(new ArrayList<Integer>(input)));
        System.out.println("Merge Sort: " + mergeSort(new ArrayList<Integer>(input)));
        System.out.println("Quick Sort: " + quickSort(new ArrayList<Integer>(input)));

    }

    /**
     * This logic is copied from https://algo.monster/problems/advanced_sorting
     *
     * @param unsortedList
     * @param start
     * @param end
     */
    public static void quickSortRecursive(List<Integer> unsortedList, int start, int end) {
        // If segment is 1 or 0, it's sorted
        if (end - start <= 1)
            return;

        // Using last element as the pivot
        int pivot = unsortedList.get(end - 1);
        int startPtr = start, endPtr = end - 1;

        // Partitioning process
        while (startPtr < endPtr) {
            // Find the next element from the left that is larger than the pivot
            while (unsortedList.get(startPtr) < pivot && startPtr < endPtr) {
                startPtr++;
            }

            // Find the next element from the right that is smaller than or equal to the pivot
            while (unsortedList.get(endPtr) >= pivot && startPtr < endPtr) {
                endPtr--;
            }

            // Swap if pointers haven't met
            if (startPtr != endPtr) {
                int temp = unsortedList.get(startPtr);
                unsortedList.set(startPtr, unsortedList.get(endPtr));
                unsortedList.set(endPtr, temp);
            }
        }

        // Place pivot in its final position
        int temp = unsortedList.get(startPtr);
        unsortedList.set(startPtr, unsortedList.get(end - 1));
        unsortedList.set(end - 1, temp);

        // Sort left and right of the pivot
        quickSortRecursive(unsortedList, start, startPtr);
        quickSortRecursive(unsortedList, startPtr + 1, end);
    }

    public static List<Integer> quickSort(List<Integer> unsortedList) {
        quickSortRecursive(unsortedList, 0, unsortedList.size());
        return unsortedList;
    }

    public static List<Integer> mergeSort(List<Integer> unsortedList) {
        if (unsortedList.size() <= 1) {
            return unsortedList;
        }

        // split
        int split = (int)Math.ceil(unsortedList.size() / 2.0);
        List<Integer> sortedA = mergeSort(unsortedList.subList(0, split));
        List<Integer> sortedB = mergeSort(unsortedList.subList(split, unsortedList.size()));

        // then merge sorted list;
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < sortedA.size() && j < sortedB.size()) {
            if (sortedA.get(i) < sortedB.get(j)) {
                merged.add(sortedA.get(i));
                i++;
            } else {
                merged.add(sortedB.get(j));
                j++;
            }
        }

        while (i < sortedA.size()) {
            merged.add(sortedA.get(i));
            i++;
        }

        while (j < sortedB.size()) {
            merged.add(sortedB.get(j));
            j++;
        }

        return merged;
    }

    public static List<Integer> selectionSort(List<Integer> unsortedList) {
        Integer[] a = unsortedList.toArray(new Integer[]{});
        for (int i = 0; i < a.length; i++) {
            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = i; j < a.length; j++) {
                if (a[j] < minValue) {
                    minValue = a[j];
                    minIndex = j;
                }
            }
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
        return Arrays.asList(a);
    }

    public static List<Integer> insertionSort(List<Integer> unsortedList) {
        Integer[] a = unsortedList.toArray(new Integer[]{});
        // [4,2,1,5,3]
        for (int i = 1; i < a.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] > a[j+1]) {
                    int temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                } else {
                    break;
                }
            }
        }
        return Arrays.asList(a);
    }

    public static List<Integer> bubbleSort(List<Integer> unsortedList) {
        // WRITE YOUR BRILLIANT CODE HERE
        // [5,1,6,3,3]
        Integer[] a = unsortedList.toArray(new Integer[]{});
        for (int i = a.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j+1]) {
                    int temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                }
            }
        }
        return Arrays.asList(a);
    }
}
