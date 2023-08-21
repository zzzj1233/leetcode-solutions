package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-21 15:32
 */
public class Leet2719 {

    public static void main(String[] args) {

        System.out.println(count("1", "12", 1, 8));

        System.out.println(count("1", "5", 1, 5));

        System.out.println(count("1", "5", 1, 5));

    }

    static final int MOD = 1000000007;

    public static int count(String num1, String num2, int minSum, int maxSum) {

        int s = digitSum(num1);

        int r2 = calc(num2, minSum, maxSum);

        int r1 = calc(num1, minSum, maxSum);

        return ((r2 + MOD) - r1) % MOD + (s >= minSum && s <= maxSum ? 1 : 0);
    }

    public static int digitSum(String str) {
        int value = 0;
        for (int i = 0; i < str.length(); i++) {
            value += Character.digit(str.charAt(i), 10);
        }
        return value;
    }

    public static int calc(
            String value,
            int minSum,
            int maxSum
    ) {

        int N = value.length();

        int[][] memo = new int[N][maxSum + 1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dfs(value, 0, true, false, 0, minSum, maxSum, memo);
    }

    public static int dfs(
            String value,
            int index,
            boolean isLimit,
            boolean isNum,
            int digitSum,
            int minSum,
            int maxSum,
            int[][] memo
    ) {

        if (digitSum > maxSum) return 0;

        if (index >= value.length())
            return isNum && digitSum >= minSum ? 1 : 0;

        int result = 0;

        if (!isNum) {
            result += dfs(value, index + 1, false, false, 0, minSum, maxSum, memo) % MOD;
            result %= MOD;
        }

        if (isLimit) {

            int limit = Character.digit(value.charAt(index), 10);

            for (int i = isNum ? 0 : 1; i <= limit; i++) {
                result += dfs(value, index + 1, i == limit, true, digitSum + i, minSum, maxSum, memo) % MOD;
                result %= MOD;
            }

        } else {

            if (memo[index][digitSum] != -1)
                return memo[index][digitSum];

            for (int i = isNum ? 0 : 1; i < 10; i++) {
                result += dfs(value, index + 1, false, true, digitSum + i, minSum, maxSum, memo) % MOD;
                result %= MOD;
            }

            memo[index][digitSum] = result;
        }

        return result;
    }

}
