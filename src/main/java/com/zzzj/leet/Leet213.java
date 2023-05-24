package com.zzzj.leet;


import com.zzzj.util.ArrayUtil;

import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * @author zzzj
 * @create 2021-10-28 14:15
 */
public class Leet213 {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            final int[] arr = ArrayUtil.generateArray(1000, 0, 100);
            if (rob(arr) != right(arr)) {
                System.out.println("Error");
                return;
            }
        }
        System.out.println("Ok");
    }

    // 最后一个和第一个互斥
    // 2,3,2
    public static int rob(int[] nums) {
        int N = nums.length;

        if (N == 1) {
            return nums[0];
        }

        if (N == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[N];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < N - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        int x = dp[N - 2];

        dp[1] = nums[1];
        dp[2] = Math.max(nums[1], nums[2]);

        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        int y = dp[N - 1];

        return Math.max(x, y);
    }

    public static int right(int[] nums) {
        int end = nums.length - 1;
        if (end == 0) return nums[0];
        if (end == 1) return Math.max(nums[0], nums[1]);
        return Math.max(dp(nums, 0, end - 1), dp(nums, 1, end));
    }

    private static int dp(int[] nums, int start, int end) {
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }

}
