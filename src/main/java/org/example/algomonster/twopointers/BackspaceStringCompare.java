package org.example.algomonster.twopointers;

/**
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a
 * backspace character.
 *
 * Note that after backspacing an empty text, the text will continue empty.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 * Explanation: Both s and t become "ac".
 * Example 2:
 *
 * Input: s = "ab##", t = "c#d#"
 * Output: true
 * Explanation: Both s and t become "".
 * Example 3:
 *
 * Input: s = "a#c", t = "b"
 * Output: false
 * Explanation: s becomes "c" while t becomes "b".
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 200
 * s and t only contain lowercase letters and '#' characters.
 *
 *
 * Follow up: Can you solve it in O(n) time and O(1) space?
 */
public class BackspaceStringCompare {
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;
        int ii = 0, jj = 0;

        while (i >= 0 || j >= 0) {
            while(i >= 0) {
                if (s.charAt(i) == '#') {
                    i--;
                    ii++;
                } else if (ii > 0) {
                    i--; ii--;
                } else {
                    break;
                }
            }
            while(j >= 0) {
                if (t.charAt(j) == '#') {
                    j--;
                    jj++;
                } else if (jj > 0) {
                    j--; jj--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) return false;
            if ((i >= 0 && j < 0) || (j >= 0 && i < 0)) return false;
            i--; j--;
        }

        return true;
    }
}
