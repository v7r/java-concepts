package org.example.algomonster.priorityqueue_heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MedianOfDataStreamTest {

    @Test
    public void testOddCountMedian() {
        MedianOfDataStream ds = new MedianOfDataStream();
        ds.addNum(1);
        ds.addNum(2);
        ds.addNum(3);
        assertEquals(2.0, ds.getMedian(), 0.0001);
    }

    @Test
    public void testEvenCountMedian() {
        MedianOfDataStream ds = new MedianOfDataStream();
        ds.addNum(1);
        ds.addNum(2);
        ds.addNum(3);
        ds.addNum(4);
        assertEquals(2.5, ds.getMedian(), 0.0001);
    }

    @Test
    public void testOrderDoesNotMatter() {
        MedianOfDataStream ds = new MedianOfDataStream();
        ds.addNum(3);
        ds.addNum(1);
        ds.addNum(4);
        ds.addNum(2);
        assertEquals(2.5, ds.getMedian(), 0.0001);
    }

    @Test
    public void testNegativeNumbers() {
        MedianOfDataStream ds = new MedianOfDataStream();
        ds.addNum(-3);
        ds.addNum(-1);
        ds.addNum(-2);
        assertEquals(-2.0, ds.getMedian(), 0.0001);
    }

    @Test
    public void testRepeatedNumbers() {
        MedianOfDataStream ds = new MedianOfDataStream();
        ds.addNum(5);
        ds.addNum(5);
        ds.addNum(5);
        assertEquals(5.0, ds.getMedian(), 0.0001);
    }

    @Test
    public void testInterleavedAddAndGet_mediansMatchExpectedSequence() {
        MedianOfDataStream ds = new MedianOfDataStream();
        ds.addNum(7);
        assertEquals(7.0, ds.getMedian(), 0.0001);

        ds.addNum(5);
        assertEquals(6.0, ds.getMedian(), 0.0001);

        ds.addNum(2);
        assertEquals(5.0, ds.getMedian(), 0.0001);

        ds.addNum(4);
        assertEquals(4.5, ds.getMedian(), 0.0001);

        ds.addNum(6);
        assertEquals(5.0, ds.getMedian(), 0.0001);
    }

    @Test
    public void testGetMedianBeforeAdd_throwsOrHandles() {
        MedianOfDataStream ds = new MedianOfDataStream();
        // Current implementation will throw NullPointerException when peeking empty heaps.
        assertThrows(NullPointerException.class, ds::getMedian);
    }
}

