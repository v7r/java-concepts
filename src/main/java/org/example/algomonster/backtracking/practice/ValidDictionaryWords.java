package org.example.algomonster.backtracking.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a
 * valid dictionary word. Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 *
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 *
 * Output: ["cats and dog","cat sand dog"]
 *
 * Example 2:
 *
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 *
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 *
 * Explanation: Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 *
 * Output: []
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 *
 */
public class ValidDictionaryWords {
    // base case: if idx == s.length; record path that forms a sentence.
    // state: idx, path, s, wordDict
    // edges: at idx, path contains valid words formed using 0..idx-1 characters.
    //        find valid word from idx and recurse.
    private static void dfs(int idx, List<String> path, String s, List<String> wordDict, List<String> ans) {
        if (idx == s.length()) {
            ans.add(String.join(" ", path));
            return;
        }
        for (int i = idx; i < s.length(); i++) {
            if (wordDict.contains(s.substring(idx, i+1))) {
                path.add(s.substring(idx, i+1));
                dfs(i+1, path, s, wordDict, ans);
                path.remove(path.size()-1);
            }
        }
    }

    public static List<String> validWords(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        dfs(0, new ArrayList(), s, wordDict, ans);
        return ans;
    }
}
