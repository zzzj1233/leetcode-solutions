package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-10-16 11:29
 */
public class Leet1745 {

    public static void main(String[] args) {

        System.out.println(checkPartitioning("abcbdd"));

        System.out.println(checkPartitioning("bcbddxy"));

        System.out.println(checkPartitioning("juchzcedhfesefhdeczhcujzzvbmoeombv"));

    }

    public static boolean checkPartitioning(String s) {

        int N = s.length();

        boolean[][] help = new boolean[N][N];

        for (int i = 0; i < N; i++)
            help[i][i] = true;

        for (int y = 0; y < N; y++) {

            for (int x = 0; x < y; x++) {

                if (s.charAt(x) == s.charAt(y))
                    help[x][y] = y - 1 == x || help[x + 1][y - 1];

            }

        }

        boolean[][] dp = new boolean[N][3];

        for (int i = 0; i < N; i++) {
            if (help[0][i])
                dp[i][0] = true;
        }

        for (int y = 1; y < N; y++) {

            for (int x = 1; x <= y; x++) {

                for (int k = 1; k < 3; k++) {

                    dp[y][k] |= help[x][y] && dp[x - 1][k - 1];
                }

            }

        }

        return dp[N - 1][2];
    }


}
