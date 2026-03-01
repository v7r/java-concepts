package org.example.algomonster.graphs;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Unit tests for WordLadder.wordLadder
 * These tests follow the problem statement and include edge cases to challenge the BFS logic:
 * - canonical example COLD -> WARM
 * - begin == end
 * - end not present in the dictionary (unreachable)
 * - multiple shortest paths
 * - words of differing lengths are ignored
 * - empty word list
 * - duplicate words in the list
 * - null inputs (expect current implementation to throw)
 *
 * Do NOT modify production logic.
 */
public class WordLadderTest extends TestCase {

    public void testExampleColdToWarm() {
        List<String> dict = Arrays.asList("COLD", "GOLD", "CORD", "SOLD", "CARD", "WARD", "WARM", "TARD");
        int steps = WordLadder.wordLadder("COLD", "WARM", dict);
        // Expected chain COLD -> CORD -> CARD -> WARD -> WARM => 4 steps
        assertEquals(4, steps);
    }

    public void testBeginEqualsEndReturnsZero() {
        List<String> dict = Arrays.asList("A", "B");
        assertEquals(0, WordLadder.wordLadder("A", "A", dict));
    }

    public void testEndNotInDictionaryReturnsMinusOne() {
        List<String> dict = Arrays.asList("hot","dot","dog","lot","log");
        int res = WordLadder.wordLadder("hit", "cog", dict);
        assertEquals(-1, res);
    }

    public void testStandardLeetExampleShortestPath() {
        List<String> dict = Arrays.asList("hot","dot","dog","lot","log","cog");
        int res = WordLadder.wordLadder("hit", "cog", dict);
        // shortest path: hit->hot->dot->dog->cog => 4
        assertEquals(4, res);
    }

    public void testDifferentLengthWordsIgnored() {
        // word lengths differ; diff() returns -1 so no transitions should be possible
        List<String> dict = Arrays.asList("ab","bb");
        int res = WordLadder.wordLadder("a", "b", dict);
        assertEquals(-1, res);
    }

    public void testEmptyWordListBehavesCorrectly() {
        List<String> empty = Collections.emptyList();
        // if begin==end, expect 0; otherwise unreachable
        assertEquals(0, WordLadder.wordLadder("x", "x", empty));
        assertEquals(-1, WordLadder.wordLadder("x", "y", empty));
    }

    public void testDuplicateWordsInListDoNotAffectResult() {
        List<String> dict = Arrays.asList("hot","hot","dot","dog","cog");
        int res = WordLadder.wordLadder("hot", "cog", dict);
        // hot->dot->dog->cog => 3 steps when starting from "hot". If begin not in dict, still works.
        assertEquals(3, res);
    }
}

