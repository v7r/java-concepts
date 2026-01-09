package org.example.algomonster.twopointers;

/**
 * Given a string s, reverse only all the vowels in the string and return it.
 *
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 *
 * Example 1:
 *
 * Input: s = "hello"
 * Output: "holle"
 *
 * Example 2:
 *
 * Input: s = "leetcode"
 * Output: "leotcede"
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consist of printable ASCII characters.
 */
public class ReverseVowels {
    public String reverseVowels(String s) {
        String vowels = "aeiouAEIOU";
        int l = 0, r = s.length() - 1;

        char[] res = s.toCharArray();

        while (l < r) {
            if (vowels.indexOf(s.charAt(l)) == -1) {   // s[l] is not a vowel
                l++;
            } else if (vowels.indexOf(s.charAt(r)) == -1) { // s[r] is not a vowel
                r--;
            } else {
                // both are vowels, swap
                char temp = res[l];
                res[l] = res[r];
                res[r] = temp;
                l++;
                r--;
            }
        }

        return new String(res);
    }
}
