package com.zzzj.window;

/**
 * @author zzzj
 * @create 2023-12-26 14:50
 */
public class Leet2972 {

    public static void main(String[] args) {

        System.out.println(incremovableSubarrayCount(new int[]{1, 2, 3, 4}));

        System.out.println(incremovableSubarrayCount(new int[]{6, 5, 7, 8}));

        System.out.println(incremovableSubarrayCount(new int[]{4, 6, 5, 7, 8}));

    }

    public static long incremovableSubarrayCount(int[] nums) {

        int N = nums.length;

        int rightLimit = N - 1;

        while (rightLimit - 1 >= 0 && nums[rightLimit - 1] < nums[rightLimit])
            rightLimit--;

        if (rightLimit == 0)
            return (long) N * (N + 1) / 2;

        int left = 0;

        while (left + 1 < rightLimit && nums[left] < nums[left + 1])
            left++;

        long ans = left + 2;

        int right = N - 2;

        while (right >= rightLimit - 1) {

            while (left >= 0 && nums[left] >= nums[right + 1]) {
                left--;
            }

            ans += left + 2;

            right--;
        }

        return ans;
    }

}
