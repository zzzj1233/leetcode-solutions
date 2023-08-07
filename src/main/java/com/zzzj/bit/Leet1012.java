package com.zzzj.bit;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-07-28 12:00
 */
public class Leet1012 {

    public static void main(String[] args) {
    }

    // 至少有1位重复数字
    public static int numDupDigitsAtMostN(int n) {

        

        return -1;
    }

    public static int dfs(String value, int index, boolean isLimit, int stat, boolean repeat) {

        if (index >= value.length()) return -1;

        int result = 0;

        if (isLimit) {

            int limit = Character.digit(value.charAt(index), 10);

            for (int i = 0; i < limit; i++) {
                result += dfs(value, index + 1, false, stat | (1 << i), repeat || (stat & (1 << i)) != 0);
            }

            result += dfs(value, index + 1, true, stat | (1 << limit), repeat || (stat & (1 << limit)) != 0);

        } else {

            for (int i = 0; i < 10; i++) {
                result += dfs(value, index + 1, false, stat | (1 << i), repeat || (stat & (1 << i)) != 0);
            }

        }

        return result;
    }

    // 求小于等于 n 的所有数中, 数字 2 出现的个数
    public static int numberOf2sInRange(int num) {
        String value = String.valueOf(num);

        int N = value.length();

        int[][] memo = new int[N][10];

        for (int i = 0; i < N; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dfs2(value, 0, true, 0, memo);
    }


    public static int dfs2(String s, int index, boolean isLimit, int cnt, int[][] memo) {

        if (index >= s.length()) return cnt;

        if (!isLimit && memo[index][cnt] != -1) {
            return memo[index][cnt];
        }

        int result = 0;

        if (isLimit) {
            char c = s.charAt(index);

            int limit = Character.digit(c, 10);

            for (int i = 0; i < limit; i++) {
                result += dfs2(s, index + 1, false, cnt + (i == 2 ? 1 : 0), memo);
            }

            result += dfs2(s, index + 1, true, cnt + (limit == 2 ? 1 : 0), memo);

        } else {

            for (int i = 0; i < 10; i++) {
                result += dfs2(s, index + 1, false, cnt + (i == 2 ? 1 : 0), memo);
            }

        }

        if (!isLimit)
            memo[index][cnt] = result;

        return result;
    }

}
