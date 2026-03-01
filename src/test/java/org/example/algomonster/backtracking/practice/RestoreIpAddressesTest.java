package org.example.algomonster.backtracking.practice;

import junit.framework.TestCase;

import java.util.*;

/**
 * Tests for RestoreIpAddresses.restoreIpAddresses
 * These tests follow the problem statement and include edge cases intended to
 * challenge incorrect validation and backtracking logic (leading zeros, segment bounds,
 * length constraints, etc). Do NOT modify production logic.
 */
public class RestoreIpAddressesTest extends TestCase {

    public void testExample1_25525511135() {
        List<String> expected = Arrays.asList("255.255.11.135", "255.255.111.35");
        List<String> actual = RestoreIpAddresses.restoreIpAddresses("25525511135");
        assertListEqualsIgnoringOrder(expected, actual);
    }

    public void testExample2_0000() {
        List<String> expected = Collections.singletonList("0.0.0.0");
        List<String> actual = RestoreIpAddresses.restoreIpAddresses("0000");
        assertListEqualsIgnoringOrder(expected, actual);
    }

    public void testExample3_101023() {
        List<String> expected = Arrays.asList(
                "1.0.10.23",
                "1.0.102.3",
                "10.1.0.23",
                "10.10.2.3",
                "101.0.2.3"
        );
        List<String> actual = RestoreIpAddresses.restoreIpAddresses("101023");
        assertListEqualsIgnoringOrder(expected, actual);
    }

    public void testLeadingZerosAndMultipleOptions() {
        // Known tricky case from problem: should allow single '0' segments but not leading zeros like "00"
        List<String> expected = Arrays.asList("0.10.0.10", "0.100.1.0");
        List<String> actual = RestoreIpAddresses.restoreIpAddresses("010010");
        assertListEqualsIgnoringOrder(expected, actual);
    }

    public void testTooShortAndTooLong() {
        // length < 4 can't form an IP
        List<String> shortRes = RestoreIpAddresses.restoreIpAddresses("111");
        assertNotNull(shortRes);
        assertEquals(0, shortRes.size());

        // length > 12 can't form an IP (each segment max length 3)
        List<String> longRes = RestoreIpAddresses.restoreIpAddresses("1111111111111");
        assertNotNull(longRes);
        assertEquals(0, longRes.size());
    }

    public void testSegmentGreaterThan255Rejected() {
        // any segment >255 should not be allowed; "256" is invalid
        List<String> res = RestoreIpAddresses.restoreIpAddresses("256256256256");
        assertNotNull(res);
        assertEquals(0, res.size());
    }

    public void testInvalidLeadingZeroesProduceNoResult() {
        List<String> res = RestoreIpAddresses.restoreIpAddresses("01");
        assertNotNull(res);
        assertEquals(0, res.size());

        res = RestoreIpAddresses.restoreIpAddresses("00");
        assertNotNull(res);
        assertEquals(0, res.size());
    }

    private void assertListEqualsIgnoringOrder(List<String> expected, List<String> actual) {
        assertNotNull(actual);
        Set<String> exp = new HashSet<>(expected);
        Set<String> act = new HashSet<>(actual);
        if (!exp.equals(act)) {
            // Provide helpful diagnostics
            List<String> el = new ArrayList<>(exp);
            List<String> al = new ArrayList<>(act);
            Collections.sort(el);
            Collections.sort(al);
            fail("Expected: " + el + "\nActual:   " + al);
        }
    }
}

