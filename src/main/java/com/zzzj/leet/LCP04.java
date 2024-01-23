package com.zzzj.leet;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-01-02 14:51
 */
public class LCP04 {

    public static void main(String[] args) {

        System.out.println(domino(2, 3, LeetUtils.convertInts("[[1, 0], [1, 1]]")));

        System.out.println(domino(3, 3, new int[0][0]));

    }

    public static int domino(int n, int m, int[][] broken) {

        int limit = 1 << m;

        int[][] f = new int[2][limit];

        int[] forbidden = new int[n + 2];

        for (int[] item : broken)
            forbidden[item[0] + 1] |= 1 << item[1];

        for (int row = 1; row <= n; row++) {

            int end = row == n ? 1 : limit;

            for (int stat = 0; stat < end; stat++) {

                if ((forbidden[row] & stat) != 0 || (forbidden[row + 1] & stat) != 0)
                    continue;

                for (int prev = 0; prev < limit; prev++) {

                    if ((prev & stat) != 0)
                        continue;

                    int cnt = cnt(stat, m, prev, forbidden[row]);

                    f[row % 2][stat] = Math.max(f[row % 2][stat], f[(row - 1) % 2][prev] + cnt);
                }

            }

        }

        return Arrays.stream(f[n % 2]).max().orElse(0);
    }

    private static int cnt(int stat, int m, int prev, int f) {

        int c = 0;

        for (int i = 0; i < m; i++) {
            if ((stat & (1 << i)) == 0) {
                if (i + 1 < m && (stat & (1 << i + 1)) == 0 && (prev & (1 << i)) == 0 && (prev & (1 << i + 1)) == 0
                        && (f & (1 << i)) == 0 && (f & (1 << i + 1)) == 0
                ) {
                    c++;
                    i++;
                }
            } else {
                c++;
            }
        }

        return c;
    }


}
