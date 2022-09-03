package com.zzzj.dp;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-08-29 16:49
 */
public class Leet1746 {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            final int[] arr = ArrayUtil.generateArray(100, -200, 1000);
            if (maxSumAfterOperation(arr) != right(arr)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int maxSumAfterOperation(int[] nums) {
        int N = nums.length;

        if (N == 0) {
            return 0;
        }

        int[][] dp = new int[N][2];

        dp[0][0] = nums[0];
        dp[0][1] = nums[0] * nums[0];

        int ans = Integer.MIN_VALUE;

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(nums[i], nums[i] + dp[i - 1][0]);
            dp[i][1] = Math.max(Math.max(dp[i][0], nums[i] + dp[i - 1][1]), Math.max(nums[i] * nums[i], nums[i] * nums[i] + dp[i - 1][0]));
            ans = Math.max(Math.max(ans, dp[i][0]), dp[i][1]);
        }

        return ans;
    }

    public static int right(int[] nums) {
        int n = nums.length, res = Integer.MIN_VALUE;
        int[][] f = new int[n][2];
        f[0][0] = nums[0];
        f[0][1] = nums[0] * nums[0];
        res = Math.max(res, Math.max(f[0][0], f[0][1]));
        for (int i = 1; i < n; i++) {
            f[i][1] = Math.max(f[i - 1][1] + nums[i], Math.max(0, f[i - 1][0]) + nums[i] * nums[i]);
            f[i][0] = Math.max(0, f[i - 1][0]) + nums[i];
            res = Math.max(res, Math.max(f[i][0], f[i][1]));
        }
        return res;
    }

}
