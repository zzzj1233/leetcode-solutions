package com.zzzj.acw;

import java.util.Arrays;
import java.util.Scanner;

public class Q4957 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        for (int time = 0; time < T; time++) {

            int N = scanner.nextInt();

            int[][] plane = new int[N][3];

            for (int i = 0; i < N; i++) {
                // Ti 时刻到达机场上空
                plane[i][0] = scanner.nextInt();
                // 继续盘旋 Di 个单位时间 : 即它最早可以于 Ti 时刻开始降落，最晚可以于 Ti+Di 时刻开始降落
                plane[i][1] = scanner.nextInt();
                // 降落过程需要 Li 个单位时间
                plane[i][2] = scanner.nextInt();
            }

            // 判断是否可以安全降落
            int limit = 1 << N;

            long[] dp = new long[limit];

            Arrays.fill(dp, Integer.MAX_VALUE);

            dp[0] = 0;

            for (int stat = 0; stat < limit; stat++) {

                for (int current = 0; current < N; current++) {

                    if ((stat & (1 << current)) == 0)
                        continue;

                    int[] p = plane[current];

                    int prevStat = stat - (1 << current);

                    if (dp[prevStat] <= p[0] + p[1]) {
                        dp[stat] = Math.min(
                                dp[stat],
                                Math.max(dp[prevStat], p[0]) + p[2]
                        );
                    }
                }
            }

            if (dp[limit - 1] < Integer.MAX_VALUE)
                System.out.println("YES");
            else
                System.out.println("NO");
        }

    }

}
