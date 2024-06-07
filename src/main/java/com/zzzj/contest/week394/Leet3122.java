package com.zzzj.contest.week394;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-05-24 18:08
 */
public class Leet3122 {

    public static void main(String[] args) {

        System.out.println(minimumOperations(LeetUtils.convertInts("[[1,0,2],[1,0,2]]")));

        System.out.println(minimumOperations(LeetUtils.convertInts("[[1,1,1],[0,0,0]]")));

        System.out.println(minimumOperations(LeetUtils.convertInts("[[1],[2],[3]]")));

    }

    public static int minimumOperations(int[][] grid) {

        int L = grid.length;

        int C = grid[0].length;

        int[][] f = new int[C][10];

        int[][] tab = new int[C][10];

        for (int i = 0; i < C; i++)
            for (int j = 0; j < L; j++)
                tab[i][grid[j][i]]++;

        for (int i = 0; i < C; i++)
            Arrays.fill(f[i], Integer.MAX_VALUE);

        for (int i = 0; i < 10; i++)
            f[0][i] = L - tab[0][i];

        for (int i = 1; i < C; i++) {

            for (int j = 0; j < 10; j++) {

                for (int k = 0; k < 10; k++) {

                    if (j != k)
                        f[i][j] = Math.min(f[i][j], f[i - 1][k] + (L - tab[i][j]));

                }

            }

        }

        return Arrays.stream(f[C - 1]).min().orElse(-1);
    }

}
