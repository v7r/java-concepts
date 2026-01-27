package org.example.algomonster.backtracking.C_BackgrackingAdditionalStates;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a string of unique letters, find all of its distinct permutations.
 *
 * Permutation means arranging things with an order. For example, permutations of [1, 2] are [1, 2] and [2, 1].
 * Permutations are best visualized with trees.
 *
 * <img src="../imgs/GenerateAllPermutations.png" />
 *
 * The number of permutations is given by n! (we looked at factorial in Recursion Review). The way to think about
 * permutation is to imagine you have a bag of 3 letters. Initially, you have 3 letters to choose from, you pick one
 * out of the bag. Now you are left with 2 letters, you pick again now there's only 1 letter. The total number of
 * choices is 3*2*1 = 6 (hence we have 6 leaf nodes in the above tree).
 *
 * Input
 * letters: a string of unique letters
 * Output
 * all of its distinct permutations
 *
 * Examples
 * Example 1:
 * Input:
 *
 * letters = abc
 * Output: abc acb bac bca cab cba
 *
 * Explanation:
 *
 * All permutations.
 */
public class GenerateAllPermutations {
    private static void dfs(List<Character> path, String letters, List<String> p) {
        if (path.size() == letters.length()) {
            p.add(path.stream().map(String::valueOf).collect(Collectors.joining()));
            return;
        }
        for (Character c : letters.toCharArray()) {
            if (path.contains(c)) continue;
            path.add(c);
            dfs(path,letters,p);
            path.remove(path.size()-1);
        }
    }

    public static List<String> permutations(String letters) {
        List<String> permutations = new ArrayList<>();
        dfs(new ArrayList<>(),letters,permutations);
        return permutations;
    }
}
