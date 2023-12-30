package com.zzzj.dp;

public class Lint1100 {

    public static void main(String[] args) {

        System.out.println(strangePrinter("aaabbb"));

        System.out.println(strangePrinter("aba"));

    }

    public static int strangePrinter(String s) {

        int N = s.length();

        int[][] f = new int[N][N];

        for (int i = 0; i < N; i++)
            f[i][i] = 1;

        for (int len = 1; len < N; len++) {

            for (int left = 0; left + len < N; left++) {

                int right = left + len;

                f[left][right] = Integer.MAX_VALUE;

                if (s.charAt(left) == s.charAt(right))
                    f[left][right] = Math.min(f[left + 1][right], f[left][right - 1]);
                else for (int k = left; k < right; k++) {
                    f[left][right] = Math.min(
                            f[left][right],
                            f[left][k] + f[k + 1][right]
                    );
                }
            }

        }

        return f[0][N - 1];
    }

}
