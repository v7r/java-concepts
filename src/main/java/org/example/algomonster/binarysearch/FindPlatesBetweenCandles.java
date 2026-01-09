package org.example.algomonster.binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * There is a long table with a line of plates and candles arranged on top of it. You are given a 0-indexed string s
 * consisting of characters '*' and '|' only, where a '*' represents a plate and a '|' represents a candle.
 *
 * You are also given a 0-indexed 2D integer array queries where queries[i] = [lefti, righti] denotes the substring
 * s[lefti...righti] (inclusive). For each query, you need to find the number of plates between candles that are in
 * the substring. A plate is considered between candles if there is at least one candle to its left and at least one
 * candle to its right in the substring.
 *
 * For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|". The number of plates between
 * candles in this substring is 2 (at indices 6 and 7), as each of the two plates has at least one candle in the
 * substring to its left and right.
 *
 * Return an integer array answer where answer[i] is the answer to the ith query.
 *
 * Example 1:
 *
 * ex-1
 *
 * Input: s = "**|**|***|", queries = [[2,5],[5,9]]
 *
 * Output: [2,3]
 *
 * Explanation:
 *
 * queries[0] has two plates between candles.
 * queries[1] has three plates between candles.
 * Example 2:
 *
 * ex-2
 *
 * Input: s = "***|**|*****|**||**|*",
 * queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 *
 * Output: [9,0,0,0,0]
 *
 * Explanation:
 *
 * queries[0] has nine plates between candles.
 * The other queries have zero plates between candles.
 * Constraints:
 *
 * 3 <= s.length <= 105
 * s consists of '*' and '|' characters.
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= lefti <= righti < s.length
 *
 *
 * Solution Hint:
 * Instead of counting the number of plates between two indices, which takes O(n) time, we can use the indices of the
 * candles to figure out the number of plates between the candles (store in candles).
 *
 * For each query (qLeft, qRight), we need to find the two candles on the outside within the boundary [qLeft, qRight].
 * This is where binary search comes in.
 */
public class FindPlatesBetweenCandles {

    // Input: s = "***|**|*****|**||**|*",
    // queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
    // candles = [3,6,12,15,16,19]
    // for each queries[i] find the candles left and right bound to count the plates between these bounds.
    // for queries[0], i.e, [1,17] the lower bound in candles is candles[0] ( 1 < 3 )
    // and upper bound is candles[4] (16 < 17)
    // Now count the plates between candles [3,6,12,15,16] ( left bound candles[0] and right bound candles[4])
    // so the output of queries[0] is 9 (2+5+2+0)
    // to find the left bound in candles use this in binary search template: candles[i] >= leftBound ( find the first true )
    // similarly for the right bound: candles[i] <= rightBound ( find the first false )
    public static List<Integer> platesBetweenCandles(String s, int[][] queries) {
        // Step 0: Pre-process the string to find all candle positions
        List<Integer> candles = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '|') {
                candles.add(i);
            }
        }

        List<Integer> res = new ArrayList<>();

        // Handle the edge case where there are no candles
        if (candles.isEmpty()) {
            for (int[] query : queries) {
                res.add(0);
            }
            return res;
        }

        // Process each query
        for (int[] query : queries) {
            int qleft = query[0];
            int qright = query[1];

            int leftPos = -1; // Index in the 'candles' list of the first candle >= qleft
            int rightPos = -1; // Index in the 'candles' list of the last candle <= qright

            // --- 1. Find the index of the first candle that comes at or after qleft (Lower Bound/Ceiling) ---
            int left = 0;
            int right = candles.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (candles.get(mid) >= qleft) {
                    right = mid - 1;
                    leftPos = mid;
                } else {
                    left = mid + 1;
                }
            }

            // --- 2. Find the index of the last candle that comes at or before qright (Upper Bound/Floor) ---
            left = 0;
            right = candles.size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (candles.get(mid) <= qright) {
                    left = mid + 1;
                    rightPos = mid;
                } else {
                    right = mid - 1;
                }
            }

            // --- 3. Calculate the result ---

            // Check if both boundary candles were found AND the rightmost candle is
            // *after* (or at the same position in the 'candles' list as) the leftmost candle.
            // Note: the Python logic uses 'right_pos > left_pos' which means there must be
            // at least two distinct candles in the list.
            if (leftPos != -1 && rightPos != -1 && rightPos > leftPos) {
                /* * The calculation is:
                 * (Position of rightmost candle) - (Position of leftmost candle) = Total distance
                 * Total distance - 1 = Total number of characters (plates and candles) between them
                 * (rightPos - leftPos) = Number of candles *including* the two boundary candles - 1
                 * * Plates = (Total distance + 1) - (Number of candles between and including boundaries)
                 * Plates = (candles[rightPos] - candles[leftPos] + 1) - (rightPos - leftPos + 1)
                 * Plates = (candles[rightPos] - candles[leftPos]) - (rightPos - leftPos)
                 */
                int totalDistance = candles.get(rightPos) - candles.get(leftPos);
                int candleCountBetween = rightPos - leftPos;

                res.add(totalDistance - candleCountBetween);
            } else {
                res.add(0);
            }
        }

        return res;
    }
}
