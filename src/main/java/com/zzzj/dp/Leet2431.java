package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-01-03 16:29
 */
public class Leet2431 {

    public static void main(String[] args) {
        System.out.println(maxTastiness(new int[]{10, 20, 20}, new int[]{5, 8, 8}, 20, 1));
        System.out.println(maxTastiness(new int[]{10, 15, 7}, new int[]{5, 8, 20}, 10, 2));
    }

    private static int dfs(int[] price, int[] tastiness, int maxAmount, int maxCoupons, int index) {
        if (index >= price.length) {
            return 0;
        }

        int ways1 = dfs(price, tastiness, maxAmount, maxCoupons, index + 1);

        int ways2 = -1;

        if (maxAmount - price[index] >= 0) {
            ways2 = dfs(price, tastiness, maxAmount - price[index], maxCoupons, index + 1) + tastiness[index];
        }

        int ways3 = -1;

        if (maxCoupons > 0 && maxAmount - price[index] / 2 >= 0) {
            ways3 = dfs(price, tastiness, maxAmount - price[index] / 2, maxCoupons - 1, index + 1) + tastiness[index];
        }

        return Math.max(ways1, Math.max(ways2, ways3));
    }

    public static int maxTastiness(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {

        int N = price.length;

        int[][][] dp = new int[N + 1][maxAmount + 1][maxCoupons + 1];

        for (int i = 1; i <= N; i++) {
            int p = price[i - 1];
            int t = tastiness[i - 1];
            int halfP = p / 2;

            for (int j = 0; j <= maxAmount; j++) {

                for (int k = 0; k <= maxCoupons; k++) {

                    int ways1 = dp[i - 1][j][k];
                    int ways2 = -1;
                    int ways3 = -1;

                    if (j - p >= 0) {
                        ways2 = dp[i - 1][j - p][k] + t;
                    }

                    if (j - halfP >= 0 && k > 0) {
                        ways3 = dp[i - 1][j - halfP][k - 1] + t;
                    }

                    dp[i][j][k] = Math.max(ways1, Math.max(ways2, ways3));
                }

            }
        }

        return dp[N][maxAmount][maxCoupons];
    }

}
