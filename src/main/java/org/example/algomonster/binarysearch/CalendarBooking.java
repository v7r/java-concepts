package org.example.algomonster.binarysearch;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause
 * a double booking. A double booking happens when two events have some non-empty intersection (i.e., some moment is
 * common to both events.).
 *
 * The event can be represented as a pair of integers startTime and endTime that represents a booking on the half-open
 * interval [startTime, endTime), the range of real numbers x such that startTime <= x < endTime.
 *
 * Implement the MyCalendar class:
 *
 * MyCalendar() Initializes the calendar object.
 * boolean book(int startTime, int endTime) Returns true if the event can be added to the calendar successfully without
 * causing a double booking. Otherwise, return false and do not add the event to the calendar.
 */
public class CalendarBooking {

    List<int[]> calendar = new ArrayList<>();

    // [10,20], [20,30] [30,40] add [15,25]
    //[10,20] add [5,15]
    //[10,20] add [20,25]
    public boolean book(int startTime, int endTime) {
        if (startTime == endTime) return false;
        int idx = calendar.size();
        int start = 0, end = calendar.size() - 1;
        while (start <= end) {
            int mid = ( start + end ) / 2;
            if (calendar.get(mid)[0] > startTime) {
                idx = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if ((idx > 0 && this.calendar.get(idx-1)[1] > startTime)
            || (idx < this.calendar.size() && this.calendar.get(idx)[0] < endTime ))
            return false;

        calendar.add(idx, new int[]{startTime,endTime});
        return true;
    }

    // Commented code is AI generated.
    /*private final TreeMap<Integer, Integer> bookings;

    public CalendarBooking() {
        this.bookings = new TreeMap<>();
    }

    *//**
     * Book an event on [startTime, endTime). Returns true if no overlap and the event is added; false otherwise.
     *//*
    public boolean book(int startTime, int endTime) {
        if (startTime < 0 || endTime <= startTime) {
            // invalid interval per problem constraints; treat as not bookable
            return false;
        }
        // previous booking with largest start <= startTime
        Integer prevKey = bookings.floorKey(startTime);
        if (prevKey != null) {
            Integer prevEnd = bookings.get(prevKey);
            // overlap if prevEnd > startTime (since intervals are [start, end))
            if (prevEnd > startTime) {
                return false;
            }
        }
        // next booking with smallest start >= startTime
        Integer nextKey = bookings.ceilingKey(startTime);
        if (nextKey != null) {
            // overlap if nextKey < endTime
            if (nextKey < endTime) {
                return false;
            }
        }
        bookings.put(startTime, endTime);
        return true;
    }*/
}
