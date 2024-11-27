package com.zzzj.contest.dweek144;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-11-24 19:33
 */
public enum Q4 {
    ;

    public static void main(String[] args) {

        System.out.println(maxCollectedFruits(LeetUtils.convertInts("[[1,2,3,4,3,4],[5,6,8,7,3,4],[9,10,11,12,3,4],[13,14,15,16,3,4],[13,14,15,16,3,4],[13,14,15,16,3,4]]")));

    }

    public static int maxCollectedFruits(int[][] fruits) {

        int N = fruits.length;

        int s = 0;

        for (int i = 0; i < N; i++)
            s += fruits[i][i];

        // [ N - 1 ~ N / 2 ]

        int[][] f = new int[N][N];

        int mid = N >> 1;

        for (int i = 0; i < N; i++)
            Arrays.fill(f[i], -1);

        f[0][N - 1] = fruits[0][N - 1];

        int cnt = 1;

        boolean peek = false;

        boolean p1 = false;

        for (int i = 1; i < N; i++) {

            if (!peek) {

                if (cnt == mid) {
                    if (p1)
                        peek = true;
                    else
                        p1 = true;
                } else {
                    cnt++;
                }
            } else {
                cnt--;
            }

            for (int loop = 0; loop < cnt; loop++) {

                int j = N - 1 - loop;

                System.out.printf("i = %d , j = %d %n", i, j);

                int fr = fruits[i][j];

                if (j + 1 < N && f[i - 1][j + 1] != -1)
                    f[i][j] = Math.max(f[i][j], f[i - 1][j + 1] + fr);
                if (f[i - 1][j - 1] != -1)
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + fr);
                if (f[i - 1][j] != -1)
                    f[i][j] = Math.max(f[i][j], f[i - 1][j] + fr);

            }

        }

        s += f[N - 1][N - 1];

        for (int i = 0; i < N; i++)
            Arrays.fill(f[i], -1);

        f[N - 1][0] = fruits[N - 1][0];

        for (int j = 1; j < N; j++) {

            for (int loop = 0; loop <= j; loop++) {

                int i = N - 1 - loop;

                int fr = fruits[i][j];

                // 固定 j - 1
                if (i - 1 >= mid && f[i - 1][j - 1] != -1)
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + fr);
                if (i + 1 < N && f[i + 1][j - 1] != -1)
                    f[i][j] = Math.min(f[i][j], f[i + 1][j - 1] + fr);
                if (f[i][j - 1] != -1)
                    f[i][j] = Math.min(f[i][j], f[i][j - 1] + fr);

            }

        }

        s += f[N - 1][N - 1];

        s -= fruits[N - 1][N - 1];
        s -= fruits[N - 1][N - 1];

        return s;
    }

}
