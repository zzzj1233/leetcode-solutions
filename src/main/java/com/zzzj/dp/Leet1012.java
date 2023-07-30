package com.zzzj.dp;

import java.util.Arrays;

public class Leet1012 {

    public static void main(String[] args) {

        System.out.println(numDupDigitsAtMostN(20));

        System.out.println(numDupDigitsAtMostN(100));

        System.out.println(numDupDigitsAtMostN(1000));

        System.out.println(numDupDigitsAtMostN(10000));

    }

    public static int NOT_NUM = 1 << 10;

    public static int numDupDigitsAtMostN(int n) {

        String value = String.valueOf(n);

        int N = value.length();

        int[][] memo = new int[N][NOT_NUM + 1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dfs(value, 0, NOT_NUM, false, true, memo);
    }

    public static int dfs(String value, int index, int stat, boolean repeat, boolean isLimit, int[][] memo) {


        if (index >= value.length()) {
            return repeat ? 1 : 0;
        }

        if (!isLimit && repeat && memo[index][stat] != -1) return memo[index][stat];

        int result = 0;

        if (stat == NOT_NUM) {
            result += dfs(value, index + 1, NOT_NUM, false, false, memo);
        }

        if (isLimit) {

            int limit = Character.digit(value.charAt(index), 10);

            for (int i = stat == NOT_NUM ? 1 : 0; i <= limit; i++) {
                result += dfs(value, index + 1, stat == NOT_NUM ? 1 << i : stat | (1 << i),
                        repeat || (stat != NOT_NUM && (stat & (1 << i)) != 0), i == limit, memo);
            }

        } else {

            for (int i = stat == NOT_NUM ? 1 : 0; i < 10; i++) {
                result += dfs(value, index + 1, stat == NOT_NUM ? 1 << i : stat | (1 << i),
                        repeat || (stat != NOT_NUM && (stat & (1 << i)) != 0), false, memo);
            }

        }

        if (repeat) {
            memo[index][stat] = result;
        }

        return result;
    }
}
