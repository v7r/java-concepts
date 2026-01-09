package org.example.algomonster.twopointers;

import junit.framework.TestCase;

public class BackspaceStringCompareTest extends TestCase {

    public void testExamplesFromProblem() {
        BackspaceStringCompare solver = new BackspaceStringCompare();
        assertTrue(solver.backspaceCompare("ab#c", "ad#c"));
        assertTrue(solver.backspaceCompare("ab##", "c#d#"));
        assertFalse(solver.backspaceCompare("a#c", "b"));
    }

    public void testBothEmptyStrings() {
        BackspaceStringCompare solver = new BackspaceStringCompare();
        assertTrue(solver.backspaceCompare("", ""));
    }

    public void testAllBackspacesReduceToEmpty() {
        BackspaceStringCompare solver = new BackspaceStringCompare();
        assertTrue(solver.backspaceCompare("###", ""));
        assertTrue(solver.backspaceCompare("a#b#c#", ""));
    }

    public void testBackspacesBeyondStart() {
        BackspaceStringCompare solver = new BackspaceStringCompare();
        // backspaces exceed number of chars before them, final is just "b"
        assertTrue(solver.backspaceCompare("a###b", "b"));
        // different shapes but same final
        assertTrue(solver.backspaceCompare("ab##", "c#d#"));
    }

    public void testConsecutiveBackspacesAndPatterns() {
        BackspaceStringCompare solver = new BackspaceStringCompare();
        assertTrue(solver.backspaceCompare("xywrrmp", "xywrrmp"));
        // remove last char
        assertTrue(solver.backspaceCompare("xywrrmp#", "xywrrm"));
        // tricky pattern where one side deletes a character the other keeps
        assertTrue(solver.backspaceCompare("xywrrmu#p", "xywrrmp"));
    }

    public void testInterleavedBackspacesProduceDifferentResults() {
        BackspaceStringCompare solver = new BackspaceStringCompare();
        assertFalse(solver.backspaceCompare("bxj##tw", "bxj###tw"));
    }

    public void testDifferentLengthsAfterProcessing() {
        BackspaceStringCompare solver = new BackspaceStringCompare();
        assertTrue(solver.backspaceCompare("a##c", "#a#c"));
        assertTrue(solver.backspaceCompare("abc#d", "abzz##d"));
    }

    public void testTrailingAndLeadingBackspaces() {
        BackspaceStringCompare solver = new BackspaceStringCompare();
        assertTrue(solver.backspaceCompare("#a#b", "b"));
        assertTrue(solver.backspaceCompare("#a#b", "#b"));
    }

    public void testNoBackspacesDifferentStrings() {
        BackspaceStringCompare solver = new BackspaceStringCompare();
        assertFalse(solver.backspaceCompare("abc", "abd"));
        assertTrue(solver.backspaceCompare("abc", "abc"));
    }

    public void testLongSequenceEdgeCases() {
        BackspaceStringCompare solver = new BackspaceStringCompare();
        String s = "a" + new String(new char[50]).replace('\0', '#') + "b"; // many backspaces
        // s reduces to "b"
        assertTrue(solver.backspaceCompare(s, "b"));

        String t = new String(new char[100]).replace('\0', '#'); // all backspaces
        assertTrue(solver.backspaceCompare(t, ""));
    }
}

