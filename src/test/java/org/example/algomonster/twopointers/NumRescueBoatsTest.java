package org.example.algomonster.twopointers;

import junit.framework.TestCase;
import java.util.Arrays;

public class NumRescueBoatsTest extends TestCase {

    private int expectedBoatsGreedy(int[] people, int limit) {
        int[] copy = Arrays.copyOf(people, people.length);
        Arrays.sort(copy);
        int l = 0, r = copy.length - 1;
        int boats = 0;
        while (l <= r) {
            if (l == r) {
                boats++;
                break;
            }
            if (copy[l] + copy[r] <= limit) {
                l++;
                r--;
            } else {
                r--;
            }
            boats++;
        }
        return boats;
    }

    public void testExamplePairs() {
        NumRescueBoats solver = new NumRescueBoats();
        assertEquals(1, solver.numRescueBoats(new int[]{1, 2}, 3));
    }

    public void testExampleFromComment_case2() {
        NumRescueBoats solver = new NumRescueBoats();
        assertEquals(3, solver.numRescueBoats(new int[]{3, 2, 2, 1}, 3));
    }

    public void testExampleFromComment_case3() {
        NumRescueBoats solver = new NumRescueBoats();
        assertEquals(4, solver.numRescueBoats(new int[]{3, 5, 3, 4}, 5));
    }

    public void testSinglePerson() {
        NumRescueBoats solver = new NumRescueBoats();
        assertEquals(1, solver.numRescueBoats(new int[]{1}, 2));
    }

    public void testAllSameOddCount() {
        NumRescueBoats solver = new NumRescueBoats();
        assertEquals(2, solver.numRescueBoats(new int[]{1, 1, 1}, 2));
    }

    public void testAllSameEvenCount() {
        NumRescueBoats solver = new NumRescueBoats();
        assertEquals(2, solver.numRescueBoats(new int[]{1, 1, 1, 1}, 2));
    }

    public void testHeavyEqualToLimitEach() {
        NumRescueBoats solver = new NumRescueBoats();
        assertEquals(3, solver.numRescueBoats(new int[]{3, 3, 3}, 3));
    }

    public void testMixedCases_againstOracle_smallSets() {
        NumRescueBoats solver = new NumRescueBoats();

        int[][] inputs = {
                {3, 2, 2, 1},
                {1, 2, 1, 2},
                {2, 3, 4, 3, 1},
                {5, 1, 4, 2, 1}
        };
        int[] limits = {3, 3, 5, 5};

        for (int i = 0; i < inputs.length; i++) {
            int[] arr = Arrays.copyOf(inputs[i], inputs[i].length);
            int limit = limits[i];
            int expected = expectedBoatsGreedy(arr, limit);
            assertEquals("Mismatch for input " + Arrays.toString(inputs[i]) + " limit " + limit,
                    expected, solver.numRescueBoats(arr, limit));
        }
    }
}

