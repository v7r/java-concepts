package org.example.algomonster.backtracking.D_AggregationAndMemoization;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Tests for MinNumberOfCoinsToMakeupAGiveValue.coinChange
 * These tests cover canonical examples from the problem statement and also
 * edge/pathological cases (empty coins, zero coin) intended to challenge
 * incorrect recursion/memoization logic in the implementation.
 * Do NOT modify production logic; tests assert current behavior including
 * thrown errors for pathological inputs.
 */
public class MinNumberOfCoinsToMakeupAGiveValueTest extends TestCase {

    public void testExampleStandardCase() {
        List<Integer> coins = Arrays.asList(1, 2, 5);
        int amount = 11;
        int result = MinNumberOfCoinsToMakeupAGiveValue.coinChange(coins, amount);
        assertEquals(3, result); // 5+5+1
    }

    public void testNoSolutionReturnsMinusOne() {
        List<Integer> coins = Arrays.asList(2);
        int amount = 3;
        int result = MinNumberOfCoinsToMakeupAGiveValue.coinChange(coins, amount);
        assertEquals(-1, result);
    }

    public void testZeroAmountReturnsZeroCoins() {
        List<Integer> coins = Arrays.asList(1, 2, 5);
        int amount = 0;
        int result = MinNumberOfCoinsToMakeupAGiveValue.coinChange(coins, amount);
        assertEquals(0, result);
    }

    public void testEmptyCoinsWithNonZeroAmountReturnsMinusOne() {
        List<Integer> coins = Collections.emptyList();
        int amount = 7;
        int result = MinNumberOfCoinsToMakeupAGiveValue.coinChange(coins, amount);
        assertEquals(-1, result);
    }

    public void testDifferentOrderOfCoinsProducesSameAnswer() {
        List<Integer> coins = Arrays.asList(5, 2, 1);
        int amount = 11;
        int result = MinNumberOfCoinsToMakeupAGiveValue.coinChange(coins, amount);
        assertEquals(3, result);
    }

    public void testDuplicateCoinValuesAreHandled() {
        List<Integer> coins = Arrays.asList(1, 1, 2);
        int amount = 4;
        int result = MinNumberOfCoinsToMakeupAGiveValue.coinChange(coins, amount);
        // optimal is 2+2 or 1+1+1+1 = 2 coins
        assertEquals(2, result);
    }

    public void testZeroCoinLeadsToStackOverflow() {
        // The production logic doesn't guard against coin value 0. Adding a 0 coin will
        // cause recursion that doesn't increase the sum and therefore leads to infinite recursion
        // (StackOverflowError). This test documents that pathological input behavior.
        List<Integer> coins = Arrays.asList(0, 1);
        int amount = 2;
        try {
            MinNumberOfCoinsToMakeupAGiveValue.coinChange(coins, amount);
            fail("Expected StackOverflowError when coin list contains 0 (pathological input)");
        } catch (StackOverflowError expected) {
            // expected under current implementation
        }
    }
}

