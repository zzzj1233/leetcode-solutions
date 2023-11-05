package com.zzzj.arr;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2023-09-21 16:36
 */
public class Leet2842 {

    public static void main(String[] args) {

        System.out.println(countKSubsequencesWithMaxBeauty("abbcd", 4));

        System.out.println(countKSubsequencesWithMaxBeauty("bcca", 2));
//
        System.out.println(countKSubsequencesWithMaxBeauty("ggsgo", 3));
    }

    public static int countKSubsequencesWithMaxBeauty(String s, int k) {

        int[] tab = new int[26];

        for (int i = 0; i < s.length(); i++)
            tab[s.charAt(i) - 'a']++;

        // 最大美丽值
        int maxBeauty = Arrays.stream(tab).boxed()
                .sorted(Comparator.reverseOrder())
                .limit(k)
                .mapToInt(value -> value)
                .sum();

        int[][] dp = new int[k][maxBeauty + 1];

        int ans = 0;

        final int MOD = 1000000007;

        for (int i = 0; i < 26; i++) {

            int beauty = tab[i];

            if (beauty == 0)
                continue;

            for (int x = k - 1; x > 0; x--) {

                for (int y = 0; y <= maxBeauty; y++) {

                    if (y + beauty > maxBeauty)
                        break;

                    dp[x][y + beauty] += (dp[x - 1][y] * ((long) beauty)) % MOD;
                    dp[x][y + beauty] %= MOD;
                }

            }

            dp[0][beauty] += beauty;
            dp[0][beauty] %= MOD;
        }

        return dp[k - 1][maxBeauty];
    }

}
