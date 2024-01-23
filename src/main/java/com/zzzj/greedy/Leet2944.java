package com.zzzj.greedy;

/**
 * @author zzzj
 * @create 2023-12-28 12:42
 */
public class Leet2944 {

    public static void main(String[] args) {

        System.out.println(minimumCoins(new int[]{3, 1, 2}));

        System.out.println(minimumCoins(new int[]{1, 10, 1, 1}));

    }

    public static int minimumCoins(int[] prices) {

        int N = prices.length;

        int[][] f = new int[N + 1][2];

        f[1][0] = Integer.MAX_VALUE / 2;
        f[1][1] = prices[0];

        for (int i = 2; i <= N; i++) {

            int price = prices[i - 1];

            f[i][0] = Integer.MAX_VALUE;

            for (int j = i - 1; j >= 0; j--) {

                if (j + j < i)
                    break;

                f[i][0] = Math.min(
                        f[i][0],
                        f[j][1]
                );
            }

            f[i][1] = Math.min(
                    f[i - 1][0],
                    f[i - 1][1]
            ) + price;
        }

        return Math.min(
                f[N][0],
                f[N][1]
        );
    }

}
