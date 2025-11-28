package org.example.algomonster.binarysearch;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeakOfAMountainTest {

    @Test
    public void testPeakMiddle() {
        List<Integer> arr = Arrays.asList(0,1,2,3,2,1,0);
        int expected = 3;
        assertEquals(expected, PeakOfAMountain.peakOfMountainArray(arr));
    }

    @Test
    public void testPeakNearStart() {
        List<Integer> arr = Arrays.asList(0,5,4,3,2);
        int expected = 1;
        assertEquals(expected, PeakOfAMountain.peakOfMountainArray(arr));
    }

    @Test
    public void testPeakNearEnd() {
        List<Integer> arr = Arrays.asList(0,1,2,10,9,8,7);
        int expected = 3;
        assertEquals(expected, PeakOfAMountain.peakOfMountainArray(arr));
    }

    @Test
    public void testMinimumLengthThree() {
        List<Integer> arr = Arrays.asList(1,3,2);
        int expected = 1;
        assertEquals(expected, PeakOfAMountain.peakOfMountainArray(arr));
    }


}

