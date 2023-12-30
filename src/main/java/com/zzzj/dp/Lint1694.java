package com.zzzj.dp;

public class Lint1694 {

    public static void main(String[] args) {

        System.out.println(killMonster(new int[]{1, 4, 5, 4}, new int[]{3, 4, 9, 1}));

    }

    public static int killMonster(int[] atk1, int[] atk2) {

        int N = atk1.length;

        int[][] f = new int[N][N];

        for (int i = 0; i < N; i++)
            f[i][i] = atk1[i];

        for (int len = 1; len < N; len++) {

            for (int left = 0; left + len < N; left++) {

                int right = left + len;

                f[left][right] = Integer.MAX_VALUE;

                if (len == 1) f[left][right] = Math.min(atk1[left] + atk2[right], atk1[right] + atk2[left]);
                else for (int k = left; k < right; k++)
                    f[left][right] = Math.min(f[left][right], f[left][k] + f[k + 1][right]);

            }

        }

        return f[0][N - 1];
    }

}
