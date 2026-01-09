package org.example.leetcode.twopointers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 30. Substring with Concatenation of All Words
 * Hard
 * Topics
 * premium lock icon
 * Companies
 * You are given a string s and an array of strings words. All the strings of words are of the same length.
 *
 * A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
 *
 * For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
 * Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 *
 * Output: [0,9]
 *
 * Explanation:
 *
 * The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
 * The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
 *
 * Example 2:
 *
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 *
 * Output: []
 *
 * Explanation:
 *
 * There is no concatenated substring.
 *
 * Example 3:
 *
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 *
 * Output: [6,9,12]
 *
 * Explanation:
 *
 * The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
 * The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
 * The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].
 *
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * s and words[i] consist of lowercase English letters.
 *
 * Ref: https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
 */
public class SubstringWithConcatinationOfAllWords {
    class Solution {

        //"barfoofoobarthefoobarman" words = ["bar","foo","the"]
        //"rabbarfoothefoobarmanbarthefoo" words = ["bar","foo","the"]
        // TODO: COMPLETE THE LOGIC
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> result = new ArrayList<>();
            int wordLen = words[0].length();
            int l = 0, r = 0;
            List<Character> window = new ArrayList<>();
            while(window.size()<wordLen-1) {
                window.add(s.charAt(r++));
            }
            while (r<s.length()) {
                window.add(s.charAt(r));
                if (!isWindowPromising(window, words) && window.size() > 0) {
                    window.remove(0);
                    l++;
                } else {
                    if (window.size() == wordLen*words.length) {
                        result.add(l);
                        l = r+1;
                        while(window.size()<wordLen-1 && r < s.length()) {
                            window.add(s.charAt(r++));
                        }
                    } else {
                        r++;
                    }
                }
            }
            return result;
        }

        // aabcd
        private boolean isWindowPromising(List<Character> window, String[] words) {
            Set<String> wordSet = new HashSet();
            int wordLen = words[0].length();
            int i = words.length - 1;
            while(i >= 0) {
                wordSet.add(words[i--]);
            }
            Set<String> windowSet = new HashSet();
            i = 0;
            int l = 0;
            while (i<window.size()) {
                if ((1+i) % wordLen == 0) {
                    //windowSet.add(new String(window.subList(l,i+1).ch);
                    l = i + 1;
                }
                i++;
            }
            //windowSet.add(window.substring(l));
            i = 0;
            //while (windowSet) {}
            return true;
        }
    }
}
