package org.example.ctci;

public class NthFibonacciNumber {

    public static void main(String[] args) {
        System.out.println("Fib at 0: "+fibonacci(0));
        System.out.println("Fib at 1: "+fibonacci(1));
        System.out.println("Fib at 2: "+fibonacci(2));
        System.out.println("Fib at 3: "+fibonacci(3));
        System.out.println("Fib at 4: "+fibonacci(4));
        System.out.println("Fib at 5: "+fibonacci(5));
        System.out.println("Fib at 6: "+fibonacci(6));
        System.out.println("Fib at 7: "+fibonacci(7));

        allFib(7);
    }

    /**
     * 0, 1, 1, 2, 3, 5, 8, 13 ...
     * @param n
     * @return
     */
    static int fibonacci(int n) {
        if (n <= 0) return 0;
        else if (n == 1)  return 1;
        return fibonacci(n-1) + fibonacci(n-2);
    }

    static void allFib(int n) {
        System.out.println("Fobonacci of "+n);
        for (int i = 0; i <= n; i++) {
            System.out.println(fibonacci(i));
        }
    }

}
