package org.example.hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import java.util.concurrent.atomic.AtomicLong;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * HackerRank problem
 * Ref: https://www.hackerrank.com/challenges/crush/problem?isFullScreen=true
 * User: vamsidhar.my@gmail.com ( google oauth )
 * Could not solved it yet.
 */
class Result1 {

    /*
     * Complete the 'arrayManipulation' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */

    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        // Write your code here
        class RollupItem {
            int a = 0;
            int b = Integer.MAX_VALUE;
            long sum;
            public RollupItem(int a, int b, long sum) {
                this.a = a;
                this.b = b;
                this.sum = sum;
            }

            @Override
            public String toString() {
                return a+" "+b+" "+sum;
            }
        };

        Comparator<RollupItem> c = (r1, r2) -> (int)(r2.sum - r1.sum);
        List<RollupItem> bag = new ArrayList<>();
        bag.add(new RollupItem(0, Integer.MAX_VALUE, 0));
        queries.stream().forEach(op -> {
            RollupItem i = new RollupItem(op.get(0),op.get(1),op.get(2));
            boolean overlapFound = false;
            for (RollupItem ri : bag) {
                if (Math.max(ri.a, i.a) <= Math.min(ri.b, i.b)) {
                    ri.a = Math.max(ri.a, i.a);
                    ri.b = Math.min(ri.b, i.b);
                    ri.sum += i.sum;
                    overlapFound = true;
                    //break;
                }
            }
            if (!overlapFound) {
                bag.add(i);
            }
        });
        Collections.sort(bag, c);
        return bag.get(0).sum;

        /*
40 30
29 40 787
9 26 219
21 31 214
8 22 719
15 23 102
11 24 83
14 22 321
5 22 300
11 30 832
5 25 29
16 24 577
3 10 905
15 22 335
29 35 254
9 20 20
33 34 351
30 38 564
11 31 969
3 32 11
29 35 267
4 24 531
1 38 892
12 18 825
25 32 99
3 39 107
12 37 131
3 26 640
8 39 483
8 11 194
12 37 502
        */


        // Mediacore solution
        /*AtomicLong max = new AtomicLong(0);
        long[] b = new long[n];
        queries.stream().forEach(op -> {
            long k = op.get(2);
            int starti = op.get(0)-1;
            int endi = op.get(1)-1;
            while ( starti <= endi ) {
                b[starti] += k;
                if (b[starti] > max.get()) max.set(b[starti]);
                starti++;
            }
        });
        return max.get();*/
    }
}

public class MaxOfArrayOperations {
    public static void main(String[] args) throws IOException {
        /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });*/

        int n = 40;
        List<List<Integer>> queries = new ArrayList<>();
        queries.add(Arrays.asList(29,40, 787));
        queries.add(Arrays.asList(9, 26, 219));
        queries.add(Arrays.asList(21, 31, 214));
        queries.add(Arrays.asList(8, 22, 719));
        queries.add(Arrays.asList(15, 23, 102));
        queries.add(Arrays.asList(11, 24 ,83));
        queries.add(Arrays.asList(14, 22 ,321));
        queries.add(Arrays.asList(5, 22 ,300));
        queries.add(Arrays.asList(11, 30, 832));
        queries.add(Arrays.asList(5, 25, 29));
        queries.add(Arrays.asList(16, 24, 577));
        queries.add(Arrays.asList(3, 10 ,905));
        queries.add(Arrays.asList(15, 22 ,335));
        queries.add(Arrays.asList(29, 35 ,254));
        queries.add(Arrays.asList(9, 20 ,20));
        queries.add(Arrays.asList(33, 34 ,351));
        queries.add(Arrays.asList(30, 38 ,564));
        queries.add(Arrays.asList(11, 31 ,969));
        queries.add(Arrays.asList(3,32 ,11));
        queries.add(Arrays.asList(29, 35, 267));
        queries.add(Arrays.asList(4, 24, 531));
        queries.add(Arrays.asList(1, 38 ,892));
        queries.add(Arrays.asList(12, 18 ,825));
        queries.add(Arrays.asList(25, 32 ,99));
        queries.add(Arrays.asList(3, 39 ,107));
        queries.add(Arrays.asList(12, 37 ,131));
        queries.add(Arrays.asList(3, 26, 640));
        queries.add(Arrays.asList(8, 39, 483));
        queries.add(Arrays.asList(8, 11, 194));
        queries.add(Arrays.asList(12, 37, 502));
        // Expected result: 8628;

        long result = Result1.arrayManipulation(n, queries);

        System.out.println("Result: "+ result);

    }
}

