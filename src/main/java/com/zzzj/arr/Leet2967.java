package com.zzzj.arr;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-12-27 16:57
 */
public class Leet2967 {

    public static void main(String[] args) {

        System.out.println(minimumCost(new int[]{301, 309, 312, 322}));

//        System.out.println(minimumCost(new int[]{1, 2, 3, 4, 5}));
//
//        System.out.println(minimumCost(new int[]{10, 12, 13, 14, 15}));

    }

    public static long minimumCost(int[] nums) {

        int N = nums.length;

        Arrays.sort(nums);

        long sum = Arrays.stream(nums).sum();

        int mid = N / 2;

        int midNum = nums[N / 2];

        long ans = Long.MAX_VALUE;

        int len = String.valueOf(midNum).length();

        int left = midNum;

        long[] preSum = new long[N + 1];

        for (int i = 1; i <= N; i++)
            preSum[i] = preSum[i - 1] + nums[i - 1];

        while (!isP(String.valueOf(left))) {
            left--;
        }

        ans = Math.min(ans, check(nums, left, preSum));

        int right = midNum;

        while (!isP(String.valueOf(right))) {
            right++;
        }

        ans = Math.min(ans, check(nums, right, preSum));

        return ans;
    }

    private static long check(int[] nums, long value, long[] preSum) {
        int left = 0;
        int right = nums.length - 1;
        int find = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] <= value) {
                find = mid;
                left++;
            } else {
                right--;
            }
        }

        find++;

        long leftSum = value * find - preSum[find];
        long rightSum = preSum[nums.length] - preSum[find] - value * (nums.length - find);
        return leftSum + rightSum;
    }

    private static boolean isP(String value) {
        int left = 0;
        int right = value.length() - 1;
        while (left < right) {
            if (value.charAt(left) != value.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }


}
