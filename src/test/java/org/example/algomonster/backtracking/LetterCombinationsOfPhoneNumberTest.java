package org.example.algomonster.backtracking;

import junit.framework.TestCase;
import org.example.algomonster.backtracking.A_combinatorialsearch.LetterCombinationsOfPhoneNumber;

import java.util.Arrays;
import java.util.List;

/**
 * Tests for LetterCombinationsOfPhoneNumber.
 * These tests aim to exercise normal inputs and edge cases that the current implementation
 * may not defensively handle (nulls, invalid digits like 0/1, non-digit characters),
 * and to validate ordering and content for typical phone-digit inputs.
 *
 * Do not modify production logic; tests assert current behavior (including thrown exceptions)
 * so regressions or defensive improvements will be caught.
 */
public class LetterCombinationsOfPhoneNumberTest extends TestCase {

    public void testExample56() {
        List<String> expected = Arrays.asList("jm","jn","jo","km","kn","ko","lm","ln","lo");
        List<String> result = LetterCombinationsOfPhoneNumber.letterCombinationsOfPhoneNumber("56");
        assertEquals(expected, result);
    }

    public void test23ProducesNineLowercaseCombinationsInOrder() {
        List<String> expected = Arrays.asList("ad","ae","af","bd","be","bf","cd","ce","cf");
        List<String> result = LetterCombinationsOfPhoneNumber.letterCombinationsOfPhoneNumber("23");
        assertEquals(expected, result);
    }

    public void testSingleDigit2ProducesThree() {
        List<String> expected = Arrays.asList("a","b","c");
        List<String> result = LetterCombinationsOfPhoneNumber.letterCombinationsOfPhoneNumber("2");
        assertEquals(expected, result);
    }

    public void testEmptyStringReturnsListWithEmptyString() {
        List<String> result = LetterCombinationsOfPhoneNumber.letterCombinationsOfPhoneNumber("");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("", result.get(0));
    }

    public void testNullInputThrowsNPE() {
        try {
            LetterCombinationsOfPhoneNumber.letterCombinationsOfPhoneNumber(null);
            fail("Expected NullPointerException for null input");
        } catch (NullPointerException expected) {
            // expected - implementation dereferences digits
        }
    }

    public void testDigitOneCausesNPEBecauseNoMappingExists() {
        try {
            LetterCombinationsOfPhoneNumber.letterCombinationsOfPhoneNumber("1");
            fail("Expected NullPointerException for digit '1' because it has no mapping");
        } catch (NullPointerException expected) {
            // expected - dcMap.get(1) returns null and code dereferences it
        }
    }

    public void testNonDigitCharacterCausesNumberFormatException() {
        try {
            LetterCombinationsOfPhoneNumber.letterCombinationsOfPhoneNumber("2a");
            fail("Expected NumberFormatException for non-digit character");
        } catch (NumberFormatException expected) {
            // expected - parseInt on 'a' should fail
        }
    }

    public void test79ProducesSixteenAndOrdering() {
        List<String> result = LetterCombinationsOfPhoneNumber.letterCombinationsOfPhoneNumber("79");
        assertNotNull(result);
        assertEquals(16, result.size());
        assertEquals("pw", result.get(0)); // P + W -> pw
        assertEquals("sz", result.get(result.size() - 1)); // S + Z -> sz
    }

    public void testReturnedListIsIndependentAcrossCalls() {
        List<String> first = LetterCombinationsOfPhoneNumber.letterCombinationsOfPhoneNumber("2");
        first.add("zzz");
        List<String> second = LetterCombinationsOfPhoneNumber.letterCombinationsOfPhoneNumber("2");
        assertFalse("Modification of previous result should not affect new call", second.contains("zzz"));
    }
}

