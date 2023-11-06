package com.zzzj.greedy;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-11-02 16:14
 */
public class Leet1449 {

    public static void main(String[] args) {

        System.out.println(largestNumber(new int[]{4, 3, 2, 5, 6, 7, 2, 5, 5}, 9));

        System.out.println(largestNumber(new int[]{6, 10, 15, 40, 40, 40, 40, 40, 40}, 47));

        System.out.println(largestNumber(new int[]{7, 6, 5, 5, 5, 6, 8, 7, 8}, 12));

    }

    public static String largestNumber(int[] cost, int target) {

        int N = cost.length;

        int[][] dp = new int[N + 1][target + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= N; i++) {

            dp[i] = Arrays.copyOfRange(dp[i - 1], 0, dp[i - 1].length);

            int c = cost[i - 1];

            for (int j = c; j <= target; j++) {

                dp[i][j] = Math.max(
                        dp[i][j - c] > 0 ? dp[i][j - c] + 1 : 0,
                        dp[i][j]
                );

            }
        }

        int expect = dp[N][target];

        if (expect == 0)
            return "0";

        StringBuilder builder = new StringBuilder(expect - 1);

        int end = target;

        for (int i = N; i > 0; i--) {

            int c = cost[i - 1];

            for (int j = end; j >= 0; ) {

                if (j - c >= 0 && dp[i][j - c] + 1 == expect) {
                    builder.append(i);
                    j -= c;
                    expect--;
                    end -= c;
                } else {
                    break;
                }

            }

        }

        return builder.toString();
    }

}
