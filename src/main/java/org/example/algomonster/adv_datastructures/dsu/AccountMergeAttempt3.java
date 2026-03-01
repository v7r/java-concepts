package org.example.algomonster.adv_datastructures.dsu;

import java.util.*;

/**
 * You are a social media manager, and there are a lot of users on your platform with various emails attached to their
 * accounts. For each user, there is a name and several emails associated with that user.
 *
 * You noticed that a lot of users have multiple accounts registered with the same email, and you decided to merge some
 * accounts according to the following rules:
 *
 * If two accounts have the same name and share at least one common email, they must belong to the same user, and thus
 * can be safely merged. This merged account can be merged with other accounts via the same method, so if two accounts
 * with the same name doesn't have a common email, but they each have a common email with a third account with the same
 * name, they can be merged together.
 * Two different accounts can share the same name, as long as they cannot be linked back to the same person via email
 * tracking.
 * Two different accounts with different names can never be merged, even if they may share a common email.
 * After merging the accounts such that you cannot merge any more, output the final remaining accounts in a sorted
 * order. That is, the emails of each account is sorted lexicographically, and the accounts are sorted lexicographically
 * by name, then by the first email (if applicable).
 *
 * Input
 * accounts: A matrix of strings (can be uneven). Each row represent an account, with the first entry being the name and
 * the rest being the email associated with the account.
 *
 * Output
 * A matrix of strings representing the final resulting accounts, sorted.
 *
 * Examples
 * Example 1:
 * Input:
 *
 * accounts = [
 *   ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 *   ["John", "johnsmith@mail.com", "john_work@mail.com"],
 *   ["Mary", "mary@mail.com"],
 *   ["John", "johnny@mail.com"]
 * ]
 * Output: [ ["John", "john_newyork@mail.com", "john_work@mail.com","johnsmith@mail.com"], ["John", "johnny@mail.com"],
 * ["Mary", "mary@mail.com"], ]
 *
 * Explanation:
 *
 * The first two accounts belong to the same user because they share an email. Notice that there are two John accounts
 * in the end, because they do not share emails with each other, even after merging.
 *
 * Constraints
 * 1 <= len(accounts) <= 1000
 * 1 <= len(accounts[i]) <= 10
 * 1 <= len(accounts[i][j]) <= 30
 * Names consist of only English letters
 * All emails are valid. Normally emails are case-insensitive, but to simplify things, assume all letters that appear
 * in the emails are lowercase.
 *
 */
public class AccountMergeAttempt3 {
    private record UserAccount(String name, String email) {
        public String toString() {
            return name+"("+email+")";
        }
    }

    private static class Dsu {
        private Map<UserAccount, UserAccount> treeSet = new HashMap<>();
        // This holds the set ID and list of elements.
        // This will also hold the final set.
        // Entries in this map are sorted by 'name'
        private Map<UserAccount, Set<UserAccount>> mergeSets = new HashMap<>();
        //new TreeMap<>((a, b) -> a.toString().compareTo(b.toString()));

        public UserAccount find(UserAccount a) {
            UserAccount b = treeSet.getOrDefault(a, a);
            // If a has a parent.
            if (!b.equals(a)) {
                b = find(b); // find the parent of b.
                treeSet.put(a, b); // compressed tree.
            }
            return b;
        }

        public void merge(UserAccount a, UserAccount b) {
            UserAccount aSetId = find(a);
            UserAccount bSetId = find(b);
            Set<UserAccount> aSetElements = mergeSets.getOrDefault(aSetId, Set.of(a));
            Set<UserAccount> bSetElements = mergeSets.getOrDefault(bSetId, Set.of(b));
            Set<UserAccount> unionSet = new TreeSet<>((x, y) -> x.toString().compareTo(y.toString()));
            unionSet.addAll(aSetElements);
            unionSet.addAll(bSetElements);

            treeSet.put(find(a), find(b));
            // a will have a new parent id. Erase past record of old parent.
            mergeSets.remove(aSetId);
            if (aSetId.equals(bSetId)) {
                mergeSets.put(find(a), aSetElements);
            } else {
                mergeSets.put(find(a), unionSet);
            }
        }

        public List<List<String>> toList() {
            List<List<String>> r = new ArrayList<>();
            for (Map.Entry<UserAccount, Set<UserAccount>> e : mergeSets.entrySet()) {
                List<String> account = new ArrayList<>();
                account.add(e.getKey().name());
                for (UserAccount ua : e.getValue()) {
                    account.add(ua.email());
                }
                r.add(account);
            }
            r.sort((s1, s2) -> {
                return s1.toString().compareTo(s2.toString());
            });
            return r;
        }
    }

    public static List<List<String>> mergeAccounts(List<List<String>> accounts) {
        Dsu dsu = new Dsu();
        for (List<String> account : accounts) {
            dsu.merge(
                    new UserAccount(account.get(0), account.get(1)),
                    new UserAccount(account.get(0), account.get(1))
            );
            for (int i = 1; i < account.size() - 1; i++) {
                dsu.merge(
                        new UserAccount(account.get(0), account.get(i)),
                        new UserAccount(account.get(0), account.get(i + 1))
                );
            }
        }
        return dsu.toList();
    }
}
