package com.zzzj.contest.week345;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;

/**
 * @author zzzj
 * @create 2023-08-01 14:58
 */
public class Leet2684 {

    public static void main(String[] args) {

        System.out.println(maxMoves(LeetUtils.convertInts("[[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]")));

        System.out.println(maxMoves(LeetUtils.convertInts("[[3,2,4],[2,1,9],[1,1,7]]")));

        System.out.println(maxMoves(LeetUtils.convertInts("[[65,200,263,220,91,183,2,187,175,61,225,120,39],[111,242,294,31,241,90,145,25,262,214,145,71,294],[152,25,240,69,279,238,222,9,137,277,8,143,143],[189,31,86,250,20,63,188,209,75,22,127,272,110],[122,94,298,25,90,169,68,3,208,274,202,135,275],[205,20,171,90,70,272,280,138,142,151,80,122,130],[284,272,271,269,265,134,185,243,247,50,283,20,232],[266,236,265,234,249,62,98,130,122,226,285,168,204],[231,24,256,101,142,28,268,82,111,63,115,13,144],[277,277,31,144,49,132,28,138,133,29,286,45,93],[163,96,25,9,3,159,148,59,25,81,233,127,12],[127,38,31,209,300,256,15,43,74,64,73,141,200]]")));

    }

    public static int maxMoves(int[][] grid) {

        int M = grid.length;

        int N = grid[0].length;

        int ans = 0;

        int[][] dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < M; i++) {
            dp[i][0] = 0;
        }


        for (int j = 1; j < N; j++) {

            for (int i = 0; i < M; i++) {

                for (int[] dir : DIRS) {
                    int r = dir[0] + i;
                    int c = dir[1] + j;

                    if (r >= 0 && r < grid.length && c >= 0 && c < grid[r].length && grid[r][c] < grid[i][j] && dp[r][c] != -1) {
                        dp[i][j] = Math.max(dp[i][j], dp[r][c] + 1);
                        ans = Math.max(ans, dp[i][j]);
                    }
                }

            }

        }

        return ans;
    }

    static int[][] DIRS = {
            {-1, -1},
            {0, -1},
            {1, -1},
    };

}
