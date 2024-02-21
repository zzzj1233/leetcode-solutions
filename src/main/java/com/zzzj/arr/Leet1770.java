package com.zzzj.arr;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-01-26 15:53
 */
public class Leet1770 {

    public static void main(String[] args) {

        System.out.println(maximumScore(new int[]{3, 3, 2}, new int[]{2, 3, 3}));

//        System.exit(0);

        int N = 3000;

        int M = 300;

        for (int i = 0; i < 10000; i++) {

            int[] nums = ArrayUtil.generateArray(N, 1, 300);

            int[] multipliers = ArrayUtil.generateArray(M, 1, 300);

            int r = maximumScore(nums, multipliers);

            int rr = right(nums, multipliers);

            if (r != rr) {
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("multipliers = " + Arrays.toString(multipliers));
                return;
            }

        }

        System.out.println("Ok");

    }

    public static int maximumScore(int[] nums, int[] multipliers) {

        int N = nums.length;

        int M = multipliers.length;

        int[][] f = new int[M + 1][M + 1];

        for (int i = 1; i <= M; i++) {

            int mul = multipliers[i - 1];

            f[i][0] = f[i - 1][0] + nums[N - i] * mul;

            f[i][i] = f[i - 1][i - 1] + nums[i - 1] * mul;

            // 枚举左边选left个
            for (int left = 1; left < i; left++) {

                // 上一次右边选了right个
                int right = i - 1 - left;

                f[i][left] = Math.max(
                        f[i - 1][left - 1] + nums[left - 1] * mul,
                        f[i - 1][left] + nums[N - right - 1] * mul
                );

            }

        }

        return Arrays.stream(f[M]).max().orElse(0);
    }

    public static int right(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        int[][] dp = new int[m + 1][m + 1];
        dp[1][0] = nums[0] * multipliers[0];
        dp[0][1] = nums[nums.length - 1] * multipliers[0];
        for (int i = 2; i <= m; i++) {
            int mul = multipliers[i - 1];
            for (int l = 0; l <= i; l++) {
                int r = i - l;
                int nums_index = nums.length - (i - l);
                if (l == 0) {
                    dp[l][r] = dp[l][r - 1] + mul * nums[nums_index];
                    continue;
                }
                if (r == 0) {
                    dp[l][r] = dp[l - 1][r] + mul * nums[l - 1];
                    continue;
                }
                dp[l][r] = dp[l - 1][r] + mul * nums[l - 1];
                dp[l][r] = Math.max(dp[l][r], dp[l][r - 1] + mul * nums[nums_index]);
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i <= m; i++) {
            ans = Math.max(dp[i][m - i], ans);
        }
        return ans;
    }

}
