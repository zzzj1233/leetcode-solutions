package com.zzzj.leet;

/**
 * @author zzzj
 * @create 2021-10-26 18:41
 */
public class Leet63 {

    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles(new int[][]{{1}}));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        obstacleGrid[0][0] = 1;

        for (int i = 1; i < m; i++) {
            int val = obstacleGrid[i][0];
            if (val == 1) {
                obstacleGrid[i][0] = 0;
            } else if (val == 0 && obstacleGrid[i - 1][0] == 1) {
                obstacleGrid[i][0] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            int val = obstacleGrid[0][i];
            if (val == 1) {
                obstacleGrid[0][i] = 0;
            } else if (val == 0 && obstacleGrid[0][i - 1] == 1) {
                obstacleGrid[0][i] = 1;
            }
        }

        for (int i = 1; i < m; i++) {

            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                    continue;
                }
                obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
            }

        }

        return obstacleGrid[m - 1][n - 1];
    }

}
