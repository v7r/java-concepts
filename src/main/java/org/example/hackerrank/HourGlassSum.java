package org.example.hackerrank;

/**
 * Problem from hackerrank
 *
 * Ref: https://www.hackerrank.com/challenges/2d-array/problem?isFullScreen=true
 * Login with vamsidhar.my@gmail.com
 *
 *
 *
 */
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import java.util.Comparator;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    /*
     * Complete the 'hourglassSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int hourglassSum(List<List<Integer>> arr) {
        // Write your code here
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(16, Comparator.reverseOrder());
        for (int i = 1; i < arr.size() - 1; i++ ) {
            for (int j = 1; j < arr.get(i).size() - 1; j++ ) {
                int rowTopI = i - 1;
                int rowBotI = i + 1;
                // Top row
                List<Integer> rowTop = arr.get(rowTopI);
                List<Integer> rowBot = arr.get(rowBotI);
                int sumAtj = rowTop.get(j-1) + rowTop.get(j) + rowTop.get(j+1);
                sumAtj += arr.get(i).get(j);
                sumAtj += rowBot.get(j-1) + rowBot.get(j) + rowBot.get(j+1);
                pq.add(sumAtj);
            }
        }
        return pq.remove();
    }

    // 4,3
    // TopRow = 0 -9  0  4 3 2
    // BotRow = 0  0  8  6 6 0

    // 0 + 4 + 3 + 1 +

    /*
        1 1 1 0 0 0
        0 1 0 0 0 0
        1 1 1 0 0 0
        0 0 2 4 4 0
        0 0 0 2 0 0
        0 0 1 2 4 0
    */

}

public class HourGlassSum {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, 6).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

