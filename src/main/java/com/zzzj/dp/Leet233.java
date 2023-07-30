package com.zzzj.dp;

import java.util.Arrays;

public class Leet233 {

    public static void main(String[] args) {

        System.out.println(countDigitOne(20));

    }

    public static int countDigitOne(int n) {

        String value = String.valueOf(n);

        int N = value.length();

        int[][] memo = new int[N][10];

        for (int i = 0; i < N; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dfs(value, 0, 0, true, memo);
    }

    public static int dfs(String value, int index, int cnt, boolean isLimit, int[][] memo) {

        if (index >= value.length()) return cnt;

        if (!isLimit && memo[index][cnt] != -1) return memo[index][cnt];

        int result = 0;

        if (isLimit) {

            int limit = Character.digit(value.charAt(index), 10);

            for (int i = 0; i <= limit; i++) {
                result += dfs(value, index + 1, cnt + (i == 1 ? 1 : 0), i == limit, memo);
            }

        } else {

            for (int i = 0; i < 10; i++) {
                result += dfs(value, index + 1, cnt + (i == 1 ? 1 : 0), false, memo);
            }

            memo[index][cnt] = result;
        }

        return result;
    }

}
