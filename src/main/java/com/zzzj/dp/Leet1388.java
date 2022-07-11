package com.zzzj.dp;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-07-05 17:32
 */
public class Leet1388 {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            final int[] arr = ArrayUtil.generateArray(9, 0, 10);
            if (maxSizeSlices(arr) != right(arr)) {
                System.out.println("Error");
                System.out.println(Arrays.toString(arr));
                System.out.println(maxSizeSlices(arr));
                System.out.println(right(arr));
                return;
            }
        }
        System.out.println("Ok");
    }

    public static int maxSizeSlices(int[] slices) {
        int N = slices.length;
        int maxChoose = N / 3;
        return Math.max(dp(slices, 0, N - 2, maxChoose), dp(slices, 1, N - 1, maxChoose));
    }

    public static int dp(int[] slices, int index, int end, int maxChoose) {
        int N = end - index + 1;
        int[][] dp = new int[N][maxChoose + 1];

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 1; j <= maxChoose; j++) {
                dp[i][j] = Math.max(pick(dp, i + 1, j), slices[i + index] + pick(dp, i + 2, j - 1));
            }
        }

        return dp[0][maxChoose];
    }

    public static int pick(int[][] dp, int i, int j) {
        if (i >= dp.length) {
            return 0;
        }
        return dp[i][j];
    }

    public static int dfs(int[] slices, int index, int end, int maxChoose) {
        if (maxChoose == 0 || index > end) {
            return 0;
        }

        return Math.max(
                dfs(slices, index + 1, end, maxChoose),
                slices[index] + dfs(slices, index + 2, end, maxChoose - 1)
        );
    }


    public static int right(int[] slices) {
        int length = slices.length;
        int picks = length / 3;
        // dp[i][j][s] 表示前i块披萨，拿了j块，最大值是多少，s表示第i块披萨是要还是不要
        int[][][] dp = new int[length][picks + 1][2];

        // 分第一块要和第一块不要两种情况
        int result = 0;
        // 第一块要
        dp[0][1][1] = slices[0];
        for (int i = 1; i < length; ++i) {
            if (i == length - 1) {
                // 对于最后一块pisa
                // 第一块要了，那么它必须不能要
                continue;
            }

            for (int j = 1; j <= picks; ++j) {
                // 对于第二块，只能选择不要
                if (i == 1) {
                    dp[i][j][0] = dp[i - 1][j][1];
                    continue;
                }
                // 对于第i块披萨，如果要，那么最大值为i-1不要，拿了j-1块
                dp[i][j][1] = dp[i - 1][j - 1][0] + slices[i];
                // 如果不要，那么最大值为i-1不要，拿了j块，或i-1要，拿了j块
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1]);
            }
            result = Math.max(result, Math.max(dp[i][picks][1], dp[i][picks][0]));
        }

        // 第一块不要
        dp[0][1][1] = 0;
        // 第二块可以要，也可以不要，所以不需要特殊处理
        for (int i = 1; i < length; ++i) {
            for (int j = 1; j <= picks; ++j) {
                // 对于第i块披萨，如果要，那么最大值为i-1不要，拿了j-1块
                dp[i][j][1] = dp[i - 1][j - 1][0] + slices[i];
                // 如果不要，那么最大值为i-1不要，拿了j块，或i-1要，拿了j块
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1]);
            }
            result = Math.max(result, Math.max(dp[i][picks][1], dp[i][picks][0]));
        }
        return result;
    }

}
