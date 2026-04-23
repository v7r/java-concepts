package org.example.algomonster.priorityqueue_heap_practice;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RearrangeStringKDistanceApartTest {

    private Map<Character, Integer> countChars(String s) {
        Map<Character, Integer> m = new HashMap<>();
        if (s == null) return m;
        for (char c : s.toCharArray()) m.put(c, m.getOrDefault(c, 0) + 1);
        return m;
    }

    private boolean validSpacing(String out, int k) {
        if (out == null) return false;
        if (k <= 1) return true; // no constraint
        int n = out.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n && j - i < k; j++) {
                if (out.charAt(i) == out.charAt(j)) return false;
            }
        }
        return true;
    }

    @Test
    public void testExample1_aabbcc_k3() {
        String s = "aabbcc";
        int k = 3;
        String out = RearrangeStringKDistanceApart.rearrange(s, k);
        assertNotNull(out);
        assertEquals(s.length(), out.length());
        assertEquals(countChars(s), countChars(out));
        assertTrue(validSpacing(out, k));
    }

    @Test
    public void testExample2_aaabc_k3_impossible() {
        String s = "aaabc";
        int k = 3;
        String out = RearrangeStringKDistanceApart.rearrange(s, k);
        assertEquals("", out);
    }

    @Test
    public void testExample3_aaadbbcc_k2() {
        String s = "aaadbbcc";
        int k = 2;
        String out = RearrangeStringKDistanceApart.rearrange(s, k);
        assertNotNull(out);
        assertEquals(s.length(), out.length());
        assertEquals(countChars(s), countChars(out));
        assertTrue(validSpacing(out, k));
    }

    @Test
    public void testKZero_noRestriction() {
        String s = "aaaa";
        int k = 0;
        String out = RearrangeStringKDistanceApart.rearrange(s, k);
        assertNotNull(out);
        assertEquals(s.length(), out.length());
        assertEquals(countChars(s), countChars(out));
        // no spacing requirement when k <= 1
    }

    @Test
    public void testSingleCharManyRepeats_impossible() {
        String s = "aaaaa";
        int k = 2;
        String out = RearrangeStringKDistanceApart.rearrange(s, k);
        assertEquals("", out);
    }

    @Test
    public void testEmptyInput_returnsEmpty() {
        String s = "";
        String out = RearrangeStringKDistanceApart.rearrange(s, 2);
        assertEquals("", out);
    }

    @Test
    public void testNullInput_throws() {
        assertThrows(NullPointerException.class, () -> RearrangeStringKDistanceApart.rearrange(null, 2));
    }

    @Test
    public void testTrickyOrdering_case() {
        // crafted to stress limiter queue ordering; should be possible
        String s = "aaabbbcc"; // balanced enough to be rearranged for k=2
        int k = 2;
        String out = RearrangeStringKDistanceApart.rearrange(s, k);
        assertNotNull(out);
        assertEquals(s.length(), out.length());
        assertEquals(countChars(s), countChars(out));
        assertTrue(validSpacing(out, k));
    }
}

