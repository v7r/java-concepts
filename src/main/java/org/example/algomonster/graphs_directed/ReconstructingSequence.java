package org.example.algomonster.graphs_directed;

import java.util.*;

/**
 * A sequence s is a list of integers. Its subsequence is a new sequence that can be made up by deleting elements from
 * s, without changing the order of integers.
 *
 * We are given an original sequence (which is a permutation of the integers from 1 to n) and a list of subsequences
 * seqs.
 *
 * Determine whether original is the only sequence that can be reconstructed from seqs. Reconstruction means building
 * the shortest sequence so that all sequences in seqs are subsequences of it.
 *
 * Parameters
 * original: a list of integers of size n representing the original sequence.
 * seqs: a list of sequences of size m representing the sequences to be reconstructed.
 * Result
 * true or false, depending on whether the original sequence can be uniquely reconstructed.
 * Examples
 * Example 1:
 * Input: original: [1,2,3], seqs: [[1,2], [1,3]]
 *
 * Output: false
 *
 * Explanation:
 *
 * [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can
 * be reconstructed.
 *
 * Example 2:
 * Input: original: [1,2,3], seqs: [[1,2]]
 *
 * Output: false
 *
 * Explanation:
 *
 * There is only one subsequence, so the reconstructed original sequence can only be [1,2] which is missing 3.
 *
 * Example 3:
 * Input: orginal: [1,2,3], seqs: [[1,2], [1,3], [2,3]]
 *
 * Output: true
 *
 * Explanation:
 *
 * [1,2,3] is the only sequence that can be reconstructed from [1,2], [1,3], and [2,3].
 *
 * Example 4:
 * Input: original: [4,1,5,2,6,3], seqs: [[5,2,6,3], [4,1,5,2]]
 *
 * Output: true
 *
 * Explanation:
 *
 * [4,1,5,2,6,3] is the only sequence that can be reconstructed from [[5,2,6,3], [4,1,5,2]].
 *
 * Constraints
 * 1 <= n <= 10^4
 * 1 <= m <= 10^4
 * 1 <= len(seqs[i]) <= n
 */
public class ReconstructingSequence {
    //4 6 1 5 8 7 2 3
    //4 6 1 7
    //1 5 8 2
    //8 7 2 3
    // outDegree: 4[6], 6[1], 1[7,5], 5[8], 8[2,7], 7[2], 2[3]
    //  inDegree: 6[4], 1[6], 7[1,8], 5[1], 8[5], 2[8,7], 3[2]
    // q: 4,6,1,5,8,7,2,3

    public static boolean sequenceReconstruction(List<Integer> original, List<List<Integer>> seqs) {
        Map<Integer, List<Integer>> inDegreeList = new HashMap<>();
        Map<Integer, List<Integer>> outDegreeList = new HashMap<>();

        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                if (i > 0) {
                    // build the in degree
                    int from = seq.get(i - 1);
                    int to = seq.get(i);
                    List<Integer> inDegreeOfTo =
                            inDegreeList.computeIfAbsent(seq.get(i), k -> new ArrayList<>());
                    inDegreeOfTo.add(from);
                }

                if (i < seq.size() - 1) {
                    // build out degree
                    int from = seq.get(i);
                    int to = seq.get(i+1);
                    List<Integer> outDegreeOfFrom =
                            outDegreeList.computeIfAbsent(seq.get(i), k -> new ArrayList<>());
                    outDegreeOfFrom.add(to);
                }
            }
        }

        final List<Integer> emptyEdges = new ArrayList<>();
        for (int node : outDegreeList.keySet()) {
            inDegreeList.compute(node, (k,v) -> {
                if (v == null || v.isEmpty()) emptyEdges.add(node);
                return v;
            });
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.addAll(emptyEdges);
        List<Integer> seq = new ArrayList<>();
        while (!q.isEmpty()) {
            int currentNode = q.pop();
            seq.add(currentNode);
            List<Integer> outDegree = outDegreeList.get(currentNode);
            if (outDegree == null) continue;
            //if (outDegree.size() > 1) return false;
            // check the outDegree nodes. If each of them has 1 indegree then return false;
            int outDegreeEdgeCount = 0;
            for (Integer outDegreeNode : outDegree) {
                List<Integer> inDegree = inDegreeList.get(outDegreeNode);
                if (inDegree == null) continue;
                inDegree.remove(inDegree.indexOf(currentNode));
                if (inDegree.isEmpty()) {
                    q.add(outDegreeNode);
                    outDegreeEdgeCount++;
                }
                if (outDegreeEdgeCount > 1) return false;
            }
        }

        return Arrays.equals(original.toArray(new Integer[0]), seq.toArray(new Integer[0]));
    }
}
