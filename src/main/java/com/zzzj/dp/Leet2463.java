package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2022-07-28 15:14
 */
public class Leet2463 {

    public static void main(String[] args) {
        System.out.println(translateNum(12221));
    }

    public static int translateNum(int num) {
        String s = String.valueOf(num);

        int N = s.length();

        int[] dp = new int[N + 1];
        dp[N] = 1;

        for (int i = N - 1; i >= 0; i--) {
            int val = s.charAt(i) - '0';

            dp[i] = dp[i + 1];

            if (i + 1 < N && val != 0) {
                int val2 = s.charAt(i + 1) - '0';

                // 另外一条路
                if (val * 10 + val2 < 26) {
                    dp[i] += dp[i + 2];
                }

            }
            // 单独一条路
        }

        return dp[0];
    }

}
