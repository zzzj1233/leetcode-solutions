package com.zzzj.arr;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-04-21 16:48
 */
public class Leet2616 {

    public static void main(String[] args) {

        System.out.println(minimizeMax(new int[]{1, 1, 2, 3, 7, 10}, 2));

        System.out.println(minimizeMax(new int[]{4, 2, 1, 2}, 2));

    }

    public static int minimizeMax(int[] nums, int p) {

        int N = nums.length;

        Arrays.sort(nums);

        int left = 0;

        int right = nums[N - 1];

        int result = 0;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if (can(nums, p, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public static boolean can(int[] nums, int p, int expect) {

        int N = nums.length;

        int cnt = 0;

        for (int i = 1; i < N; i++) {

            if (nums[i] - nums[i - 1] <= expect) {
                cnt++;
                i++;
            }

            if (cnt == p) {
                return true;
            }
        }

        return false;
    }

}
