package org.example.algomonster.binarysearch;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly left rotated at an unknown
 * index k (1 <= k < nums.length) such that the resulting array is
 * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of
 * target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class FindInRotatedSortedArray {

    //4,5,6,7,0,1,2  target 6
    //1,2,-2,-1,0 target 2
    //3,4,0,1,2 target 4
    //1,2,3,4,5 target 1
    public int searchPractice(int nums[], int target) {
        int k = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right -left)/2;
            if (nums[mid] == target) return mid;
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return k;
    }

    public int searchAi(int nums[], int target) {
        if (nums == null || nums.length == 0) return -1;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;

            // Determine which side is sorted
            if (nums[low] <= nums[mid]) {
                // Left side [low..mid] is sorted
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // Right side [mid..high] is sorted
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    // This is alternative solution where I initially discovered.
    // First find the lowest item location and call it pivot
    // Now split the array into two at the pivot. Both of these arrays are sorted.
    // Find the array that contains the target and do the binary search in it.
    private int findLowest(int nums[]) {
        int k = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (nums[mid] <= nums[nums.length-1]) {
                k = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return k;
    }

    private int binarySearch(int nums[], int start, int end, int target) {
        int k = -1;
        int left = start, right = end;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (nums[mid] >= target) {
                if (target == nums[mid]) k = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return k;
    }
    // 4,5,6,7,0,1,2 target 6

    public int search(int nums[], int target) {
        int pivot = findLowest(nums);
        if (nums[pivot] == target) {
            return pivot;
        } else if (pivot == 0 || pivot == nums.length - 1) {
            return binarySearch(nums,0, nums.length - 1, target);
        } else if (target >= nums[0] && target <= nums[pivot-1]) {
            return binarySearch(nums,0,pivot-1, target);
        } else {
            return binarySearch(nums,pivot,nums.length-1, target);
        }
    }
}
