package org.example.algomonster.twopointers;

/**
 * Valid Palindrome
 * Determine whether a string is a palindrome, ignoring non-alphanumeric characters and case. Examples:
 *
 * Input: Do geese see God? Output: True
 *
 * Input: Was it a car or a cat I saw? Output: True
 *
 * Input: A brown fox jumping over Output: False
 */
public class ValidPalindrome {
    private static boolean isAlphaNumaric(char c) {
        return Character.isLetterOrDigit(c);
    }

    public static boolean isPalindrome(String s) {
        s = s.toUpperCase();
        int l = 0, r = s.length()-1;
        while (l<r) {
            if (!isAlphaNumaric(s.charAt(l))) {
                l++;
            } else if (!isAlphaNumaric(s.charAt(r))) {
                r--;
            } else if (s.charAt(l) != s.charAt(r)) return false;
            else {
                l++; r--;
            }
        }
        return true;
    }
}
