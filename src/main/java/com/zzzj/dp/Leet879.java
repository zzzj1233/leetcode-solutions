package com.zzzj.dp;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-06 20:23
 */
public class Leet879 {

    public static void main(String[] args) {

        System.out.println(right(5, 3, new int[]{2, 2}, new int[]{2, 3}));
        System.out.println(profitableSchemes(5, 3, new int[]{2, 2}, new int[]{2, 3}));

        System.out.println(right(10, 5, new int[]{2, 3, 5}, new int[]{6, 7, 8}));
        System.out.println(profitableSchemes(10, 5, new int[]{2, 3, 5}, new int[]{6, 7, 8}));
    }

    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {

        final int MOD = 1000000007;

        int[][][] dp = new int[n + 1][group.length + 1][minProfit + 1];

        for (int i = 0; i < group.length; i++)
            dp[0][i][0] = 1;

        for (int i = 0; i <= n; i++) {

            for (int j = 1; j <= group.length; j++) {

                int g = group[j - 1];

                int p = profit[j - 1];

                dp[i][j] = Arrays.copyOfRange(dp[i][j - 1], 0, dp[i][j - 1].length);

                for (int k = 0; k <= minProfit; k++) {

                    int mp = Math.min(minProfit, k + p);

                    if (i - g >= 0) {
                        dp[i][j][mp] += dp[i - g][j - 1][k] % MOD;
                        dp[i][j][mp] %= MOD;
                    }

                }

            }

        }

        int ans = 0;

        for (int i = 0; i < dp.length; i++) {
            ans += dp[i][group.length][minProfit] % MOD;
            ans %= MOD;
        }

        return ans;
    }

    public static int right(
            int n,
            int minProfit,
            int[] group,
            int[] profit
    ) {
        return dfs(n, minProfit, group, profit, 0, 0, 0);
    }

    public static int dfs(
            int n,
            int minProfit,
            int[] group,
            int[] profit,
            int index,
            int groupIndex,
            int curProfit
    ) {
        if (index >= n || groupIndex >= group.length)
            return curProfit >= minProfit ? 1 : 0;

        int g = group[groupIndex];

        int p = profit[groupIndex];

        int case1 = 0;

        if (index + g <= n)
            case1 = dfs(n, minProfit, group, profit, index + g, groupIndex + 1, curProfit + p);

        int case2 = dfs(n, minProfit, group, profit, index, groupIndex + 1, curProfit);

        return case1 + case2;
    }

}
