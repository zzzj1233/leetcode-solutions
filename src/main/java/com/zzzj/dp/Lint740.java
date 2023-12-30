package com.zzzj.dp;

public class Lint740 {

    public static void main(String[] args) {

        System.out.println(change(8, new int[]{2, 3, 8}));

    }

    public static int change(int amount, int[] coins) {

        int N = coins.length;

        int[] f = new int[amount + 1];

        f[0] = 1;

        for (int i = 0; i < N; i++) {

            for (int j = coins[i]; j <= amount; j++) {
                f[j] += f[j - coins[i]];
            }

        }

        return f[amount];
    }

}
