package org.example.algomonster.binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement a SnapshotArray that supports the following interface:
 *
 * SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 * Example 1:
 *
 * Input: ["SnapshotArray","set","snap","set","get"]
 *
 * [[3],[0,5],[],[0,6],[0,0]]
 *
 * Output: [null,null,0,null,5]
 *
 * Explanation:
 *
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 *
 * snapshotArr.set(0,5);  // Set array[0] = 5
 *
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 *
 * snapshotArr.set(0,6);
 *
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 * Constraints:
 *
 * 1 <= length <= 5 * 104
 * 0 <= index < length
 * 0 <= val <= 109
 * 0 <= snap_id < (the total number of times we call snap())
 * At most 5 * 104 calls will be made to set, snap, and get.
 *
 *
 * Solution hint:
 *
 * Instead of copying the entire array each time we take a snapshot, we wish to only store the changes to each index.
 * we keep track of an array histories of size n where histories[i] is an array that stores the history of the changes
 * of array[i]'s values. We use the pair (snap_id, value) to indicate that we have updated array[i]=value at the time
 * we took the snapshot with the given snap_id.
 *
 * So when implementing get(snap_id) for index i, we will do binary search on histories[i] to find the index pos in
 * histories[i] that contains the most recent value up to the time we took the snapshot with the given snap_id.
 *
 */
class SnapshotArray {
    // A list of lists, where each inner list stores the history of changes
    // for a specific index in the format: [snapId, value].
    // List<int[]> represents the history for a single index.
    private List<List<int[]>> histories;

    // The current snapshot ID. It increments every time snap() is called.
    private int snapId;

    /**
     * Initializes the data structure with an array of length 'length'.
     * @param length The size of the array.
     */
    public SnapshotArray(int length) {
        // Initialize the main list to hold histories for 'length' indices.
        this.histories = new ArrayList<>(length);

        // Initialize the history for each index.
        // The default initial state is: at snapId -1 (before any snap), the value is 0.
        // We use snapId -1 or 0 for the initial state depending on the context,
        // but here we align with the Python logic using a snapId that precedes the first one (0).
        for (int i = 0; i < length; i++) {
            List<int[]> history = new ArrayList<>();
            // Store the initial value: [snapId, value].
            // In the Python code, the initial list is [[-1, 0]], which serves
            // as the default value before snap 0. Let's use [0, 0]
            // or equivalent depending on whether you expect get(index, 0) to return 0.
            // Sticking closer to the Python's intent for the initial default state:
            // Since the first real snap ID is 0, let's use a change logged at snap ID 0 with value 0
            // or an initial value that the first set() will overwrite.

            // Following the Python logic exactly: initialize with an entry representing
            // the default value (0) that applies from before snap ID 0.
            history.add(new int[]{-1, 0});
            this.histories.add(history);
        }

        this.snapId = 0;
    }

    /**
     * Sets the value at the given index to val.
     * This operation is logged with the current snapId.
     * @param index The array index to set.
     * @param val The value to set.
     */
    public void set(int index, int val) {
        // Append the change: [current snapId, new value] to the history of the index.
        // Note: The value change is associated with the *current* snapId,
        // meaning it applies to all subsequent snap IDs until the next set() call.
        this.histories.get(index).add(new int[]{this.snapId, val});
    }

    /**
     * Takes a snapshot of the array and returns the current snapId.
     * @return The snapId of the snapshot just taken.
     */
    public int snap() {
        // The ID of the current state *before* incrementing.
        int currentSnapId = this.snapId;
        this.snapId++;
        return currentSnapId;
    }

    /**
     * Retrieves the value at the given index that was active
     * in the snapshot with the given snapId.
     * @param index The array index.
     * @param snap_id The snapshot ID to retrieve from.
     * @return The value at index in the given snapId.
     */
    public int get(int index, int snap_id) {
        List<int[]> history = this.histories.get(index);

        // --- Binary Search (Lower Bound) Implementation ---
        // We look for the latest change (max snapId) that is less than or equal to the requested snap_id.
        int left = 0;
        int right = history.size() - 1;
        int pos = -1; // Index of the found entry in the history list.

        while (left <= right) {
            int mid = left + (right - left) / 2; // Safer way to calculate midpoint

            // history.get(mid)[0] is the snapId of the change at 'mid' position.
            if (history.get(mid)[0] <= snap_id) {
                // This change applies at or before the requested snap_id.
                // It's a potential answer, so we store it and search in the right half
                // for an even later change that still meets the criteria.
                pos = mid;
                left = mid + 1;
            } else {
                // This change occurred *after* the requested snap_id, so we ignore it
                // and search in the left half (older changes).
                right = mid - 1;
            }
        }

        // pos holds the index of the change entry [snapId, value] that was
        // the last one to occur at or before the requested snap_id.
        // history.get(pos)[1] is the value associated with that change.
        // Due to the initial entry [-1, 0], pos will never be -1.
        return history.get(pos)[1];
    }
}