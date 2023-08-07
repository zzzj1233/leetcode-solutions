package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-04 17:52
 */
public class Leet2563 {

    public static void main(String[] args) {

        System.out.println(countFairPairs(new int[]{0, 1, 7, 4, 4, 5}, 3, 6));

        System.out.println(countFairPairs(new int[]{1, 7, 9, 2, 5}, 11, 11));

        System.out.println(countFairPairs(new int[]{-5, -7, -5, -7, -5}, -12, -12));

    }

    public static long countFairPairs(int[] nums, int lower, int upper) {

        Arrays.sort(nums);

        long ans = 0;

        int N = nums.length;

        for (int i = 0; i < N; i++) {
            ans += search(nums, lower - nums[i], upper - nums[i], i - 1);
        }

        return ans;
    }

    public static int search(int[] nums, int low, int up, int R) {
        // 先找up
        int left = 0;
        int right = R;
        int rightIndex = -1;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (nums[mid] <= up) {
                rightIndex = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        if (rightIndex == -1) return 0;

        int leftIndex = -1;

        left = 0;
        right = R;

        while (left <= right) {

            int mid = left + ((right - left) >> 1);

            if (nums[mid] >= low) {
                leftIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        if (leftIndex == -1) return 0;
        return rightIndex - leftIndex + 1;
    }

}
