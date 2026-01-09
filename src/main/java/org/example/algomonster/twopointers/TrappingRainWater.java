package org.example.algomonster.twopointers;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array
 * [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 *
 * Example 2:
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 *
 * Constraints:
 *
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 *
 * Ref: <a href="https://leetcode.com/problems/trapping-rain-water/description/">Refer</a>
 */
public class TrappingRainWater {

    //[0,1,0,2,1,0,1,3,2,1,2,1]
    //
    public int trap(int[] heights) {
        int vol = 0;
        int l = 0, r = heights.length - 1;
        int lMax = 0, rMax = 0;
        while (l < r) {
            if (heights[l] < heights[r]) {
                if (heights[l] >= lMax) {
                    lMax = heights[l];
                } else {
                    vol += lMax - heights[l];
                }
                l++;
            } else {
                if (heights[r] >= rMax) {
                    rMax = heights[r];
                } else {
                    vol += rMax - heights[r];
                }
                r--;
            }
        }
        return vol;
    }

    public int trapAttempt3(int[] heights) {
        int vol = 0;
        int lMax = 0, rMax = 0, l = 0, r = heights.length - 1;
        while (l < r) {
            if (heights[l] <= heights[r]) {
                if (heights[l] > lMax) {
                    lMax = heights[l];
                } else {
                    vol += lMax - heights[l];
                }
                l++;
            } else {
                if (heights[r] > rMax) {
                    rMax = heights[r];
                } else {
                    vol += rMax - heights[r];
                }
                r--;
            }
        }
        return vol;
    }

    //[4,2,0,3,2,5]
    //
    public int trapAttempt2(int[] heights) {
        int vol = 0;
        int l = 0, r = heights.length - 1;
        int countedLow = 0;
        while (l < r) {
            if (heights[l]==0) l++;
            if (heights[r]==0) r--;
            int baseLevel = Math.min(heights[l],heights[r]);
            for (int i = l; i<=r; i++) {
                if (heights[i] >= baseLevel) continue;
                vol += baseLevel - heights[i];
                if (heights[i] < countedLow) {
                    vol -= countedLow;
                }
            }
            countedLow = Math.max(countedLow,baseLevel);
            l++; r--;
        }
        return vol;
    }

    //[4,2,0,3,2,5]
    //[0,2,4,1,2,0]
    //[0,1,0,2,1,0,1,3,2,1,2,1]
    //
    public int trapAttempt1(int[] heights) {
        int vol = 0;
        int l = 0, r=1;
        while (r < heights.length) {
            if (heights[r] >= heights[l]) {
                int lowestHeight = Math.min(heights[l],heights[r]);
                while(l<r) {
                    vol += lowestHeight - heights[l];
                    l++;
                }
            }
            r++;
        }
        return vol;
    }
}
