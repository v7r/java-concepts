package org.example.algomonster.graphs_directed;

import java.util.*;

/**
 * There are a total of n courses a student has to take, numbered from 0 to n-1. A course may have prerequisites.
 * The "depends on" relationship is expressed as a pair of numbers. For example, [0, 1] means you need to take course
 * 1 before taking course 0. Given n and the list of prerequisites, decide if it is possible to take all the courses.
 *
 * Example 1:
 *
 * Input: n = 2, prerequisites = [[0, 1]]
 *
 * Output: true
 *
 * Explanation: we can take 1 first and then 0.
 *
 * Example 2:
 *
 * Input: n = 2, prerequisites = [[0, 1], [1, 0]]
 *
 * Output: false
 *
 * Explanation: the two courses depend on each other, there is no way to take them
 */
public class CourseSchedule {
    public static boolean isValidCourseSchedule(int n, List<List<Integer>> prerequisites) {
        Map<Integer, List<Integer>> outDegree = new HashMap<>();
        Map<Integer, List<Integer>> inDegree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            outDegree.computeIfAbsent(i, ArrayList::new);
            inDegree.computeIfAbsent(i, ArrayList::new);
        }
        for (List<Integer> prereq : prerequisites) {
            outDegree.get(prereq.get(1)).add(prereq.get(0));
            inDegree.get(prereq.get(0)).add(prereq.get(1));
        }
        List<Integer> ans = new ArrayList<>();
        ArrayDeque<Integer> q = new ArrayDeque();
        q.addAll(inDegree.entrySet().stream()
                .filter(e -> e.getValue().isEmpty())
                .map(e -> e.getKey())
                .toList()
        );
        while(!q.isEmpty()) {
            Integer currentCourse = q.poll();
            ans.add(currentCourse);
            List<Integer> outDegreeList = outDegree.get(currentCourse);
            for (Integer outDegreeCourse : outDegreeList) {
                inDegree.get(outDegreeCourse).remove(currentCourse);
                if (inDegree.get(outDegreeCourse).isEmpty()) {
                    q.add(outDegreeCourse);
                }
            }
        }

        return ans.size() == n;
    }
}
