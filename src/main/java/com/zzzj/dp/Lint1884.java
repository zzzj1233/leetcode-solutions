package com.zzzj.dp;

public class Lint1884 {

    public static void main(String[] args) {

        System.out.println(takeAwayTheBottle(new int[]{1, 3, 4, 1, 5}));

    }

    public static int takeAwayTheBottle(int[] arr) {

        int N = arr.length;

        int[][] f = new int[N][N];

        for (int i = 0; i < N; i++)
            f[i][i] = 1;

        for (int len = 1; len < N; len++) {

            for (int left = 0; left + len < N; left++) {

                int right = left + len;

                if (len == 1)
                    f[left][right] = arr[left] == arr[right] ? 1 : 2;
                else {
                    f[left][right] = Integer.MAX_VALUE;
                    if (arr[left] == arr[right])
                        f[left][right] = f[left + 1][right - 1];
                    for (int k = left; k < right; k++) {
                        f[left][right] = Math.min(
                                f[left][right],
                                f[left][k] + f[k + 1][right]
                        );
                    }
                }

            }

        }

        return f[0][N - 1];
    }


}
