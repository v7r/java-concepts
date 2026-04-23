package org.example.algomonster.priorityqueue_heap;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ReorganizeStringTest {

    private static Map<Character, Integer> countChars(String s) {
        Map<Character, Integer> m = new HashMap<>();
        if (s == null) return m;
        for (char c : s.toCharArray()) m.put(c, m.getOrDefault(c, 0) + 1);
        return m;
    }

    private static boolean noAdjacentEqual(String s) {
        if (s == null || s.length() < 2) return true;
        for (int i = 1; i < s.length(); i++) if (s.charAt(i) == s.charAt(i - 1)) return false;
        return true;
    }

    @Test
    public void testNullInput_returnsEmpty() {
        String out = ReorganizeString.reorganizeString(null);
        assertNotNull(out);
        assertEquals("", out);
    }

    @Test
    public void testEmptyInput_returnsEmpty() {
        String out = ReorganizeString.reorganizeString("");
        assertEquals("", out);
    }

    @Test
    public void testImpossibleCase_returnsEmpty() {
        // 3 a's and 1 b -> length 4, topCount 3 > (4+1)/2 = 2 -> impossible
        String in = "aaab";
        String out = ReorganizeString.reorganizeString(in);
        assertEquals("", out);
    }

    @Test
    public void testSimpleReorganize() {
        String in = "aab";
        String out = ReorganizeString.reorganizeString(in);
        // Should produce a non-empty string with same characters and no adjacent equals
        assertNotEquals("", out);
        assertEquals(in.length(), out.length());
        assertEquals(countChars(in), countChars(out));
        assertTrue(noAdjacentEqual(out));
    }

    @Test
    public void testAllSameChar_impossible() {
        String in = "aaaaa";
        String out = ReorganizeString.reorganizeString(in);
        assertEquals("", out);
    }

    @Test
    public void testBalancedCounts_valid() {
        String in = "aabbcc";
        String out = ReorganizeString.reorganizeString(in);
        assertNotEquals("", out);
        assertEquals(in.length(), out.length());
        assertEquals(countChars(in), countChars(out));
        assertTrue(noAdjacentEqual(out));
    }

    @Test
    public void testTopCountExactlyLimit_valid() {
        // length 5, topCount 3, (5+1)/2 = 3 => possible
        String in = "aaabb";
        String out = ReorganizeString.reorganizeString(in);
        assertNotEquals("", out);
        assertEquals(in.length(), out.length());
        assertEquals(countChars(in), countChars(out));
        assertTrue(noAdjacentEqual(out));
    }

    @Test
    public void testLongerString_preservesCountsAndNoAdjacency() {
        String in = "aaabbbcccdddeeefff"; // balanced groups
        String out = ReorganizeString.reorganizeString(in);
        assertNotEquals("", out);
        assertEquals(in.length(), out.length());
        assertEquals(countChars(in), countChars(out));
        assertTrue(noAdjacentEqual(out));
    }
}
