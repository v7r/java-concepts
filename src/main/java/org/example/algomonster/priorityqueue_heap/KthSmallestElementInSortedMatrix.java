package org.example.algomonster.priorityqueue_heap;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest
 * element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * Example:
 * Input:
 * matrix = [
 *   [ 1,  5,  9],
 *   [10, 11, 13],
 *   [12, 13, 15]
 * ],
 * k = 8,
 * Output: 13
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ n^2. You may also assume that 1 <= n <= 1000.
 */
public class KthSmallestElementInSortedMatrix {
    public static int kthSmallest(List<List<Integer>> matrix, int k) {
        //return solutionAlgoMonster(matrix, k);
        return solutionSelfMade(matrix, k);
    }
    // 1, 2, 3
    // 2, 4, 5
    // 4, 5, 6

    // 1, 1, 1, 1
    // 1, 0, 0, 0
    // 1, 0, 0, 0
    // 1, 0, 0, 0
    private static int solutionSelfMade(final List<List<Integer>> matrix, int k) {
        PriorityQueue<List<Integer>> q = new PriorityQueue<>((i1, i2) ->
                Integer.compare(matrix.get(i1.get(0)-1).get(i1.get(1)-1), matrix.get(i2.get(0)-1).get(i2.get(1)-1))
        );
        int n = matrix.size();
        boolean[][] isProcessed = new boolean[n+1][n+1];
        Arrays.fill(isProcessed[0], true);
        for (int i = 0; i < n+1; i++) {
            isProcessed[i][0] = true;
        }
        int i = 0;
        int r = 1, c = 1;
        q.offer(List.of(r,c));
        while (i < k - 1) {
            List<Integer> item = q.poll();
            r = item.get(0); c = item.get(1);
            isProcessed[r][c] = true;
            System.out.printf("Processing (%d,%d)->(%d)\n", r, c, matrix.get(r-1).get(c-1));
            // if the right cell's top is processed then the right cell is probably the next lowest.
            if (c+1 <= n && r-1 >= 0 && isProcessed[r-1][c+1]) {
                q.add(List.of(r, c+1));
            }
            // if the below cell's left is processed then the below cell is probably the next lowest.
            if ((c-1) >= 0 && r+1 <= n && isProcessed[r+1][c-1]) {
                q.add(List.of(r+1, c));
            }
            i++;
        }
        List<Integer> item = q.poll();
        return matrix.get(item.get(0)-1).get(item.get(1)-1);
    }

    private static int solutionAlgoMonster(List<List<Integer>> matrix, int k) {
        int n = matrix.size();
        // Keeps track of row and column numbers of items in the heap
        // The smallest item represented by the row and column number is added to the top
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (a, b) -> Integer.compare(matrix.get(a[0]).get(a[1]), matrix.get(b[0]).get(b[1])));
        heap.offer(new int[] {0, 0});
        // Keeps track of the top of each row that is not processed
        int[] columnTop = new int[n];
        // Keeps track of the first number each row not processed
        int[] rowFirst = new int[n];
        // Repeat the process k - 1 times.

        while (k > 1) {
            k--;
            int[] coords = heap.poll();
            int row = coords[0], column = coords[1];
            rowFirst[row] = column + 1;
            // Add the item on the right to the heap if everything above it is processed
            if (column + 1 < n && columnTop[column + 1] == row) {
                heap.offer(new int[] {row, column + 1});
            }
            columnTop[column] = row + 1;
            // Add the item below it to the heap if everything before it is processed
            if (row + 1 < n && rowFirst[row + 1] == column) {
                heap.offer(new int[] {row + 1, column});
            }
        }
        int[] resCoords = heap.poll();
        return matrix.get(resCoords[0]).get(resCoords[1]);
    }
}
