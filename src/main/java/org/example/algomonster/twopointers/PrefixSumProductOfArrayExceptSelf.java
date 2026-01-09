package org.example.algomonster.twopointers;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements
 * of nums except nums[i].
 *
 * Input: [1, 2, 3, 4].
 *
 * Output: [24, 12, 8, 6].
 */
public class PrefixSumProductOfArrayExceptSelf {
    public static List<Integer> productOfArrayExceptSelf(List<Integer> nums) {
        List<Integer> result = new ArrayList<>();
        int prefixProd = 1;
        for (int i=0;i<nums.size();i++) {
           result.add(prefixProd);
           prefixProd *= nums.get(i);
        }
        int suffixProd = 1;
        for (int j=nums.size()-1;j>-1;j--) {
            result.set(j,suffixProd * result.get(j));
            suffixProd *= nums.get(j);
        }
        return result;
    }
}
