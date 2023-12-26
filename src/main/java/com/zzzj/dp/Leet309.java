package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2021-10-29 15:57
 */
public class Leet309 {

    public static void main(String[] args) {
        // System.out.println(maxProfit(new int[]{1, 2, 3, 0, 2}));
        System.out.println(maxProfit(new int[]{2, 1, 4}));
    }

    public static int maxProfit(int[] prices) {

        int N = prices.length;

        int[][] f = new int[N + 1][3];

        // 0 = 持有
        // 1 = 未持有 + 不可买
        // 2 = 未持有 + 可以买

        // 2 -> 0
        // 0 -> 1
        // 1 -> 2

        f[0][0] = Integer.MIN_VALUE / 2;
        f[0][1] = Integer.MIN_VALUE / 2;

        for (int i = 1; i <= N; i++) {

            int p = prices[i - 1];

            f[i][0] = Math.max(
                    f[i - 1][2] - p,
                    f[i - 1][0]
            );

            f[i][1] = f[i - 1][0] + p;

            f[i][2] = Math.max(
                    f[i - 1][1],
                    f[i - 1][2]
            );

        }

        return Math.max(
                0,
                Math.max(
                        f[N][1],
                        f[N][2]
                )
        );
    }

}
