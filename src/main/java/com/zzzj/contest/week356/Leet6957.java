package com.zzzj.contest.week356;

import java.util.Arrays;

public class Leet6957 {

    public static void main(String[] args) {

        System.out.println(countSteppingNumbers("1", "11"));


        System.out.println(countSteppingNumbers("1", "5550"));

    }

    static final int MOD = 1000000007;

    public static int countSteppingNumbers(String low, String high) {

        char[] lowChars = low.toCharArray();

        char[] highChars = high.toCharArray();

        return calc(highChars) - calc(lowChars) + (isStepNum(low) ? 1 : 0);
    }

    public static boolean isStepNum(String str) {
        int prev = Character.digit(str.charAt(0), 10);

        for (int i = 1; i < str.length(); i++) {
            int digit = Character.digit(str.charAt(i), 10);
            if (digit != prev + 1 && digit != prev - 1)
                return false;
            prev = digit;
        }

        return true;
    }

    public static int calc(char[] value) {
        int N = value.length;
        int[][] memo = new int[N][11];
        for (int i = 0; i < N; i++) Arrays.fill(memo[i], -1);
        return dfs(value, 0, true, 10, memo) % MOD;
    }

    public static int dfs(char[] value, int index, boolean isLimit, int prev, int[][] memo) {

        if (index >= value.length) {
            return prev >= 0 ? 1 : 0;
        }

        int result = 0;

        if (!isLimit && memo[index][prev] != -1) return memo[index][prev];

        if (prev == 10) {
            result += dfs(value, index + 1, false, 10, memo) % MOD;
        }

        if (isLimit) {

            int limit = Character.digit(value[index], 10);

            for (int i = prev == 10 ? 1 : 0; i <= limit; i++) {

                if (prev == 10 || i == prev + 1 || i == prev - 1) {
                    result += dfs(value, index + 1, i == limit, i, memo) % MOD;
                }

            }

        } else {

            for (int i = prev == 10 ? 1 : 0; i < 10; i++) {
                if (prev == 10 || i == prev + 1 || i == prev - 1) {
                    result += dfs(value, index + 1, false, i, memo) % MOD;
                }
            }

        }

        result %= MOD;

        if (!isLimit) memo[index][prev] = result;

        return result;
    }

}
