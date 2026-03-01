package org.example.algomonster.graphs;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests for FloodFill.floodFill
 * These tests follow the problem statement and include edge cases to challenge the implementation:
 * - canonical example in the class comment
 * - single-cell image
 * - replacement equals target (should leave image effectively unchanged)
 * - only connected region is filled (other equal-colored regions untouched)
 * - starting at a border/corner
 *
 * Do NOT modify production logic.
 */
public class FloodFillTest extends TestCase {

    public void testExampleFromComment() {
        List<List<Integer>> image = arrayToList(new int[][]{
                {0,1,3,4,1},
                {3,8,8,3,3},
                {6,7,8,8,3},
                {12,2,8,9,1},
                {12,3,1,3,2}
        });

        List<List<Integer>> expected = arrayToList(new int[][]{
                {0,1,3,4,1},
                {3,9,9,3,3},
                {6,7,9,9,3},
                {12,2,9,9,1},
                {12,3,1,3,2}
        });

        List<List<Integer>> out = FloodFill.floodFill(2,2,9,image);
        assertEquals(expected, out);
    }

    public void testSinglePixelImage() {
        List<List<Integer>> image = arrayToList(new int[][]{{5}});
        List<List<Integer>> expected = arrayToList(new int[][]{{7}});
        List<List<Integer>> out = FloodFill.floodFill(0,0,7,image);
        assertEquals(expected, out);
    }

    public void testReplacementEqualsTargetLeavesImageSame() {
        List<List<Integer>> image = arrayToList(new int[][]{
                {1,1,0},
                {1,0,0},
                {0,0,1}
        });
        // target at (0,0) is 1; replacement also 1
        List<List<Integer>> before = deepCopy(image);
        List<List<Integer>> out = FloodFill.floodFill(0,0,1,image);
        // Since replacement == target, the final image should be equal to the original content-wise
        assertEquals(before, out);
    }

    public void testOnlyConnectedRegionFilled() {
        List<List<Integer>> image = arrayToList(new int[][]{
                {1,1,0},
                {1,0,0},
                {0,0,1}
        });
        List<List<Integer>> expected = arrayToList(new int[][]{
                {2,2,0},
                {2,0,0},
                {0,0,1}
        });
        List<List<Integer>> out = FloodFill.floodFill(0,0,2,image);
        assertEquals(expected, out);
    }

    public void testStartAtCornerBottomRight() {
        List<List<Integer>> image = arrayToList(new int[][]{
                {1,2,2},
                {2,2,2},
                {2,2,3}
        });
        // start at bottom-right (2,2) value 3 -> only that cell
        List<List<Integer>> expected = arrayToList(new int[][]{
                {1,2,2},
                {2,2,2},
                {2,2,9}
        });
        List<List<Integer>> out = FloodFill.floodFill(2,2,9,image);
        assertEquals(expected, out);
    }

    // helpers
    private List<List<Integer>> arrayToList(int[][] arr) {
        List<List<Integer>> out = new ArrayList<>();
        for (int[] row : arr) {
            List<Integer> r = new ArrayList<>();
            for (int v : row) r.add(v);
            out.add(r);
        }
        return out;
    }

    private List<List<Integer>> deepCopy(List<List<Integer>> src) {
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> r : src) {
            out.add(new ArrayList<>(r));
        }
        return out;
    }
}

