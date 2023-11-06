package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-11-06 14:22
 */
public class Leet1691 {

    public static void main(String[] args) {

//        System.out.println(maxHeight(LeetUtils.convertInts("[[50,45,20],[95,37,53],[45,23,12]]")));

        System.out.println(maxHeight(LeetUtils.convertInts("[[38,25,45],[76,35,3]]")));

    }

    public static int maxHeight(int[][] cuboids) {

        Arrays.sort(cuboids, (o1, o2) -> (o2[0] + o2[1] + o2[2]) - (o1[0] + o1[1] + o1[2]));

        int N = cuboids.length;

        int[][] dp = new int[N][6];

        int[][] ways = new int[6][];
        ways[0] = new int[]{0, 1, 2};
        ways[1] = new int[]{0, 2, 1};
        ways[2] = new int[]{1, 0, 2};
        ways[3] = new int[]{1, 2, 0};
        ways[4] = new int[]{2, 1, 0};
        ways[5] = new int[]{2, 0, 1};

        int ans = 0;

        for (int i = 0; i < N; i++) {

            int[] cuboid = cuboids[i];

            for (int j = 0; j < 6; j++) {

                int width = cuboid[ways[j][0]];

                int length = cuboid[ways[j][1]];

                int height = cuboid[ways[j][2]];

                for (int x = 0; x < i; x++) {

                    int[] other = cuboids[x];

                    for (int y = 0; y < 6; y++) {

                        int w = other[ways[y][0]];

                        int l = other[ways[y][1]];

                        int h = other[ways[y][2]];

                        if (w >= width && l >= length && h >= height) {
                            dp[i][j] = Math.max(
                                    dp[i][j],
                                    dp[x][y]
                            );
                        }

                    }
                }

                dp[i][j] += height;

                ans = Math.max(ans, dp[i][j]);
            }

        }

        return ans;
    }

}
