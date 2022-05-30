package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-05-30 18:55
 */
public class Leet1908 {

    public static void main(String[] args) {
        System.out.println(nimGame(new int[]{1, 2, 3}));
    }

    public static boolean nimGame(int[] piles) {
        int N = piles.length;

        int max = Arrays.stream(piles).max().getAsInt();

        return dfs(piles);
    }

    public static boolean dfs(int[] piles) {
        for (int i = 0; i < piles.length; i++) {
            if (piles[i] > 0) {
                for (int j = 1; j <= piles[i]; j++) {
                    piles[i] -= j;
                    boolean result = dfs(piles);
                    piles[i] += j;
                    if (!result) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
