package org.example.algomonster.binarysearch;

import junit.framework.TestCase;

public class SnapshotArrayTest extends TestCase {

    public void testExampleScenario() {
        SnapshotArray snapshotArr = new SnapshotArray(3);
        snapshotArr.set(0, 5);
        int snap0 = snapshotArr.snap();
        assertEquals(0, snap0);
        snapshotArr.set(0, 6);
        assertEquals(5, snapshotArr.get(0, 0));
    }

    public void testNoSetBeforeSnap() {
        SnapshotArray arr = new SnapshotArray(2);
        int s0 = arr.snap();
        assertEquals(0, s0);
        // No sets were done, so each index should be 0 at snap 0
        assertEquals(0, arr.get(0, 0));
        assertEquals(0, arr.get(1, 0));
    }

    public void testMultipleSnapsWithoutChange() {
        SnapshotArray arr = new SnapshotArray(1);
        arr.set(0, 5);
        int s0 = arr.snap();
        int s1 = arr.snap();
        assertEquals(0, s0);
        assertEquals(1, s1);
        // Value should persist across subsequent snaps until changed
        assertEquals(5, arr.get(0, 0));
        assertEquals(5, arr.get(0, 1));
    }

    public void testMultipleSetsSameSnap() {
        SnapshotArray arr = new SnapshotArray(1);
        arr.set(0, 5);
        arr.set(0, 6);
        int s0 = arr.snap();
        assertEquals(0, s0);
        // The last set in the same snap should be the value for that snap
        assertEquals(6, arr.get(0, 0));
    }

    public void testMultipleIndicesIndependence() {
        SnapshotArray arr = new SnapshotArray(3);
        arr.set(0, 1);
        arr.set(1, 2);
        int s0 = arr.snap();
        // change index 0 after the snapshot
        arr.set(0, 3);
        int s1 = arr.snap();

        assertEquals(1, arr.get(0, s0));
        assertEquals(2, arr.get(1, s0));
        assertEquals(3, arr.get(0, s1));
        // index 1 remains unchanged for s1 as it wasn't modified
        assertEquals(2, arr.get(1, s1));
    }

    public void testRepeatedSetsAcrossSnaps() {
        SnapshotArray arr = new SnapshotArray(1);
        arr.set(0, 7);
        int s0 = arr.snap(); // 0
        arr.set(0, 9);
        int s1 = arr.snap(); // 1
        arr.set(0, 11);
        int s2 = arr.snap(); // 2

        assertEquals(7, arr.get(0, s0));
        assertEquals(9, arr.get(0, s1));
        assertEquals(11, arr.get(0, s2));
    }

    public void testGetUsesMostRecentChangeBeforeOrAtSnap() {
        SnapshotArray arr = new SnapshotArray(1);
        // initial value implicit
        arr.set(0, 1);      // snapId 0
        arr.set(0, 2);      // snapId 0 (overwrites within same snap)
        int s0 = arr.snap();

        arr.set(0, 3);      // snapId 1
        arr.set(0, 4);      // snapId 1
        int s1 = arr.snap();

        // s0 should see the last value set before snap() returned 0 -> 2
        assertEquals(2, arr.get(0, s0));
        // s1 should see the last value set before snap() returned 1 -> 4
        assertEquals(4, arr.get(0, s1));
    }
}

