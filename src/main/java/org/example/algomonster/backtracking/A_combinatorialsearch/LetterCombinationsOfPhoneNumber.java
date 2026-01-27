package org.example.algomonster.backtracking.A_combinatorialsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *     Given a phone number that contains digits 2-9, find all possible letter combinations the phone number could translate to.
 * </p>
 *
 *  <img src="../imgs/LetterCombinationsOfPhoneNumber.png" />
 *
 * <p>
 * Input:
 *
 * "56"
 * Output:
 *
 * ["jm","jn","jo","km","kn","ko","lm","ln","lo"]
 * </p>
 */
public class LetterCombinationsOfPhoneNumber {
    private static final Map<Integer,String> dcMap = new HashMap<>();
    static {
        dcMap.put(2, "ABC");
        dcMap.put(3, "DEF");
        dcMap.put(4, "GHI");
        dcMap.put(5, "JKL");
        dcMap.put(6, "MNO");
        dcMap.put(7, "PQRS");
        dcMap.put(8, "TUV");
        dcMap.put(9, "WXYZ");
    }

    // 5[jkl], 6[mno]
    private static void comb(List<Character> path, List<String> words, String digits, int idx) {
        if (path.size() == digits.length()) {
            words.add(path.stream().map(String::valueOf).collect(Collectors.joining()).toLowerCase());
            return;
        }
        Character digit = digits.charAt(idx);
        String dString = dcMap.get(Integer.parseInt(String.valueOf(digit)));
        for (Character dchar : dString.toCharArray()) {
            path.add(dchar);
            comb(path, words, digits, idx+1);
            path.remove(path.size()-1);
        }
    }

    public static List<String> letterCombinationsOfPhoneNumber(String digits) {
        List<String> words = new ArrayList<>();
        comb(new ArrayList<>(), words, digits, 0);
        return words;
    }

}
