package org.example.algomonster.binarysearch;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You have a stack of newspapers in a fixed order. Each newspaper has a read time. You must split this stack into
 * consecutive sections and assign each section to a worker. Workers read their assigned newspapers in parallel.
 *
 * The constraint: you cannot reorder newspapers. If you assign newspapers at positions 1, 2, 3 to worker A, you cannot
 * then assign newspaper 2 to worker B. Each worker gets a consecutive block from the original stack.
 *
 * Find the minimum time needed to read all newspapers. Since workers read in parallel, the total time equals the time
 * taken by the slowest worker.
 *
 * For example, with newspapers [7,2,5,10,8] and 2 workers, you could assign [7,2,5] to worker A (14 minutes total) and
 * [10,8] to worker B (18 minutes total). Worker B finishes last, so the answer is 18 minutes.
 *
 * Constraints
 * 1 <= newspapers_read_times.length <= 10^5
 *
 * 1 <= newspapers_read_times[i] <= 10^5
 *
 * 1 <= num_coworkers <= 10^5
 *
 * Examples
 * Example 1:
 * Input: newspapers_read_times = [7,2,5,10,8], num_coworkers = 2
 * Output: 18
 * Explanation:
 * Assign first 3 newspapers to one coworker then assign the rest to another. The time it takes for the first 3 newspapers is 7 + 2 + 5 = 14 and for the last 2 is 10 + 8 = 18.
 *
 * Example 2:
 * Input: newspapers_read_times = [2,3,5,7], num_coworkers = 3
 * Output: 7
 * Explanation:
 * Assign [2, 3], [5], and [7] separately to workers. The minimum time is 7.
 *
 *
 *
 * NOTE:
 *  Generalised solution identification for this kind of problem.
 *   https://gemini.google.com/share/a751fdd27774
 */
public class NewsPapers {
    // The problem is to find the minimum time needed to read all news papers.
    // Seach bounds
    //  Minimum bound: Maximum of read times. This happens when each worker is assigned with one paper to read.
    //  Maximum bound: Sum of all read times. This happens when one worker reading all papers.
    // The solution is to find the minimum time needed to read all the papers by the given readers.
    // Note that it is not constrained that we should use all the readers. It is inferred that we can use at most readers but not more.
    //
    public static int newspapersSplit(List<Integer> newspapersReadTimes, int numCoworkers) {
        int minTime = 0;
        int left = Collections.max(newspapersReadTimes);
        int right = newspapersReadTimes.stream().mapToInt((i) -> i).sum();
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (isFeasible(newspapersReadTimes, numCoworkers, mid)) {
                minTime = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return minTime;
    }

    // [7 2 5 14 17], 14
    private static boolean isFeasible(List<Integer> newspapersReadTimes, int numCoworkers, int maxTime) {
        // accumulate time until the maxTime.
        // If accumulated is more than maxTime then add a worker and set the current readTime into accumulated.
        // at the end if accumulated is non zero then we need one more worker.
        int workersAssigned = 0;
        int cumulativeTime = 0;
        for (int readTime : newspapersReadTimes) {
            if (cumulativeTime + readTime > maxTime) {
                workersAssigned++;
                cumulativeTime = 0;
            }
            cumulativeTime += readTime;
        }
        if (cumulativeTime > 0) workersAssigned++;

        if (workersAssigned == 0) return false;
        return workersAssigned <= numCoworkers;
    }
}
