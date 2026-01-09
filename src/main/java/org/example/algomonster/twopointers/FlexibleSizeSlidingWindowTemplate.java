package org.example.algomonster.twopointers;

import java.util.List;

/**
 * Flexible Size Sliding Window Template - Longest
 * Observe that to find the longest (maximum size) subarray, the condition within the window is naturally satisfied.
 * That is, before we process the array when left == right == 0, the empty array [] is a valid longest subarray during
 * that stage. In the above example, the empty subarray satisfies the constraint that sum <= target To extend the
 * window, we wish to increment the right pointer. This could in fact break the constraint of a valid window, so we
 * will need to increment the left pointer until the window becomes valid again. Additionally, only invalid windows go
 * into the while loop, which means when the window exits the while loop, it must be valid. At the beginning of each
 * for loop iteration, the window is valid, and at the very end of each iteration, the window is still valid. This is
 * what we call the "invariant" - the condition(s) that holds before and after the loop. Using this property, we can
 * update ans at the end of each for loop iteration, as the window is guaranteed to be valid there.
 *
 * To find the longest subarray, we move the left pointer as little as possible. Here is the template.
 */
public class FlexibleSizeSlidingWindowTemplate<T,W> {
    private W slidingWindowFlexibleLongest(List<T> input) {
//        initialize window, ans
//        int left = 0;
//        for (int right = 0; right < input.size(); ++right) {
//            //append input.get(right) to window
//            while (invalid(window)) {         // update left until window is valid again
//                remove input.get(left) from window
//                ++left;
//            }
//            ans = max(ans, window);           // window is guaranteed to be valid here
//        }
//        return ans;


        return null; // not part of the template
    }
}
