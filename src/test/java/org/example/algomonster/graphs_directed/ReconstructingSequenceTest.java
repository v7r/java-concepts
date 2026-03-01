package org.example.algomonster.graphs_directed;

import junit.framework.TestCase;

import java.util.*;

/**
 * Tests for ReconstructingSequence.sequenceReconstruction
 * These tests exercise canonical examples and edge cases that should reveal problems
 * in construction of in/out degree maps, node handling when nodes are missing from seqs,
 * duplicate edges, and branching where uniqueness must be enforced.
 * Do NOT modify production logic.
 */
public class ReconstructingSequenceTest extends TestCase {

    public void testLinearSequenceIsUnique() {
        List<Integer> original = Arrays.asList(1,2,3,4);
        List<List<Integer>> seqs = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(2,3),
                Arrays.asList(3,4)
        );
        assertTrue("A straight chain should be uniquely reconstructable", ReconstructingSequence.sequenceReconstruction(original, seqs));
    }

    public void testAmbiguousSequenceNotUnique() {
        List<Integer> original = Arrays.asList(1,2,3);
        List<List<Integer>> seqs = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(1,3)
        );
        assertFalse("Ambiguous constraints should not yield a unique reconstruction", ReconstructingSequence.sequenceReconstruction(original, seqs));
    }

    public void testMissingElementsInSeqs() {
        List<Integer> original = Arrays.asList(1,2,3);
        List<List<Integer>> seqs = Arrays.asList(
                Arrays.asList(1,2)
        );
        assertFalse("When seqs do not cover all elements, reconstruction is impossible", ReconstructingSequence.sequenceReconstruction(original, seqs));
    }

    public void testDuplicateEdgesAreHandled() {
        List<Integer> original = Arrays.asList(1,2,3);
        List<List<Integer>> seqs = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(1,2),
                Arrays.asList(2,3)
        );
        assertTrue("Duplicate subsequences should not break reconstruction when constraints are sufficient", ReconstructingSequence.sequenceReconstruction(original, seqs));
    }

    public void testExampleFromPromptComplexCase() {
        List<Integer> original = Arrays.asList(4,1,5,2,6,3);
        List<List<Integer>> seqs = Arrays.asList(
                Arrays.asList(5,2,6,3),
                Arrays.asList(4,1,5,2)
        );
        assertTrue("The complex example from the prompt should be reconstructable uniquely", ReconstructingSequence.sequenceReconstruction(original, seqs));
    }

    public void testDisconnectedButCompleteOrder() {
        // Provide pairs that together establish a unique order despite being presented in disconnected pieces
        List<Integer> original = Arrays.asList(1,2,3,4,5);
        List<List<Integer>> seqs = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(3,4),
                Arrays.asList(2,3),
                Arrays.asList(4,5)
        );
        assertTrue("Separate chunks that connect should allow unique reconstruction", ReconstructingSequence.sequenceReconstruction(original, seqs));
    }

    public void testIncorrectExtraNumbersInSeqs() {
        List<Integer> original = Arrays.asList(1,2,3);
        List<List<Integer>> seqs = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(2,3),
                Arrays.asList(3,4) // 4 is not part of original
        );
        // If seqs contain numbers not in original, reconstruction should fail (implementation may return false)
        assertFalse("Sequences containing elements outside original should fail reconstruction", ReconstructingSequence.sequenceReconstruction(original, seqs));
    }

    /**
     * 4 6 1 5 8 7 2 3
     * 3
     * 4 6 1 7
     * 1 5 8 2
     * 8 7 2 3
     */
    public void testCase101() {
        List<Integer> original = Arrays.asList(4, 6, 1, 5, 8, 7, 2, 3);
        List<List<Integer>> seqs = Arrays.asList(
                Arrays.asList(4, 6, 1, 7),
                Arrays.asList(1, 5, 8, 2),
                Arrays.asList(8, 7, 2, 3)
        );
        assertTrue("Wrong results", ReconstructingSequence.sequenceReconstruction(original, seqs));
    }
}

