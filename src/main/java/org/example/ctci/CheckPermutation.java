package org.example.ctci;

/**
 * Page # 193 in ctci book
 *
 * Check Permutation: Given two strings, write a method to decide if one is a permutation of the
 * other
 */
public class CheckPermutation {
    public static void main(String[] args) {
        String a = "God   ";
        String b = "dog";

        System.out.println("is permutation? "+isPermutation(a, b));

        a = "god";
        b = "dog";
        System.out.println("is permutation? "+isPermutation(a, b));

        a = "ggd";
        b = "ddg";
        System.out.println("is permutation? "+isPermutation(a, b));
    }

    static boolean isPermutation(String a, String b) {
        if (a.length() != b.length()) return false;




        /*for (int i = 0; i < a.length(); i++) {
            if (!b.contains(""+a.charAt(i))) {
                return false;
            }
        }*/
        return true;
    }
}
