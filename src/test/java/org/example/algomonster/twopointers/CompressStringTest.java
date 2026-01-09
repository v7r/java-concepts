package org.example.algomonster.twopointers;

import junit.framework.TestCase;

public class CompressStringTest extends TestCase {

    public void testExample1() {
        char[] chars = new char[]{'a','a','b','b','c','c','c'};
        assertEquals(6, new CompressString().compress(chars));
    }

    public void testExample2_singleChar() {
        char[] chars = new char[]{'a'};
        assertEquals(1, new CompressString().compress(chars));
    }

    public void testExample3_multiDigitCount() {
        // a followed by 12 b's -> compressed "ab12" length 4
        char[] chars = new char[1 + 12];
        chars[0] = 'a';
        for (int i = 1; i < chars.length; i++) chars[i] = 'b';
        assertEquals(4, new CompressString().compress(chars));
    }

    public void testAllUniqueChars() {
        char[] chars = new char[]{'a','b','c','d','e'};
        assertEquals(5, new CompressString().compress(chars));
    }

    public void testAllSameTen() {
        char[] chars = new char[10];
        for (int i = 0; i < 10; i++) chars[i] = 'x';
        // compressed "x10" length 3
        assertEquals(3, new CompressString().compress(chars));
    }

    public void testCountBoundaryNineTen() {
        char[] nine = new char[9];
        for (int i = 0; i < 9; i++) nine[i] = 'z';
        assertEquals(2, new CompressString().compress(nine)); // "z9"

        char[] ten = new char[10];
        for (int i = 0; i < 10; i++) ten[i] = 'z';
        assertEquals(3, new CompressString().compress(ten)); // "z10"
    }

    public void testDigitsAsChars() {
        char[] chars = new char[]{'1','1','1'};
        assertEquals(2, new CompressString().compress(chars)); // "13"
    }

    public void testSymbolsAndMixed() {
        char[] chars = new char[]{'!','!','!','@','@'};
        // "!3@2" length 4
        assertEquals(4, new CompressString().compress(chars));
    }

    public void testVeryLargeGroup() {
        // 2000 same char should compress to 'a2000' => length = 1 + 4 = 5
        char[] chars = new char[2000];
        for (int i = 0; i < chars.length; i++) chars[i] = 'a';
        assertEquals(5, new CompressString().compress(chars));
    }
}

