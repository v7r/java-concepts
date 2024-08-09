package org.example;

/**
 * What is the output of this
 *
 * A. 4
 * B. 2
 * C. 6
 * D. Infinite loop
 * E. Compilation fails
 * F. 5
 * G. Runtime error
 * H. 1
 */
public class TrickySwitchCase {
    public static void main(String[] args) {
        eg1();
        eg2();
    }

    private static void eg1() {
        final int x = 2;
        int y = x;
        while (y < 3) {
            switch (y) {
                case 0 + x:
                    y++;
                case 1:
                    y++;
            }
        }
        System.out.println(y);
    }

    private static void eg2() {
        Integer rank = 4;
        switch (rank) {
            case 1, 4 -> System.out.println("Flute");
            case 5, 8 -> System.out.println("Piano");
            case 9, 10 -> System.out.println("Drums");
            default -> System.out.println("Vocal");
        }
    }
}
