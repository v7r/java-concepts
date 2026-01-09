package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

public class FindAnagramsTest extends TestCase {

    public void testExample1() {
        FindAnagrams solver = new FindAnagrams();
        List<Integer> expected = Arrays.asList(0,6);
        assertEquals(expected, solver.findAnagrams("cbaebabacd", "abc"));
    }

    public void testExample2() {
        FindAnagrams solver = new FindAnagrams();
        List<Integer> expected = Arrays.asList(0,1,2);
        assertEquals(expected, solver.findAnagrams("abab", "ab"));
    }

    public void testSingleCharP() {
        FindAnagrams solver = new FindAnagrams();
        List<Integer> expected = Arrays.asList(0,1,2,3,4);
        assertEquals(expected, solver.findAnagrams("aaaaa", "a"));
    }

    public void testDuplicateLettersP() {
        FindAnagrams solver = new FindAnagrams();
        // p has duplicates: "aa" -> should find overlapping windows
        List<Integer> expected = Arrays.asList(0,1,2);
        assertEquals(expected, solver.findAnagrams("aaaa", "aa"));
    }

    public void testNoAnagrams() {
        FindAnagrams solver = new FindAnagrams();
        List<Integer> expected = Arrays.asList();
        assertEquals(expected, solver.findAnagrams("abcdefg", "hij"));
    }

    public void testPLongerThanS() {
        FindAnagrams solver = new FindAnagrams();
        List<Integer> expected = Arrays.asList();
        assertEquals(expected, solver.findAnagrams("ab", "abc"));
    }

    public void testOverlappingAnagrams() {
        FindAnagrams solver = new FindAnagrams();
        List<Integer> expected = Arrays.asList(0,1,2,3,4);
        assertEquals(expected, solver.findAnagrams("ababab", "ab"));
    }

    public void testRepeatedCharsComplex() {
        FindAnagrams solver = new FindAnagrams();
        // challenge counts that go to zero and new chars enter window
        List<Integer> expected = Arrays.asList(0,1);
        assertEquals(expected, solver.findAnagrams("aabacbba", "aab"));
    }

    public void testAnagramsWithExtrasInS() {
        FindAnagrams solver = new FindAnagrams();
        // many potential anagrams; ensure correct positions only
        List<Integer> expected = Arrays.asList(2,3,4);
        // substrings of length 3: s="xxabcbaay" -> windows: "xxa","xab","abc","bcb","cba","baa","aay"
        // anagrams of "bca" appear at indices 2("abc"),3("bcb" is not),4("cba") and maybe 5? but using expected 2,4
        // to challenge implementation we craft a string where only 2,3,4 expected: we'll use "zzabcaabc"
        assertEquals(Arrays.asList(2,3,6), solver.findAnagrams("zzabcaabc", "abc"));
    }
}

