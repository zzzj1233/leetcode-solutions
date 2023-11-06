package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-10-31 17:37
 */
public class Leet2209 {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {

            int M = LeetUtils.random.nextInt(1000) + 1;

            String floor = LeetUtils.randomString(M, "01");

            int numCarpets = LeetUtils.random.nextInt(floor.length()) + 1;

            int carpetLen = LeetUtils.random.nextInt(floor.length()) + 1;

            int r = minimumWhiteTiles(floor, numCarpets, carpetLen);

            int rr = right(floor, numCarpets, carpetLen);

            if (r != rr) {
                System.out.println("floor = " + floor);
                System.out.println("numCarpets = " + numCarpets);
                System.out.println("carpetLen = " + carpetLen);
                System.out.println("r = " + r);
                System.out.println("rr = " + rr);
                return;
            }
        }

        System.out.println("Ok");
    }

    public static int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {

        int N = floor.length();

        int[][] dp = new int[N + 1][numCarpets + 1];

        final char black = '0';

        final char white = '0';

        for (int i = 1; i <= N; i++) {

            char tile = floor.charAt(i - 1);

            if (tile == black) {
                dp[i] = Arrays.copyOfRange(dp[i - 1], 0, dp[i - 1].length);
            } else {

                for (int j = 0; j <= numCarpets; j++)
                    dp[i][j] = dp[i - 1][j] + 1;

                for (int j = 1; j <= numCarpets; j++) {

                    int prev = Math.max(0, i - carpetLen);

                    // 铺的情况
                    dp[i][j] = Math.min(
                            dp[i][j],
                            dp[prev][j - 1]
                    );
                }

            }

        }

        return Arrays.stream(dp[N]).min().orElse(0);
    }

    public static int right(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        int[][] dp = new int[numCarpets + 1][n + 1];
        for (int i = 0; i < n; i++) {
            dp[0][i + 1] = floor.charAt(i) - '0' + dp[0][i];
        }

        for (int i = 1; i <= numCarpets; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j + 1] = Math.min(floor.charAt(j) - '0' + dp[i][j], j + 1 - carpetLen < 0 ? 0 : dp[i - 1][j + 1 - carpetLen]);
            }
        }

        return dp[numCarpets][n];
    }

}
