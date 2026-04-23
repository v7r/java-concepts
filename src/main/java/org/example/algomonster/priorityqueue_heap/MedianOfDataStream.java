package org.example.algomonster.priorityqueue_heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a stream of numbers, find the median number at any given time (accurate to 1 decimal place).
 *
 * Example:
 *
 * add_number(1)
 * add_number(2)
 * add_number(3)
 * get_median() == 2.0
 * add_number(4)
 * get_median() == 2.5
 *
 */
public class MedianOfDataStream {
    private Queue<Integer> minHeap = new PriorityQueue<>();
    private Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    public void addNum(int num) {

        //7,5,2,get,4,get,6,get
        // maxHeap: 5
        // minHeap: 7
        if (maxHeap.size() == 0 || num < maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        // balance
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }
}
