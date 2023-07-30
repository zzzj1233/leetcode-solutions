package com.zzzj.dp;

import java.util.Arrays;

public class Leet902 {

    public static void main(String[] args) {

        System.out.println(atMostNGivenDigitSet(new String[]{"1", "3", "5", "7"}, 100));

        System.out.println(atMostNGivenDigitSet(new String[]{"1", "4", "9"}, 1000000000));

    }

    public static int atMostNGivenDigitSet(String[] digits, int n) {

        boolean[] ds = new boolean[10];

        for (String digit : digits) ds[Character.digit(digit.charAt(0), 10)] = true;

        String value = String.valueOf(n);

        int N = value.length();

        int[][] memo = new int[N][11];

        for (int i = 0; i < N; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dfs(value, ds, 0, 10, true, memo);
    }

    public static int dfs(String value, boolean[] digits, int index, int prev, boolean isLimit, int[][] memo) {

        if (index >= value.length()) return prev == 10 ? 0 : 1;

        if (!isLimit && memo[index][prev] != -1) {
            return memo[index][prev];
        }

        int result = 0;

        if (prev == 10) {
            result += dfs(value, digits, index + 1, 10, false, memo);
        }

        if (isLimit) {

            int limit = Character.digit(value.charAt(index), 10);

            for (int i = 0; i <= limit; i++) {
                if (digits[i]) {
                    result += dfs(value, digits, index + 1, i, i == limit, memo);
                }
            }

        } else {

            for (int i = 0; i < 10; i++) {
                if (digits[i]) {
                    result += dfs(value, digits, index + 1, i, false, memo);
                }
            }

            memo[index][prev] = result;
        }

        return result;
    }

}
