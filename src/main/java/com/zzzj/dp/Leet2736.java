package com.zzzj.dp;

import java.util.Arrays;

public class Leet2736 {

    public static void main(String[] args) {

        System.out.println(countSpecialNumbers(20));

        System.out.println(countSpecialNumbers(135));

    }

    public static int countSpecialNumbers(int n) {

        String value = String.valueOf(n);

        int N = value.length();

        int[][] memo = new int[N][1 << 10];

        for (int i = 0; i < N; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dfs(value, 0, true, 0, false, memo);
    }

    public static int dfs(String value, int index, boolean isLimit, int stat, boolean isNum, int[][] memo) {

        if (index >= value.length()) {
            return isNum ? 1 : 0;
        }

        if (!isLimit && memo[index][stat] != -1) return memo[index][stat];

        int result = 0;

        if (!isNum) {
            result += dfs(value, index + 1, false, 0, false, memo);
        }

        if (isLimit) {

            int limit = Character.digit(value.charAt(index), 10);

            for (int i = isNum ? 0 : 1; i <= limit; i++) {
                if ((stat & (1 << i)) != 0) continue;
                result += dfs(value, index + 1, i == limit, stat | (1 << i), true, memo);
            }

        } else {
            for (int i = isNum ? 0 : 1; i < 10; i++) {
                if ((stat & (1 << i)) != 0) continue;
                result += dfs(value, index + 1, false, stat | (1 << i), true, memo);
            }

            memo[index][stat] = result;
        }

        return result;
    }
}
