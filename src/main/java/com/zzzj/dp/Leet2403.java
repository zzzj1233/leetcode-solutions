package com.zzzj.dp;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2024-01-23 15:31
 */
public class Leet2403 {

    public static void main(String[] args) {

        System.out.println(minimumTime(new int[]{3, 1, 4}));

        System.out.println(minimumTime(new int[]{1, 1, 4}));

        System.out.println(minimumTime(new int[]{1, 2, 4, 9}));

    }

    public static long minimumTime(int[] power) {

        int N = power.length;

        int limit = 1 << N;

        long[][] f = new long[2][limit];

        Arrays.fill(f[0], Long.MAX_VALUE);
        Arrays.fill(f[1], Long.MAX_VALUE);

        f[0][0] = 0;

        for (int killed = 1; killed <= N; killed++) {

            for (int stat = 0; stat < limit; stat++) {

                if (Integer.bitCount(stat) != killed) {
                    continue;
                }

                for (int i = 0; i < N; i++) {

                    if ((stat & (1 << i)) != 0) {

                        int prevStat = stat ^ (1 << i);

                        if (f[(killed - 1) % 2][prevStat] == Integer.MAX_VALUE)
                            continue;

                        f[killed % 2][stat] = Math.min(
                                f[killed % 2][stat],
                                f[(killed - 1) % 2][prevStat] + (power[i] % killed == 0 ? power[i] / killed : power[i] / killed + 1)
                        );

                    }

                }

            }

        }

        return f[N % 2][limit - 1];
    }

}
