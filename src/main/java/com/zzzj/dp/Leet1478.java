package com.zzzj.dp;


import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-09 20:39
 */
public class Leet1478 {

    public static void main(String[] args) {

        System.out.println(minDistance(new int[]{1, 4, 8, 10, 20}, 3));

        System.out.println(minDistance(new int[]{2, 3, 5, 12, 18}, 2));

    }

    public static int minDistance(int[] houses, int k) {

        int N = houses.length;

        Arrays.sort(houses);

        int[][] help = new int[N][N];

        for (int i = 0; i < N; i++) {

            for (int j = i + 1; j < N; j++) {

                int mid = houses[i + ((j - i) >> 1)];

                for (int x = i; x <= j; x++)
                    help[i][j] += Math.abs(mid - houses[x]);
            }

        }

        int[][] f = new int[N][k + 1];

        for (int i = 0; i < N; i++)
            Arrays.fill(f[i], Integer.MAX_VALUE);

        for (int i = 0; i < N; i++)
            f[i][1] = help[0][i];

        for (int right = 0; right < N; right++) {

            int K = Math.min(k, right + 1);

            // 选mailBox个
            for (int mailBox = 2; mailBox <= K; mailBox++) {

                // 考虑 [ 0 - left ] 选 mailBox - 1个
                for (int left = Math.max(0, mailBox - 2); left < right; left++) {
                    f[right][mailBox] = Math.min(
                            f[right][mailBox],
                            f[left][mailBox - 1] + help[left + 1][right]
                    );
                }

            }

        }

        return f[N - 1][k];
    }


}
