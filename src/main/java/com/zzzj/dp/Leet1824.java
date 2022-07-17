package com.zzzj.dp;

import com.zzzj.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author Zzzj
 * @create 2022-07-10 18:59
 */
public class Leet1824 {


    public static void main(String[] args) {
//        System.out.println(minSideJumps(new int[]{0, 1, 2, 3, 0}));
//        System.out.println(minSideJumps(new int[]{0, 1, 1, 3, 3, 0}));
//        System.out.println(minSideJumps(new int[]{0, 2, 1, 0, 3, 0}));

        for (int i = 0; i < 1000; i++) {
            int[] arr = ArrayUtil.generateArray(1000, 1, 3);
            arr[0] = 0;
            arr[arr.length - 1] = 0;
            if (minSideJumps(arr) != minSideJumps2(arr)) {
                System.out.println(Arrays.toString(arr));
                System.out.println(minSideJumps(arr));
                System.out.println(minSideJumps2(arr));
                return;
            }
        }

        System.out.println("ok");
    }


    // 空间压缩
    public static int minSideJumps2(int[] obstacles) {

        int N = obstacles.length;

        int[] dp = new int[4];

        dp[1] = 1;
        dp[3] = 1;

        int[] copy = new int[4];
        copy[1] = 1;
        copy[3] = 1;

        for (int i = 1; i < obstacles.length - 1; i++) {

            for (int j = 1; j < 4; j++) {

                // 路障
                int obstacle = obstacles[i];

                if (obstacle == j) {
                    copy[j] = Integer.MAX_VALUE;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = 1; k < 4; k++) {
                        if (k == obstacle) {
                            continue;
                        }
                        min = Math.min(min, dp[k]);
                    }

                    if (min != Integer.MAX_VALUE) {
                        copy[j] = Math.min(dp[j], min + 1);
                    }
                }
            }

            for (int j = 1; j < copy.length; j++) {
                dp[j] = copy[j];
            }

        }

        return Math.min(dp[1], Math.min(dp[2], dp[3]));
    }

    public static int minSideJumps(int[] obstacles) {

        int N = obstacles.length;

        int[][] dp = new int[N - 1][4];

        dp[0][1] = 1;
        dp[0][3] = 1;

        for (int i = 1; i < obstacles.length - 1; i++) {

            for (int j = 1; j < 4; j++) {

                // 路障
                int obstacle = obstacles[i];

                if (obstacle == j) {
                    dp[i][j] = Integer.MAX_VALUE;
                } else {
                    dp[i][j] = dp[i - 1][j] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[i - 1][j];

                    int min = Integer.MAX_VALUE;
                    for (int k = 1; k < 4; k++) {
                        if (k == j || k == obstacle) {
                            continue;
                        }
                        min = Math.min(min, dp[i - 1][k]);
                    }

                    if (min != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], min + 1);
                    }
                }
            }

        }

        return Math.min(dp[N - 2][1], Math.min(dp[N - 2][2], dp[N - 2][3]));
    }


}
