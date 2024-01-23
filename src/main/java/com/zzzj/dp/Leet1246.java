package com.zzzj.dp;

/**
 * @author zzzj
 * @create 2024-01-22 14:25
 */
public class Leet1246 {

    public static void main(String[] args) {

        System.out.println(minimumMoves(new int[]{1, 2}));

        System.out.println(minimumMoves(new int[]{1, 3, 4, 1, 5}));

        System.out.println(minimumMoves(new int[]{1, 4, 1, 1, 2, 3, 2, 1}));

    }

    public static int minimumMoves(int[] arr) {

        int N = arr.length;

        int[][] f = new int[N][N];

        for (int i = 0; i < N; i++)
            f[i][i] = 1;

        for (int len = 1; len < N; len++) {

            for (int left = 0; left + len < N; left++) {

                int right = left + len;

                f[left][right] = Integer.MAX_VALUE;

                if (arr[left] == arr[right])
                    if (left + 1 == right)
                        f[left][right] = 1;
                    else
                        f[left][right] = f[left + 1][right - 1];

                for (int k = left; k < right; k++)
                    f[left][right] = Math.min(f[left][right], f[left][k] + f[k + 1][right]);

            }

        }


        return f[0][N - 1];
    }

}
