package org.example.algomonster.binarysearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareRootEstimationTest {

    private final SquareRootEstimation sut = new SquareRootEstimation();

    @Test
    void perfectSquareSixteen() {
        assertEquals(4, sut.findSqRootEstimate(16));
    }

    @Test
    void nonPerfectSquareEight() {
        assertEquals(2, sut.findSqRootEstimate(8));
    }

    @Test
    void zeroReturnsZero() {
        assertEquals(0, sut.findSqRootEstimate(0));
    }

    @Test
    void oneReturnsOne() {
        assertEquals(1, sut.findSqRootEstimate(1));
    }

    @Test
    void twoReturnsOne() {
        assertEquals(1, sut.findSqRootEstimate(2));
    }

    @Test
    void threeReturnsOne() {
        assertEquals(1, sut.findSqRootEstimate(3));
    }

    @Test
    void fifteenReturnsThree() {
        assertEquals(3, sut.findSqRootEstimate(15));
    }

    @Test
    void largeButSafeValue() {
        // keep input <= 46340 to avoid integer overflow inside the implementation
        int input = 46340;
        // floor(sqrt(46340)) == 215 because 215*215=46225 and 216*216=46656
        assertEquals(215, sut.findSqRootEstimate(input));
    }

    @Test
    void tenThousandPerfectSquare() {
        assertEquals(100, sut.findSqRootEstimate(10_000));
    }
}

