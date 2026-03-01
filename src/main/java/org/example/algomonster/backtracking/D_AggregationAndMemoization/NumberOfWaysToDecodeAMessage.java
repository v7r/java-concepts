package org.example.algomonster.backtracking.D_AggregationAndMemoization;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * We have a message consisting of digits 0-9 to decode. Letters are encoded to digits by their positions in the
 * alphabet
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Y -> 25
 * Z -> 26
 * Given a non-empty string of digits, how many ways are there to decode it?
 *
 * Input: "18"
 *
 * Output: 2
 *
 * Explanation: "18" can be decoded as "AH" or "R"
 *
 * Input: "123"
 *
 * Output: 3
 *
 * Explanation: "123" can be decoded as "ABC", "LC", "AW"
 *
 * Solution:
 *  <img src="../imgs/NumberOfWaysToDecodeAMessageSolution.png" />
 */
public class NumberOfWaysToDecodeAMessage {

    // 18
    // 1 8, 18

    // 29
    // 2 9(+1), 29X

    // 209
    // 2 9(+1), 20 9(+1), 2 09x

    // Algo.monster solution
    private static int dfsAm(int idx, String digits) {
        if (idx == digits.length()) return 1;
        int res = 0;
        String sWord = String.valueOf(digits.charAt(idx));
        if (sWord.startsWith("0")) return 0;
        res += dfsAm(idx+1, digits);

        if (idx < digits.length() - 1) {
            int dWord = Integer.parseInt(digits.substring(idx,idx+2));
            if (dWord <= 26) {
                res += dfsAm(idx+2, digits);
            }
        }
        return res;
    }

    private static int decodeWaysAlgoMonster(String digits) {
        return dfsAm(0, digits);
    }

    // End of Algo.monster solution

    // My intuition
    private static void dfs(List<String> path, int idx, String digits, AtomicInteger c) {
        // Leaf node
        //if (path.stream().mapToInt(String::length).sum() == digits.length()) {
        if (idx == digits.length()) {
            // Satisfied leaf get a count.
            c.getAndIncrement();
            return;
        }
        // single digit decode
        // NO NEED TO SPAWN SUBTREES FOR EACH REMAINING DIGITS. AS THEY WILL LEAD TO UNSATISFIED LEAFS.
        //for (int i = idx; i < digits.length(); i++) {
        int i = idx;
            String sdigit = String.valueOf(digits.charAt(i));
            if (Integer.parseInt(sdigit) <= 0) return; //continue;
            path.add(sdigit);
            dfs(path,i+1,digits,c);
            path.remove(path.size()-1);

            if (i < digits.length() - 1) {
                String ddigit = digits.substring(i,i+2);
                if (Integer.parseInt(ddigit) > 26) {
                    return; // Prune the invalid two digit. eg: 27 cannot be mapped to a valid alphabet.
                }
                path.add(ddigit);
                dfs(path,i+2,digits,c);
                path.remove(path.size() - 1);
            }
        //}
        // double digit decode
        /*for (int i = idx; i < digits.length()-1; i+=2) {
            String ddigit = digits.substring(i,i+2);
            if (Integer.parseInt(ddigit) > 26 || Integer.parseInt(ddigit) < 10) {
                continue; // Prune the invalid two digit. eg: 27 cannot be mapped to a valid alphabet.
            }
            path.add(ddigit);
            dfs(path,i+2,digits,c);
            path.remove(path.size() - 1);
        }*/
    }

    private static int decodeWaysMyIntuition(String digits) {
        AtomicInteger count = new AtomicInteger(0);
        dfs(new ArrayList<>(),0,digits,count);
        return count.get();
    }
    //End of my intuition.

    public static int decodeWays(String digits) {
        //return decodeWaysAlgoMonster(digits);
        return decodeWaysMyIntuition(digits);
    }
}
