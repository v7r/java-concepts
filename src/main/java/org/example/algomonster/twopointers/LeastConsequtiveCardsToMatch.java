package org.example.algomonster.twopointers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A bunch of cards is laid out in front of you in a line, where the value of each card ranges from 0 to 10^6. A pair of
 * cards is matching if they have the same number value.
 *
 * Given a list of integers cards, your goal is to match a pair of cards, but you can only pick up cards in a
 * consecutive manner. What's the minimum number of cards that you need to pick up to make a pair? If there are no
 * matching pairs, return -1.
 *
 * For example, given cards = [3, 4, 2, 3, 4, 7], then picking up [3, 4, 2, 3] makes a pair of 3s and picking up
 * [4, 2, 3, 4] matches two 4s. We need 4 consecutive cards to match a pair of 3s and 4 consecutive cards to match 4s,
 * so you need to pick up at least 4 cards to make a match.
 */
public class LeastConsequtiveCardsToMatch {

    // algo.monster recommended solution.
    public static int leastConsecutiveCardsToMatch(List<Integer> cards) {
        int minLn = cards.size() + 1;
        int l = 0, r = 0;
        Map<Integer, Integer> winCt = new HashMap<>();
        while (r < cards.size()) {
            winCt.compute(cards.get(r),(n,c)->c==null?1:++c);
            while(winCt.get(cards.get(r)) == 2) {
                minLn = Math.min(minLn,1+r-l);
                winCt.compute(cards.get(l),(n,c)->c!=null&&--c>0?c:null);
                l++;
            }
            r++;
        }

        if (minLn > cards.size()) return -1;
        return minLn;
    }

    public static int leastConsecutiveCardsToMatchMySolution(List<Integer> cards) {
        int minLn = cards.size() + 1;
        int l = 0, r = 0;
        Map<Integer, Integer> windowCounts = new HashMap<>();
        while (r < cards.size()) {
            windowCounts.compute(cards.get(r),(n,c)->c==null?1:++c);
            while (containsPairs(windowCounts)) {
                minLn = Math.min(minLn,1+r-l);
                windowCounts.compute(cards.get(l),(n,c)->c!=null&&--c>0?c:null);
                l++;
            }
            r++;
        }

        if (minLn > cards.size()) {
            return -1;
        }
        return minLn;
    }

    private static boolean containsPairs(Map<Integer,Integer> windowCounts) {
        return windowCounts.entrySet().stream().filter(m->m.getValue()>1).findFirst().isPresent();
    }
}
