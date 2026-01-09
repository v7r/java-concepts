package org.example.algomonster.twopointers;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrefixSumProductOfArrayExceptSelfTest extends TestCase {

    public void testExample() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> expected = Arrays.asList(24, 12, 8, 6);
        assertEquals(expected, PrefixSumProductOfArrayExceptSelf.productOfArrayExceptSelf(nums));
    }

    public void testSingleZero() {
        List<Integer> nums = Arrays.asList(0, 1, 2, 3);
        List<Integer> expected = Arrays.asList(6, 0, 0, 0);
        assertEquals(expected, PrefixSumProductOfArrayExceptSelf.productOfArrayExceptSelf(nums));
    }

    public void testMultipleZeros() {
        List<Integer> nums = Arrays.asList(0, 1, 0, 3);
        List<Integer> expected = Arrays.asList(0, 0, 0, 0);
        assertEquals(expected, PrefixSumProductOfArrayExceptSelf.productOfArrayExceptSelf(nums));
    }

    public void testNegatives() {
        List<Integer> nums = Arrays.asList(-1, 2, -3, 4);
        List<Integer> expected = Arrays.asList(-24, 12, -8, 6);
        assertEquals(expected, PrefixSumProductOfArrayExceptSelf.productOfArrayExceptSelf(nums));
    }

    public void testSingleElement() {
        List<Integer> nums = Collections.singletonList(5);
        List<Integer> expected = Collections.singletonList(1); // product of empty set = 1
        assertEquals(expected, PrefixSumProductOfArrayExceptSelf.productOfArrayExceptSelf(nums));
    }

    public void testSingleZeroOnly() {
        List<Integer> nums = Collections.singletonList(0);
        List<Integer> expected = Collections.singletonList(1);
        assertEquals(expected, PrefixSumProductOfArrayExceptSelf.productOfArrayExceptSelf(nums));
    }

    public void testEmptyList() {
        List<Integer> nums = Collections.emptyList();
        List<Integer> expected = Collections.emptyList();
        assertEquals(expected, PrefixSumProductOfArrayExceptSelf.productOfArrayExceptSelf(nums));
    }

    public void testOnesAndTwos() {
        List<Integer> nums = Arrays.asList(1, 1, 2, 2);
        List<Integer> expected = Arrays.asList(4, 4, 2, 2);
        assertEquals(expected, PrefixSumProductOfArrayExceptSelf.productOfArrayExceptSelf(nums));
    }
}

/**
Solution Explanation
 For each position in the result array, we need the product of all numbers except the one at that position. Consider the
 array [1, 2, 3, 4]. At position 2 (the number 3), we want 1 × 2 × 4 = 8. At position 0 (the number 1), we want
 2 × 3 × 4 = 24.

 The pattern becomes clear when we split each product into left and right parts. For position 2, the left part is 1 × 2
 (everything before 3) and the right part is 4 (everything after 3). Multiplying these gives us the answer:
 (1 × 2) × 4 = 8.

 Building the prefix products
 We start by filling the result array with prefix products. At each position i, we store the product of all elements to
 the left of i. For the array [1, 2, 3, 4]:

 Position 0 has nothing to its left, so we use 1. Position 1 has only 1 to its left, so we store 1. Position 2 has 1 × 2
 to its left, so we store 2. Position 3 has 1 × 2 × 3 to its left, so we store 6.

 The result array now contains [1, 1, 2, 6]. These are the left products for each position.

 Multiplying by suffix products
 Next, we traverse the array from right to left, maintaining a running product of elements we've seen so far. At each
 position, we multiply the current result value (the prefix product) by this running product (the suffix product).

 Starting from the right with a suffix product of 1:

 Position 3: suffix is 1 (nothing to the right), so result[3] = 6 × 1 = 6. Position 2: suffix is 4(only 4 to the right),
 so result[2] = 2 × 4 = 8. Position 1: suffix is 4 × 3 = 12 (both 3 and 4 to the right), so result[1] = 1 × 12 = 12.
 Position 0: suffix is 4 × 3 × 2 = 24 (all three to the right), so result[0] = 1 × 24 = 24.

 The final result is [24, 12, 8, 6]. Each position contains the product of all elements except itself, computed by
 multiplying its prefix product by its suffix product.

 Why not use division
 We could compute the total product and divide by each element. However, this fails when the array contains zeros. If
 nums[i] = 0, division by zero is undefined. The prefix-suffix approach handles zeros correctly because it never
 divides—it only multiplies.

 Complexity
 Time: O(n). We traverse the array twice, once for prefix products and once for suffix products.

 Space: O(1) if we don't count the output array. We use only a constant amount of extra space for the running product
 variable.

 */

