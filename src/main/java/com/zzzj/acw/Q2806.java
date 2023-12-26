package com.zzzj.acw;

import java.util.Scanner;

/**
 * @author zzzj
 * @create 2023-12-13 14:43
 */
public class Q2806 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner("RGBGR");

        String str = scanner.nextLine();

        int N = str.length();

        int[][] f = new int[N][N];

        for (int i = 0; i < N; i++)
            f[i][i] = 1;

        for (int len = 1; len < N; len++) {

            for (int left = 0; left + len < N; left++) {

                int right = left + len;

                f[left][right] = Integer.MAX_VALUE;

                if (str.charAt(left) == str.charAt(right))
                    f[left][right] = Math.min(
                            f[left + 1][right],
                            f[left][right - 1]
                    );
                else for (int k = left; k < right; k++)
                    f[left][right] = Math.min(
                            f[left][right],
                            f[left][k] + f[k + 1][right]
                    );

            }

        }

        System.out.println(f[0][N - 1]);

    }

}
