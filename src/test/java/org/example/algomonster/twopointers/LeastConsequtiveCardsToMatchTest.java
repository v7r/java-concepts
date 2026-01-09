package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class LeastConsequtiveCardsToMatchTest extends TestCase {

    public void testExampleFromComment() {
        List<Integer> cards = Arrays.asList(3, 4, 2, 3, 4, 7);
        // expected: pick [3,4,2,3] -> length 4
        assertEquals(4, LeastConsequtiveCardsToMatch.leastConsecutiveCardsToMatch(cards));
    }

    public void testAdjacentPair() {
        List<Integer> cards = Arrays.asList(1, 1);
        assertEquals(2, LeastConsequtiveCardsToMatch.leastConsecutiveCardsToMatch(cards));
    }

    public void testSinglePairSeparatedByOne() {
        List<Integer> cards = Arrays.asList(5, 1, 5);
        // two 5s at indices 0 and 2 -> need to pick indices [0..2] => length 3
        assertEquals(3, LeastConsequtiveCardsToMatch.leastConsecutiveCardsToMatch(cards));
    }

    public void testMultiplePairsChooseShortest() {
        List<Integer> cards = Arrays.asList(1, 2, 1, 3, 2);
        // pairs: 1s at (0,2) -> len3, 2s at (1,4) -> len4 -> expect 3
        assertEquals(3, LeastConsequtiveCardsToMatch.leastConsecutiveCardsToMatch(cards));
    }

    public void testNoPairsReturnsMinusOne() {
        List<Integer> cards = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(-1, LeastConsequtiveCardsToMatch.leastConsecutiveCardsToMatch(cards));
    }

    public void testAllSameCards() {
        List<Integer> cards = Arrays.asList(7,7,7,7);
        // nearest pair adjacent -> length 2
        assertEquals(2, LeastConsequtiveCardsToMatch.leastConsecutiveCardsToMatch(cards));
    }

    public void testPairAtEnds() {
        List<Integer> cards = Arrays.asList(9, 1, 2, 3, 9);
        // only pair is at indices 0 and 4 -> length 5
        assertEquals(5, LeastConsequtiveCardsToMatch.leastConsecutiveCardsToMatch(cards));
    }

    public void testMultipleValuesVariousDistances() {
        List<Integer> cards = Arrays.asList(8, 6, 7, 6, 8);
        // 6s: (1,3)->len3; 8s: (0,4)->len5 -> expect 3
        assertEquals(3, LeastConsequtiveCardsToMatch.leastConsecutiveCardsToMatch(cards));
    }

    public void testSingleElementList() {
        List<Integer> cards = Collections.singletonList(42);
        assertEquals(-1, LeastConsequtiveCardsToMatch.leastConsecutiveCardsToMatch(cards));
    }

    public void testZerosAndDuplicates() {
        List<Integer> cards = Arrays.asList(0,1,0,2,3,0);
        // zeros at 0,2,5 -> closest pair (0,2)->len3
        assertEquals(3, LeastConsequtiveCardsToMatch.leastConsecutiveCardsToMatch(cards));
    }
}
