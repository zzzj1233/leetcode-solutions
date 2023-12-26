package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2023-12-13 17:16
 */
public class Lint476 {

    public static void main(String[] args) {

        System.out.println(stoneGame(new int[]{3, 4, 3}));

        System.out.println(stoneGame(new int[]{4, 1, 1, 4}));

    }

    public static int stoneGame(int[] a) {

        int N = a.length;

        int[][] f = new int[N][N];

        for (int i = 0; i < N; i++)
            f[i][i] = a[i];

        int[] s = new int[N + 1];

        for (int i = 1; i <= N; i++)
            s[i] = s[i - 1] + a[i - 1];

        for (int len = 1; len < N; len++) {

            for (int left = 0; left + len < N; left++) {

                int right = left + len;

                f[left][right] = Integer.MAX_VALUE;

                if (len == 1)
                    f[left][right] = s[right + 1] - s[left];
                else for (int k = left; k < right; k++) {
                    f[left][right] = Math.min(
                            f[left][right],
                            f[left][k] + f[k + 1][right] + s[right + 1] - s[left]
                    );
                }

            }

        }

        return f[0][N - 1];
    }

}
