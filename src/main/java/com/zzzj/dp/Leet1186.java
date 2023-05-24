package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-05-24 16:21
 */
public class Leet1186 {

    public static void main(String[] args) {

        System.out.println(maximumSum(new int[]{1, -2, 0, 3}));

        System.out.println(maximumSum(new int[]{1, -2, -2, 3}));

        System.out.println(maximumSum(new int[]{-1, -1, -1, -1}));

        System.out.println(maximumSum(new int[]{2, 1, -2, -5, -2}));

    }


    public static int maximumSum(int[] arr) {

        int N = arr.length;

        if (N == 1) {
            return arr[0];
        }

        int[][] dp = new int[N][2];

        final int ALLOW = 0;
        final int USED = 1;

        dp[0][ALLOW] = arr[0];
        dp[0][USED] = Math.max(0, arr[0]);

        int ans = dp[0][USED] == 0 ? Integer.MIN_VALUE : dp[0][USED];

        for (int i = 1; i < N; i++) {

            int num = arr[i];

            dp[i][ALLOW] = Math.max(dp[i - 1][ALLOW] + num, num);

            dp[i][USED] = Math.max(
                    dp[i - 1][USED] + num,
                    dp[i - 1][ALLOW]
            );

            ans = Math.max(
                    ans,
                    Math.max(
                            dp[i][ALLOW],
                            dp[i][USED]
                    )
            );
        }

        return ans;
    }

}
