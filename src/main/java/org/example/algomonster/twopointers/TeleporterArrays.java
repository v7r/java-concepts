package org.example.algomonster.twopointers;

import java.util.List;

/**
 * You are given two sorted arrays of distinct integers, arr1, and arr2. Your goal is to start from the beginning of one array, and arrive at the end of one array (it could be the same array or not).
 *
 * For each step, you can either move forward a step on an array, or move to a square in the other array where the number is the same as the number in your current square ("teleportation"). Your total score is defined as the sum of all unique numbers that you have been on.
 *
 * Find the maximum score that you can get given the above rules. Since the result might be very large and cause overflow, return the maximum score modded by 10^9 + 7.
 *
 * Parameters
 * arr1: A list of ordered, distinct integers.
 * arr2: Another list of ordered, distinct integers.
 * Result
 * The maximum score possible, modded by 10^9 + 7.
 * Examples
 * Example 1
 * Input: arr1 = [2, 4, 5, 8, 10], arr2 = [4, 6, 8, 9]
 *
 * Output: 30
 *
 * Explanation:
 *   <img src="TeleporterArrays.png" alt="Explanation" width="500">
 *
 * Constraints
 * 1 <= len(arr1), len(arr2) <= 50000
 * 1 <= arr1[i], arr2[i] <= 10^7
 * arr1[i] < arr1[j] for all i < j. Same goes for arr2.
 */
public class TeleporterArrays {

    // Attempt 2
    public static int maximumScore(List<Integer> arr1, List<Integer> arr2) {
        long maxSum = 0;
        int MOD_MAX = 1000000007;
        int i = 0, j = 0;
        long sumi = 0, sumj = 0;
        while (i < arr1.size() && j < arr2.size()) {
            if (arr1.get(i).equals(arr2.get(j))) {
                maxSum = (maxSum + Math.max(sumi,sumj) + arr1.get(i)) % MOD_MAX;
                i++;j++;
                sumi = 0; sumj = 0;
            } else if (arr1.get(i) < arr2.get(j)){
                sumi += arr1.get(i);
                i++;
            } else {
                sumj += arr2.get(j);
                j++;
            }
        }
        while (i<arr1.size()) {
            sumi += arr1.get(i);
            i++;
        }
        while (j<arr2.size()) {
            sumj += arr2.get(j);
            j++;
        }
        return (int) (maxSum + Math.max(sumi,sumj)) % MOD_MAX;
    }

    //2 3 5 6 7 9 11 13 14 16 17 19 20
    //3 4 5 7 8 10 11 12 15 16 18 20
    public static int maximumScoreAttempt1(List<Integer> arr1, List<Integer> arr2) {
        int maxSum = Math.max(sumX(arr1,arr2), sumX(arr2,arr1));
        return maxSum;
    }

    //2 4 5 8 10   Total: 29, squareSum: 2+4+6+8+10 = 30
    //4 6 8 9      Total: 27, squareSum: 4+5+8+9 = 27
    //o=2,c=2 2+4+6

    // Expected: 155
    //2 3 5 6 7 9 11 13 14 16 17 19 20   Total:
    //3 4 5 7 8 10 11 12 15 16 18 20

    public static int sumX(List<Integer> arr1, List<Integer> arr2) {
        int c = 0, o = 0;
        List<Integer> ca = arr1, oa = arr2;
        int sum = 0;
        while (c < ca.size()) {
            if (o < oa.size() && ca.get(c).equals(oa.get(o))) {
                sum += ca.get(c);
                int t = c;
                c = o;
                o = t;
                c++;
                o++;
                List<Integer> ta = ca;
                ca = oa;
                oa = ta;
            } else {
                sum += ca.get(c);
                c++;
                while (o<oa.size() && c<ca.size() && oa.get(o) < ca.get(c)) {
                    o++;
                }
            }
        }
        return sum;
    }

    // Recommended in the Algo.monster
    public static int maximumScore1(List<Integer> arr1, List<Integer> arr2) {
        int ptr1 = 0, ptr2 = 0;
        int n1 = arr1.size(), n2 = arr2.size();
        int MODULO_AMT = 1000000007;
        // We use long for these attributes because they can exceed integer limit.
        // The max score, summed up and modded
        long result = 0;
        // The sum of the subarray between the previous teleporter and the pointer for each array
        long sectionSum1 = 0, sectionSum2 = 0;
        // As long as the two arrays are not both at the end, we advance the pointers
        while (ptr1 < n1 || ptr2 < n2) {
            // If they match, we sum up the max score of that section and the score of
            // the current position, then shrink result by using modulo
            // Reset the sums and move the pointers afterwards
            if (ptr1 < n1 && ptr2 < n2 && arr1.get(ptr1).equals(arr2.get(ptr2))) {
                result += Math.max(sectionSum1, sectionSum2) + arr1.get(ptr1);
                result %= MODULO_AMT;
                sectionSum1 = 0;
                sectionSum2 = 0;
                ptr1++;
                ptr2++;
                continue;
            }
            // If either "ptr1" reaches the end, or "ptr2" is smaller than "ptr1"
            // we move "ptr2" and keep track of the sum.
            if (ptr1 == n1 || (ptr2 != n2 && arr1.get(ptr1) > arr2.get(ptr2))) {
                sectionSum2 += arr2.get(ptr2);
                ptr2++;
            } else {
                // Otherwise, we move "ptr1" and keep track of that sum
                sectionSum1 += arr1.get(ptr1);
                ptr1++;
            }
        }
        // Add the remaining max section sum to the result, then return the result
        // modulo
        result += Math.max(sectionSum1, sectionSum2);
        return (int)(result % MODULO_AMT);
    }
}
