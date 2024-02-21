package com.zzzj.dp;

import com.zzzj.leet.LeetUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zzzj
 * @create 2024-01-30 14:48
 */
public class Leet2328 {

    public static void main(String[] args) {

        System.out.println(countPaths(LeetUtils.convertInts("[[1,1],[3,4]]")));

        System.out.println(countPaths(LeetUtils.convertInts("[[1],[2]]")));

    }

    static final int MOD = 1000000007;

    public static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {-1, 0},
            {0, -1}
    };

    public static int countPaths(int[][] grid) {

        int N = grid.length;

        int M = grid[0].length;

        int[][] f = new int[N][M];

        int[][] nums = new int[N * M][];

        int index = 0;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                nums[index++] = new int[]{i, j, grid[i][j]};

        for (int i = 0; i < N; i++)
            Arrays.fill(f[i], 1);

        Arrays.sort(nums, Comparator.comparingInt(o -> o[2]));

        for (int i = 0; i < nums.length; i++) {

            int[] num = nums[i];

            int x = num[0];

            int y = num[1];

            int v = num[2];

            for (int[] dir : DIRS) {

                int row = x + dir[0];

                int col = y + dir[1];

                if (row >= 0 && col >= 0 && row < N && col < M && grid[row][col] < v)
                    f[x][y] = (f[x][y] + f[row][col]) % MOD;
            }

        }

        int ans = 0;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                ans = (ans + f[i][j]) % MOD;

        return ans;
    }


}
