package org.example.algomonster.adv_datastructures.dsu;

import junit.framework.TestCase;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Tests for AccountMerge.mergeAccounts per the problem statement.
 * These tests are designed to challenge the implementation (transitive merges, name-sensitive merging,
 * duplicate emails, ordering requirements, empty inputs).
 * Do NOT modify production logic.
 */
public class AccountMergeAttempt3Test extends TestCase {

    public void testExampleFromPrompt() {
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john_work@mail.com"),
                Arrays.asList("Mary", "mary@mail.com"),
                Arrays.asList("John", "johnny@mail.com")
        );

        List<List<String>> expected = Arrays.asList(
                Arrays.asList("John", "john_newyork@mail.com", "john_work@mail.com","johnsmith@mail.com"),
                Arrays.asList("John", "johnny@mail.com"),
                Arrays.asList("Mary", "mary@mail.com")
        );

        List<List<String>> out = AccountMergeAttempt3.mergeAccounts(accounts);
        assertAccountsEqualIgnoringOrder(expected, out);
    }

    public void testTransitiveMerge() {
        // a shares with b, b shares with c -> all three merge
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("A", "a1@mail.com", "a2@mail.com"),
                Arrays.asList("A", "a2@mail.com", "a3@mail.com"),
                Arrays.asList("A", "a3@mail.com", "a4@mail.com")
        );
        List<List<String>> expected = Collections.singletonList(
                Arrays.asList("A", "a1@mail.com","a2@mail.com","a3@mail.com","a4@mail.com")
        );
        List<List<String>> out = AccountMergeAttempt3.mergeAccounts(accounts);
        assertAccountsEqualIgnoringOrder(expected, out);
    }

    public void testDifferentNamesNotMergedEvenIfEmailsOverlap() {
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("Bob", "shared@mail.com"),
                Arrays.asList("Rob", "shared@mail.com")
        );
        // Should remain two separate accounts because names differ
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("Bob", "shared@mail.com"),
                Arrays.asList("Rob", "shared@mail.com")
        );
        List<List<String>> out = AccountMergeAttempt3.mergeAccounts(accounts);
        assertAccountsEqualIgnoringOrder(expected, out);
    }

    public void testDuplicateEmailsWithinAccountAreHandled() {
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("D", "x@mail.com", "x@mail.com", "y@mail.com")
        );
        List<List<String>> expected = Collections.singletonList(
                Arrays.asList("D", "x@mail.com", "y@mail.com")
        );
        List<List<String>> out = AccountMergeAttempt3.mergeAccounts(accounts);
        assertAccountsEqualIgnoringOrder(expected, out);
    }

    public void testSingleAccountAndEmptyInput() {
        List<List<String>> single = Collections.singletonList(
                Arrays.asList("Solo", "solo@mail.com")
        );
        assertAccountsEqualIgnoringOrder(single, AccountMergeAttempt3.mergeAccounts(single));

        List<List<String>> empty = Collections.emptyList();
        assertAccountsEqualIgnoringOrder(empty, AccountMergeAttempt3.mergeAccounts(empty));
    }

    // Helper: compare account lists ignoring order of accounts but requiring each account to have
    // sorted emails and the account name preserved. The expected input should follow the problem statement
    // shape: [name, email1, email2, ...]
    private void assertAccountsEqualIgnoringOrder(List<List<String>> expected, List<List<String>> actual) {
        // Normalize: map each account to "name|email1,email2,..." with emails sorted and unique
        Set<String> es = expected.stream().map(this::normalizeAccount).collect(Collectors.toSet());
        Set<String> as = actual == null ? Collections.emptySet() : actual.stream().map(this::normalizeAccount).collect(Collectors.toSet());
        if (!es.equals(as)) {
            List<String> el = new ArrayList<>(es);
            List<String> al = new ArrayList<>(as);
            Collections.sort(el);
            Collections.sort(al);
            fail("Accounts differ.\nExpected: " + el + "\nActual:   " + al);
        }
    }

    private String normalizeAccount(List<String> acct) {
        if (acct == null || acct.isEmpty()) return "";
        String name = acct.get(0);
        Set<String> emails = new TreeSet<>();
        for (int i = 1; i < acct.size(); i++) emails.add(acct.get(i));
        String joined = String.join(",", emails);
        return name + "|" + joined;
    }
}

