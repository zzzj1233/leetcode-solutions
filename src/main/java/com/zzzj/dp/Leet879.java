package com.zzzj.dp;


import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-06 20:23
 */
public class Leet879 {

    public static void main(String[] args) {

        System.out.println(profitableSchemes(
                5,
                3,
                new int[]{2, 2},
                new int[]{2, 3}
        ));

    }

    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {

        int N = group.length;

        int s = Arrays.stream(profit).sum();

        int[][] f = new int[n + 1][s + 1];

        return -1;
    }


}
