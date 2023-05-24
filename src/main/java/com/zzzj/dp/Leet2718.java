package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-05-24 15:55
 */
public class Leet2718 {

    public static void main(String[] args) {

        System.out.println(minimumOperations("rrryyyrryyyrr"));

        System.out.println(minimumOperations("ryr"));

        System.out.println(minimumOperations("yry"));

    }

    // yry
    public static int minimumOperations(String leaves) {

        // 调整为: RYR

        int N = leaves.length();

        int[][] dp = new int[N][3];

        final int R1 = 0;
        final int Y = 1;
        final int R2 = 2;

        dp[N - 1][R2] = leaves.charAt(N - 1) == 'r' ? 0 : 1;
        dp[N - 1][Y] = Integer.MAX_VALUE;
        dp[N - 1][R1] = Integer.MAX_VALUE;

        dp[N - 2][R2] = (leaves.charAt(N - 2) == 'r' ? 0 : 1) + dp[N - 1][R2];
        dp[N - 2][Y] = (leaves.charAt(N - 2) == 'y' ? 0 : 1) + dp[N - 1][R2];
        dp[N - 2][R1] = Integer.MAX_VALUE;

        for (int i = N - 3; i >= 0; i--) {
            char c = leaves.charAt(i);

            dp[i][R2] = (c == 'r' ? 0 : 1) + dp[i + 1][R2];
            dp[i][Y] = (c == 'y' ? 0 : 1) + Math.min(dp[i + 1][Y], dp[i + 1][R2]);
            dp[i][R1] = (c == 'r' ? 0 : 1) + Math.min(dp[i + 1][R1], dp[i + 1][Y]);
        }

        return dp[0][R1];
    }

}
