package org.example.algomonster.backtracking.D_AggregationAndMemoization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string and a list of words, determine if the string can be constructed from concatenating words from the
 * list of words. A word can be used multiple times.
 *
 * Input:
 *
 * target = "algomonster"
 * words = ["algo", "monster"]
 * Output: true
 *
 * Input:
 *
 * target = "aab"
 * words = ["a", "c"]
 * Output: false
 */
public class WordBreak {
    // Algo.Monster solution; Look below for my solution.
    // NOTE: memoization is needed to save the time complexity. Refer testLargeCase unit test.
    private static boolean dfs(int idx, String s, List<String> words, Map<Integer,Boolean> memo) {
        if (memo.get(idx) != null) return memo.get(idx);
        if (s.length() == idx) {
            memo.put(idx, true);
            return true;
        }
        boolean res = false;
        for (String w : words) {
            if (s.substring(idx).startsWith(w)) {
                res = res || dfs(idx + w.length(),s,words,memo);
            }
        }
        memo.put(idx, res);
        return res;
    }

    public static boolean wordBreakV2(String s, List<String> words) {
        return dfs(0,s,words,new HashMap<>());
    }
    // End of Algo.monster solution

    // Attempt 1: my intuition
    // NOTE: memoization is needed to save the time complexity. Refer testLargeCase unit test.
    private static boolean checkWordJoin(String s, List<String> words, String join, Map<String,Boolean> memo) {
        if (memo.get(join) != null) return memo.get(join);
        if (s.equals(join)) {
            memo.put(join,true);
            return true;
        }
        if (s.length() <= join.length()) {
            memo.put(join,false);
            return false;
        }
        for (String w : words) {

            // NOTE: MY INTUITION INITIALLY LEAD to explore prefixed and suffixed subtrees.
            //       But, we do not need it. As we are beginning the subtrees for each word from the dictionary of words.
            //       That will cover a subtree prefixed by a every word. So, we just need sub-tree process with either
            //       prefixed word or suffixed word at each node.

            //boolean isConcat = checkWordJoin(s,words,w+join,memo) || checkWordJoin(s,words,join+w,memo);

            // This alone is sufficient to traverse all the state-tree
            boolean isConcat = checkWordJoin(s,words,join+w,memo);

            if (isConcat) {
                memo.put(join,true);
                return true;
            }
        }
        memo.put(join,false);
        return false;
    }

    public static boolean wordBreakV1(String s, List<String> words) {
        return checkWordJoin(s,words,"",new HashMap<>());
    }
    // End of my intuition.



    public static boolean wordBreak(String s, List<String> words) {
        return wordBreakV1(s,words);
    }


}
