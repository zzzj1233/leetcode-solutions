package com.zzzj.review;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-03-10 17:54
 */
public class Leet34 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 4)));
    }

    public static int[] searchRange(int[] nums, int target) {

        int left = leftest(nums, target);

        if (left == -1) {
            return new int[]{-1, -1};
        }

        return new int[]{left, rightest(nums, target, left)};
    }

    public static int rightest(int[] nums, int target, int left) {
        int right = nums.length - 1;

        int result = -1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (nums[mid] <= target) {
                if (nums[mid] == target) {
                    result = mid;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    public static int leftest(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int result = -1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (nums[mid] >= target) {
                if (nums[mid] == target) {
                    result = mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

}
