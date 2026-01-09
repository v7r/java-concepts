package org.example.algomonster.twopointers;

import junit.framework.TestCase;

public class TrappingRainWaterTest extends TestCase {

    public void testExample1() {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater solver = new TrappingRainWater();
        assertEquals(6, solver.trap(height));
    }

    public void testExample2() {
        int[] height = {4,2,0,3,2,5};
        TrappingRainWater solver = new TrappingRainWater();
        assertEquals(9, solver.trap(height));
    }

    public void testSingleElement() {
        int[] height = {5};
        TrappingRainWater solver = new TrappingRainWater();
        assertEquals(0, solver.trap(height));
    }

    public void testTwoElements() {
        int[] height = {5,2};
        TrappingRainWater solver = new TrappingRainWater();
        assertEquals(0, solver.trap(height));
    }

    public void testMonotonicIncreasing() {
        int[] height = {0,1,2,3,4,5};
        TrappingRainWater solver = new TrappingRainWater();
        assertEquals(0, solver.trap(height));
    }

    public void testMonotonicDecreasing() {
        int[] height = {5,4,3,2,1,0};
        TrappingRainWater solver = new TrappingRainWater();
        assertEquals(0, solver.trap(height));
    }

    public void testAllEqualHeights() {
        int[] height = {3,3,3,3,3};
        TrappingRainWater solver = new TrappingRainWater();
        assertEquals(0, solver.trap(height));
    }

    public void testSingleDeepHole() {
        int[] height = {5,0,5};
        TrappingRainWater solver = new TrappingRainWater();
        assertEquals(5, solver.trap(height));
    }

    public void testMultiplePits() {
        int[] height = {5,0,3,0,5};
        TrappingRainWater solver = new TrappingRainWater();
        assertEquals(12, solver.trap(height));
    }

    public void testAlternatingSmallPits() {
        int[] height = {2,0,2,0,2};
        TrappingRainWater solver = new TrappingRainWater();
        assertEquals(4, solver.trap(height));
    }
}

