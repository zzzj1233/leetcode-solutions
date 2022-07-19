package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2021-10-29 14:22
 */
public class Leet416 {


    public static void main(String[] args) {
        // [3,3,3,4,5]
        System.out.println(canPartition(new int[]{100}));
    }

    /**
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3,5]
     * 输出：false
     */
    public static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();

        if ((sum & 1) == 1) {
            return false;
        }

        int half = sum / 2;

        int N = nums.length;

        boolean[][] dp = new boolean[N][half + 1];

        if (nums[0] <= half) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < N; i++) {

            int num = nums[i];

            if (num <= half) {
                dp[i][num] = true;
            }

            for (int j = 0; j <= half; j++) {
                if (dp[i - 1][j] && num + j <= half) {
                    dp[i][num + j] = true;
                }
            }

            for (int j = 0; j <= half; j++) {
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[N - 1][half];
    }

}
