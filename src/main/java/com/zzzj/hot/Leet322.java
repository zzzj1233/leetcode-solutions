package com.zzzj.hot;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-05-05 15:11
 */
public class Leet322 {

    public static void main(String[] args) {

        System.out.println(coinChange(new int[]{1, 2, 5}, 11));

        System.out.println(coinChange(new int[]{2}, 3));

        System.out.println(coinChange(new int[]{1}, 0));

    }

    public static int coinChange(int[] coins, int amount) {

        int[] f = new int[amount + 1];

        int N = coins.length;

        Arrays.fill(f, Integer.MAX_VALUE);

        f[0] = 0;

        for (int i = 0; i < N; i++) {

            int w = coins[i];

            for (int v = w; v <= amount; v++) {
                if (f[v - w] != Integer.MAX_VALUE)
                    f[v] = Math.min(
                            f[v],
                            f[v - w] + 1
                    );
            }

        }

        return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
    }

}
