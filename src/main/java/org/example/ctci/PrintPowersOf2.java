package org.example.ctci;

/**
 * Page 54 in ctci book
 *
 * Prints the powers of 2 from 1 through n (inclusive).
 * For example, if n is 4, it would print 1, 2, and 4.
 * For example, if n is 3, it would print 1 and 2.
 * For example, if n is 2, it would print 1 and 2.
 * For example, if n is 1, it would print 1.
 * For example, if n is 0, it would print 0.
 */
public class PrintPowersOf2 {
    public static void main(String[] args) {
        powerOf2(25);
    }

    static int powerOf2(int n) {
        if (n <= 0) {
            return 0;
        } else if ( n == 1) {
            System.out.println(" "+1);
            return 1;
        } else {
                int prev = powerOf2(n / 2);
            int current = prev * 2;
            System.out.println(" "+current);
            return current;
        }
    }
}


