package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-06-15 17:01
 */
public class Leet518 {

    public static void main(String[] args) {
        System.out.println(change(125, new int[]{1, 2, 5}));
    }

    public static int change(int amount, int[] coins) {

        int[] f = new int[amount + 1];

        f[0] = 1;

        for (int w : coins) for (int v = w; v <= amount; v++) f[v] += f[v - w];

        return f[amount];
    }

}
