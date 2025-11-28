package org.example.algomonster.binarysearch;

import junit.framework.TestCase;

public class CalendarBookingTest extends TestCase {

    public void testNonOverlappingAndBoundary() {
        CalendarBooking cal = new CalendarBooking();
        // basic booking
        assertTrue(cal.book(10, 20));
        // booking that starts exactly at the end should be allowed ([10,20) and [20,30) do not overlap)
        assertTrue(cal.book(20, 30));
        // booking that ends exactly at the start should be allowed
        assertTrue(cal.book(0, 10));
        // booking that overlaps with existing [10,20) should be rejected
        assertFalse(cal.book(15, 25));
    }

    public void testOverlappingVarious() {
        CalendarBooking cal = new CalendarBooking();
        assertTrue(cal.book(10, 20));
        // starts before and ends inside existing
        assertFalse(cal.book(5, 15));
        // exact same interval should be rejected
        assertFalse(cal.book(10, 20));
        // starts inside and ends after existing
        assertFalse(cal.book(15, 25));
        // starts just before existing and ends at start => overlap (since [9,10) ends at 10 which is allowed) -> but [9,11) overlaps
        assertFalse(cal.book(9, 11));
        // non-overlapping after
        assertTrue(cal.book(20, 25));
    }

    public void testInvalidIntervals() {
        CalendarBooking cal = new CalendarBooking();
        // end equals start -> invalid
        assertFalse(cal.book(10, 10));
        // negative start -> invalid per implementation
        assertTrue(cal.book(-5, 5));
    }

    public void testMultipleSequentialBookings() {
        CalendarBooking cal = new CalendarBooking();
        assertTrue(cal.book(5, 10));
        assertTrue(cal.book(15, 20));
        // adjacent block between existing should be accepted
        assertTrue(cal.book(10, 15));
        // overlapping multiple existing intervals should be rejected
        assertFalse(cal.book(9, 16));
    }

    public void testLargeValuesAndStressLike() {
        CalendarBooking cal = new CalendarBooking();
        // create non-overlapping wide intervals
        assertTrue(cal.book(0, 100000));
        // anything overlapping should be rejected
        assertFalse(cal.book(50000, 150000));
        // a booking starting at 100000 is allowed
        assertTrue(cal.book(100000, 200000));
    }
}
