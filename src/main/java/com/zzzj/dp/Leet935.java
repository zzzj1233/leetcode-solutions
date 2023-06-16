package com.zzzj.dp;


/**
 * @author zzzj
 * @create 2023-06-14 17:35
 */
public class Leet935 {

    public static void main(String[] args) {

        System.out.println(knightDialer(1));

        System.out.println(knightDialer(2));

        System.out.println(knightDialer(3131));
    }

    public static int knightDialer(int n) {
        return dp(n);
    }

    public static final int[][] DIRS = {
            new int[]{-2, -1},
            new int[]{-2, 1},
            new int[]{-1, -2},
            new int[]{-1, 2},
            new int[]{1, -2},
            new int[]{1, 2},
            new int[]{2, -1},
            new int[]{2, 1},
    };

    public static final int[][] DIALER = {
            new int[]{1, 2, 3},
            new int[]{4, 5, 6},
            new int[]{7, 8, 9},
            new int[]{-1, 0, -1},
    };

    public static int dp(int n) {

        final int MOD = 1000000007;

        int N = 3;

        int M = 3;

        int[][] dp = new int[N + 1][M + 1];

        for (int i = 0; i <= N; i++) for (int j = 0; j <= M; j++) dp[i][j] = 1;

        for (int k = 2; k <= n; k++) {

            int[][] newDP = new int[N + 1][M + 1];

            for (int i = 0; i <= N; i++) {

                for (int j = 0; j <= M; j++) {

                    int result = 0;

                    for (int[] dir : DIRS) {
                        int row = i + dir[0];
                        int col = j + dir[1];

                        if (row >= 0 && col >= 0 && row < DIALER.length && col < DIALER[row].length && DIALER[row][col] >= 0) {
                            result += dp[row][col] % MOD;
                            result %= MOD;
                        }
                    }

                    newDP[i][j] = result;
                }

            }

            dp = newDP;
        }


        int ans = 0;

        for (int i = 0; i < DIALER.length; i++) {

            for (int j = 0; j < DIALER[i].length; j++) {

                if (DIALER[i][j] >= 0) {
                    ans += dp[i][j] % MOD;
                    ans %= MOD;
                }

            }

        }

        return ans;
    }

}
