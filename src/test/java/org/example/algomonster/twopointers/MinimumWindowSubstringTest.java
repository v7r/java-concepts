package org.example.algomonster.twopointers;

import junit.framework.TestCase;

public class MinimumWindowSubstringTest extends TestCase {

    public void testExampleFromComment() {
        String original = "cdbaebaecd";
        String check = "abc";
        // expected from problem comment
        assertEquals("baec", MinimumWindowSubstring.getMinimumWindow(original, check));
    }

    public void testLeetCodeClassic() {
        String original = "ADOBECODEBANC";
        String check = "ABC";
        // Case sensitive; expected shortest window that contains A,B,C in that string
        assertEquals("BANC", MinimumWindowSubstring.getMinimumWindow(original, check));
    }

    public void testMultipleEqualLengthWindows_chooseLexicographicallySmallest() {
        // Two valid windows of the same (minimal) length exist: "acb" and "bac".
        // Lexicographically smaller is "acb" which should be returned.
        String original = "zzacbzzbaczz";
        String check = "abc";
        assertEquals("acb", MinimumWindowSubstring.getMinimumWindow(original, check));
    }

    public void testNoWindowExists() {
        String original = "a";
        String check = "aa";
        assertEquals("", MinimumWindowSubstring.getMinimumWindow(original, check));
    }

    public void testExactMatchWholeString() {
        String original = "abc";
        String check = "abc";
        assertEquals("abc", MinimumWindowSubstring.getMinimumWindow(original, check));
    }

    public void testRepeatedCharactersInOriginalAndCheck() {
        String original = "aaabcbcdaa";
        String check = "aba"; // requires two 'a' and one 'b'
        // shortest window containing two 'a' and one 'b' is "aab" starting near index 1
        assertEquals("aab", MinimumWindowSubstring.getMinimumWindow(original, check));
    }
}

