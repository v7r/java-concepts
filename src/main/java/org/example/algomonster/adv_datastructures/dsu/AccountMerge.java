package org.example.algomonster.adv_datastructures.dsu;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry;

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
public class AccountMerge {
    // [anny, anny1, anny2]
    // [anny, anny3, anny2]
    // [anny, anny4, anny5]
    // Output:
    // [anny, anny1, anny2, anny3]
    // [anny, anny4, anny5]

    // [anny, anny1, anny2]
    // [anny, anny3, anny2]
    // [anny, anny4, anny5]
    // [anny, anny3, anny5]
    // Output:
    // [anny, anny1, anny2, anny3, anny4, anny5]

    // [anny, anny1, anny2]
    // [anny, anny3, anny2]
    // [anny, anny4, anny5]
    // [Nanny, anny3, anny5]
    // Output:
    // [anny, anny1, anny2, anny3]
    // [anny, anny4, anny5]
    // [Nanny, anny3, anny5]

    private static class DsuV1 {
        private Map<Entry<String,String>, Entry<String,String>> setTree = new HashMap<>();
        private Map<Entry<String,String>,Set<Entry<String, String>>> idSetToElementsMap =
                new TreeMap<>((a, b) -> a.toString().compareTo(b.toString()));

        public Entry<String,String> find(Entry<String, String> e) {
            Entry<String,String> f = setTree.getOrDefault(e, e);
            // If e has a prent then compress the tree.
            if (!f.equals(e)) {
                f = find(f); // find the parent's parent.
                setTree.put(e,f); // compressed path to parent.
            }
            return f;
        }

        public void merge(Entry<String, String> a, Entry<String, String> b) {
            Entry<String, String> aSetId = find(a);
            Entry<String, String> bSetId = find(b);
            // create union set u;
            Set<Entry<String,String>> aSetElements = idSetToElementsMap.getOrDefault(aSetId, Set.of(a));
            Set<Entry<String,String>> bSetElements = idSetToElementsMap.getOrDefault(bSetId, Set.of(b));
            Set<Entry<String,String>> u = new TreeSet<>((x, y) -> x.toString().compareTo(y.toString()));
            u.addAll(aSetElements);
            u.addAll(bSetElements);

            setTree.put(find(a), find(b));
            // parent of a will be changed and it will now hold the union set. So, remove the old parent.
            idSetToElementsMap.remove(aSetId);
            if (!aSetId.equals(bSetId)) {
                idSetToElementsMap.put(find(a), u);
            } else {
                // same set then
                idSetToElementsMap.put(find(a), aSetElements);
            }
        }

        // LEX SORT ACCOUNT AND EMAILS.
        public List<List<String>> toList() {
            List<List<String>> r = new ArrayList<>();
            for (Entry<Entry<String,String>, Set<Entry<String,String>>> e : idSetToElementsMap.entrySet()) {
                List<String> ac = new ArrayList<>();
                ac.add(e.getKey().getKey());
                ac.addAll(e.getValue().stream().map(Entry::getValue).toList());
                r.add(ac);
            }
            return r;
        }
    }


    public static List<List<String>> mergeAccounts(List<List<String>> accounts) {
        DsuV1 dsu = new DsuV1();
        for (List<String> account: accounts) {
            // merge first email;
            dsu.merge(Map.entry(account.get(0), account.get(1)),
                    Map.entry(account.get(0), account.get(1)));
            for (int i = 1; i < account.size() - 1; i++) {
                dsu.merge(Map.entry(account.get(0), account.get(i)),
                        Map.entry(account.get(0), account.get(i+1)));

            }
        }
        return dsu.toList();
    }


    ///////////////////// Attempt 2 ////////////////////////////////////////////////////////////////////////////////////
    // Blunder#4: Use of Map.Entry<String,String> instead of simple UserAccount.
    private static class UserAccount {
        public String name;
        public String email;
        public UserAccount(String name, String email) {
            this.name = name;
            this.email = email;
        }
        public boolean equals(Object o) {
            if (o instanceof UserAccount) {
                return ((UserAccount)o).name.equals(this.name) &&
                        ((UserAccount)o).email.equals(this.email);
            }
            return false;
        }
        public String toString() {
            return name+"("+email+")";
        }
        public int hashCode() {
            return this.toString().hashCode();
        }
    }

    private static class Dsu {
        private final Map<UserAccount, UserAccount> setTree = new HashMap<>();
        private final Map<UserAccount,Set<UserAccount>> idSetToElementsMap =
                new TreeMap<>((a, b) -> a.toString().compareTo(b.toString()));

        public UserAccount find(UserAccount e) {
            UserAccount f = setTree.getOrDefault(e, e);
            // If e has a prent then compress the tree.
            if (!f.equals(e)) {
                f = find(f); // find the parent's parent.
                setTree.put(e, f); // compressed path to parent.
            }
            return f;
        }

        public void merge(UserAccount a, UserAccount b) {
            UserAccount aSetId = find(a);
            UserAccount bSetId = find(b);
            //create union set u;
            //Blunder#3: Empty default set instead of a set with current element.
            Set<UserAccount> aSetElements = idSetToElementsMap.getOrDefault(aSetId, Set.of(a));
            Set<UserAccount> bSetElements = idSetToElementsMap.getOrDefault(bSetId, Set.of(b));
            // Blunder#1: Wrong logic in comparator caused wrong retrieval by key.
            Set<UserAccount> u = new TreeSet<>((x, y) -> x.email.compareTo(y.email));
            u.addAll(aSetElements);
            u.addAll(bSetElements);

            setTree.put(find(a), find(b));
            //Remove old setId of a, because new set id is created by merging a,b that hold the new union set.
            //Blunder#2: when a parent changes the old parent should be removed. Otherwise, intermediate parents end up in results computation.
            idSetToElementsMap.remove(aSetId);
            if (!aSetId.equals(bSetId)) {
                idSetToElementsMap.put(find(a), u);
            } else {
                idSetToElementsMap.put(find(a), aSetElements);
            }
        }

        // LEX SORT ACCOUNT AND EMAILS.
        public List<List<String>> toList() {
            List<List<String>> r = new ArrayList<>();
            for (Entry<UserAccount,Set<UserAccount>> e : idSetToElementsMap.entrySet()) {
                List<String> ac = new ArrayList<>();
                ac.add(e.getKey().name);
                ac.addAll(e.getValue().stream().map(e1 -> e1.email).toList());
                r.add(ac);
            }
            return r;
        }
    }


    public static List<List<String>> mergeAccountsV2(List<List<String>> accounts) {
        Dsu dsu = new Dsu();
        for (List<String> account: accounts) {
            // merge first email;
            dsu.merge(new UserAccount(account.get(0), account.get(1)),
                        new UserAccount(account.get(0), account.get(1)));
            for (int i = 1; i < account.size() - 1; i++) {
                dsu.merge(new UserAccount(account.get(0), account.get(i)),
                        new UserAccount(account.get(0), account.get(i+1)));
            }
        }
        return dsu.toList();
    }
}
