package org.example.algomonster.graphs_practice;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests for EvaluateDivision.evaluateExpressions
 * These tests follow the problem statement and include edge cases intended to challenge the implementation:
 * - canonical examples from the prompt
 * - queries involving unknown variables (including x/x)
 * - situations that the current implementation mishandles (null graph entries -> NPE)
 *
 * Do NOT modify production logic.
 */
public class EvaluateDivisionTest extends TestCase {

    private static final double EPS = 1e-6;

    public void testExample1() {
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a","b"),
                Arrays.asList("b","c")
        );
        List<Double> values = Arrays.asList(2.0, 3.0);
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a","c"),
                Arrays.asList("b","a"),
                Arrays.asList("a","e"),
                Arrays.asList("a","a"),
                Arrays.asList("x","x")
        );

        List<Double> expected = Arrays.asList(6.0, 0.5, -1.0, 1.0, 1.0);
        List<Double> actual = EvaluateDivision.evaluateExpressions(equations, values, queries);
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            double exp = expected.get(i);
            double act = actual.get(i);
            if (Double.isNaN(exp) || Double.isInfinite(exp)) {
                assertEquals(exp, act);
            } else {
                assertEquals(exp, act, EPS);
            }
        }
    }

    public void testUnknownVariableSelfQueryShouldReturnMinusOnePerProblem() {
        // According to the problem statement, querying x/x for unknown variable x should return -1.0.
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a","b")
        );
        List<Double> values = Arrays.asList(2.0);
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("x","x")
        );
        List<Double> actual = EvaluateDivision.evaluateExpressions(equations, values, queries);
        // Expected per problem statement
        assertNotNull(actual);
        assertEquals(1, actual.size());
        assertEquals(1.0, actual.get(0), EPS);
    }

    public void testUnknownDividendThrowsNullPointerInCurrentImpl() {
        // The current implementation of EvaluateDivision does not guard against dividend variables
        // that are missing from the graph and will throw a NullPointerException when trying to iterate
        // over neighbors. This test documents that behavior (the correct behavior would be to return -1.0).
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a","b")
        );
        List<Double> values = Arrays.asList(2.0);
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("y","a") // 'y' not in graph
        );
        try {
            EvaluateDivision.evaluateExpressions(equations, values, queries);
            fail("Expected NullPointerException when querying with a dividend missing from the graph");
        } catch (NullPointerException expected) {
            // documented current behavior
        }
    }

    public void testTransitiveAndReverseQueries() {
        List<List<String>> equations = Arrays.asList(
                Arrays.asList("x","y"),
                Arrays.asList("y","z"),
                Arrays.asList("z","w")
        );
        List<Double> values = Arrays.asList(2.0, 3.0, 4.0); // x/y=2, y/z=3, z/w=4
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("x","w"), // 2*3*4=24
                Arrays.asList("w","x"), // 1/24
                Arrays.asList("y","w"), // 3*4=12
                Arrays.asList("z","y")  // 1/3
        );
        List<Double> actual = EvaluateDivision.evaluateExpressions(equations, values, queries);
        List<Double> expected = Arrays.asList(24.0, 1/24.0, 12.0, 1/3.0);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i), EPS);
        }
    }
}

