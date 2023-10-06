package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-10-01 21:46
 */
public class Leet1416 {

    public static void main(String[] args) {

        System.out.println(numberOfArrays("1000", 10000));
//
        System.out.println(numberOfArrays("1000", 10));

        System.out.println(numberOfArrays("2020", 30));
//
        System.out.println(numberOfArrays("1317", 200));
//
        System.out.println(numberOfArrays("1317", 2000));

        System.out.println(numberOfArrays("1234567890", 90));

    }

    public static int numberOfArrays(String s, int k) {

        int N = s.length();

        int[] dp = new int[N + 1];

        dp[0] = 1;

        final int MOD = 1000000007;

        for (int i = 1; i <= N; i++) {

            int lowBound = Math.max(0, i - 1 - 10);

            for (int j = i - 1; j >= lowBound; j--) {

                if (Character.digit(s.charAt(j), 10) == 0) {
                    continue;
                }

                long num = Long.parseLong(s.substring(j, i));

                if (num > k)
                    break;

                dp[i] = (dp[i] + dp[j]) % MOD;
            }

        }

        return dp[N];
    }


}
