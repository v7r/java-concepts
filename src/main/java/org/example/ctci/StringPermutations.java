package org.example.ctci;

/**
 * Page 51 in Cracking the coding interview book.
 *
 */
public class StringPermutations {

    public static void main(String[] args) {
        permutation("abcd");
    }

    static void permutation(String str) {
        permutation(str, "");
    }

    static void permutation(String prefix, String suffix) {
        if (prefix.length() == 0) {
            System.out.println(suffix);
            return;
        }

        for (int i = 0; i < prefix.length(); i++) {
            String newPrefix = prefix.substring(0,i) + prefix.substring(i+1);
            permutation(newPrefix, suffix + prefix.charAt(i));
        }
    }

    /*
    RecLev 1;
    abcd, ""
    bcd, a; acd, b; abd, c; abc, d;

    RecLev 2-1;
    bcd, a
    cd, ab; bd, ac; bc, ad;

    RecLev 2-1-1
    cd, ab
    d, abc; c, abd;

    RecLevel 2-1-1-1
    c, abd
    "", abdc;

    RecLevel 2-1-1-1-1
    "", abdc <=P

    RecLev 2-1-1-2
    d, abc





     */





    /*

    // Original snippet from the book page: 51
    static void permutation(String str) {
        permutation(str, "");
    }

    static void permutation(String str, String prefix) {
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i= 0; i < str.length(); i++) {
                String rem = str.substring(0, i) + str.substring(i + 1);
                permutation(rem, prefix + str.charAt(i));
            }
        }
    }*/
}
