package org.example.algomonster.backtracking.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem statement
 *
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots
 * (.) into s.
 *
 * A valid IP address consists of exactly four integers, each between 0 and 255, separated by dots, and no integer has
 * leading zeros (unless the integer is exactly "0").
 *
 * Examples
 * Input:  "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 *
 * Input: "0000"
 * Output: ["0.0.0.0"]
 *
 * Input: "101023"
 * Output: [
 *   "1.0.10.23",
 *   "1.0.102.3",
 *   "10.1.0.23",
 *   "10.10.2.3",
 *   "101.0.2.3"
 * ]
 */
public class RestoreIpAddresses {

    private static boolean isValidSeg(String s) {
        if (s.isEmpty() || s.length() > 3) return false;
        int set = Integer.parseInt(s);
        if (s.length() > 1 && s.charAt(0) == '0') return false;
        return set >= 0 && set <= 255;
    }

    private static void dfs(int idx, List<String> path, String s, List<String> ans) {
        if (idx == s.length()) {
            if (path.size() == 4) {
                ans.add(String.join(".",path));
            }
            return;
        }
        for (int i = idx; i < s.length(); i++) {
            String seg = s.substring(idx, i+1);
            if (isValidSeg(seg)) {
                path.add(seg);
                dfs(i+1,path, s, ans);
                path.remove(path.size()-1);
            }
        }
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        dfs(0, new ArrayList<>(), s, ans);
        return ans;
    };

}
