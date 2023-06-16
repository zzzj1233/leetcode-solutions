package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-05-24 17:54
 */
public class Leet2320 {

    public static void main(String[] args) {

        System.out.println(countHousePlacements(2));

        System.out.println(countHousePlacements(3));

        System.out.println(countHousePlacements(5));

    }

    static final int MOD = 1000000007;

    public static int countHousePlacements(int n) {

        long[][] dp = new long[n + 1][2];

        final int TOP = 0;
        final int BOTTOM = 1;

        dp[n][0] = 1;
        dp[n][1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            dp[i][TOP] = pick(dp, i + 2, TOP, n) + pick(dp, i + 1, BOTTOM, n) + pick(dp, i + 2, BOTTOM, n);
            dp[i][BOTTOM] = pick(dp, i + 2, BOTTOM, n) + pick(dp, i + 1, TOP, n) + pick(dp, i + 2, TOP, n);
        }

        return (int) ((dp[0][TOP] % MOD) + (dp[0][BOTTOM] % MOD));
    }

    public static long pick(long[][] dp, int x, int position, int n) {
        if (x >= n) return 1;
        return dp[x][position];
    }

    public static int dfs(int N, int x, int y) {

        if (x >= N && y >= N) return 1;

        if (x >= N) {
            int case1 = dfs(N, x, y + 1);
            int case2 = dfs(N, x, y + 2);
            return case1 + case2;
        }

        if (y >= N) {
            int case1 = dfs(N, x + 1, y);
            int case2 = dfs(N, x + 2, y);
            return case1 + case2;
        }

        // 都不放
        int case1 = dfs(N, x + 1, y + 1);

        // 都放
        int case2 = dfs(N, x + 2, y + 2);

        // x放y不放
        int case3 = dfs(N, x + 2, y + 1);

        // x不放y放
        int case4 = dfs(N, x + 1, y + 2);

        return case1 + case2 + case3 + case4;
    }

}
