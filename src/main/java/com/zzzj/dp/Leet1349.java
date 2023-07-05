package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-06-28 16:47
 */
public class Leet1349 {

    public static void main(String[] args) {
        System.out.println(maxStudents(LeetUtils.convertChars("[[\"#\",\".\",\"#\",\"#\",\".\",\"#\"],\n" +
                "              [\".\",\"#\",\"#\",\"#\",\"#\",\".\"],\n" +
                "              [\"#\",\".\",\"#\",\"#\",\".\",\"#\"]]")));

        System.out.println(maxStudents(LeetUtils.convertChars("[[\".\",\"#\"],\n" +
                "              [\"#\",\"#\"],\n" +
                "              [\"#\",\".\"],\n" +
                "              [\"#\",\"#\"],\n" +
                "              [\".\",\"#\"]]")));

        System.out.println(maxStudents(LeetUtils.convertChars("[[\"#\",\".\",\".\",\".\",\"#\"],\n" +
                "              [\".\",\"#\",\".\",\"#\",\".\"],\n" +
                "              [\".\",\".\",\"#\",\".\",\".\"],\n" +
                "              [\".\",\"#\",\".\",\"#\",\".\"],\n" +
                "              [\"#\",\".\",\".\",\".\",\"#\"]]")));
    }


    public static final char UN_SEAT_ABLE = '#';

    public static final char SEAT_ABLE = '.';

    public static int maxStudents(char[][] seats) {
        // # = 不可坐
        // . = 可以坐

        int M = seats.length;

        int N = seats[0].length;

        int[][] memo = new int[M][1 << N];

        for (int i = 0; i < M; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dfs(seats, M, N, 0, 0, memo);
    }

    public static int dfs(char[][] seats, int M, int N, int index, int stat, int[][] memo) {

        if (index >= seats.length) return 0;

        for (int i = 0; i < N; i++) if (seats[index][i] == UN_SEAT_ABLE) stat |= 1 << i;

        if (memo[index][stat] != -1) return memo[index][stat];

        // 枚举二进制

        int limit = 1 << N;

        int result = 0;

        for (int i = 0; i < limit; i++) {

            if ((i & stat) == 0 && !hasAdjacentOnes(i)) {

                int cnt = Integer.bitCount(i);

                // 下一行的有些位置是不可坐的
                int newRowStat = 0;

                for (int j = 0; j < N; j++) {

                    if ((i & (1 << j)) != 0) {

                        // 下一行的stat在j的左右不能坐
                        if (j - 1 >= 0) newRowStat |= 1 << (j - 1);
                        if (j + 1 < N) newRowStat |= 1 << (j + 1);

                    }

                }

                result = Math.max(result, dfs(seats, M, N, index + 1, newRowStat, memo) + cnt);
            }

        }

        memo[index][stat] = result;

        return result;
    }

    // 是否有相邻的1
    public static boolean hasAdjacentOnes(int num) {
        return (num & (num >> 1)) != 0;
    }


}
