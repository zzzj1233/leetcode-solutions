package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2022-10-12 12:02
 */
public class Leet2266 {

    public static void main(String[] args) {
        System.out.println(countTexts("22233"));
    }


    public static int countTexts(String pressedKeys) {

        final int MOD = 1000000007;

        int N = pressedKeys.length();

        long[] dp = new long[N];

        dp[0] = 1;

        int[] ways = new int[10];
        Arrays.fill(ways, 3);
        ways[7] = 4;
        ways[9] = 4;

        int consecutive = 1;

        for (int i = 1; i < N; i++) {
            char c = pressedKeys.charAt(i);

            if (c != pressedKeys.charAt(i - 1)) {
                dp[i] = dp[i - 1];
                consecutive = 1;
            } else {
                consecutive++;

                int end = Math.min(ways[Character.digit(c, 10)], consecutive);

                for (int j = 1; j <= end; j++) {
                    if (i - j < 0) {
                        dp[i] += 1;
                    } else {
                        dp[i] += dp[i - j];
                    }
                    dp[i] %= MOD;
                }
            }
        }

        return (int) dp[N - 1];
    }


}
