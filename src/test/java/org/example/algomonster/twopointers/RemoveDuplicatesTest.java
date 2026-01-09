package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RemoveDuplicatesTest extends TestCase {

    public void testTrickyCase() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 0, 1, 1, 1, 2, 2, 200, 200, 300, 3000));
        int i = RemoveDuplicates.removeDuplicates(input);
        assertEquals(6, i);
    }
}
