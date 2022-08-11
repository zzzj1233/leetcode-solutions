package com.zzzj.leet;

/**
 * @author Zzzj
 * @create 2022-08-05 22:48
 */
public class Leet2128 {


    public static boolean removeOnes(int[][] grid) {

        int N = grid.length;
        int M = grid[0].length;


        for (int i = 1; i < N; i++) {

            for (int j = 1; j < M; j++) {

                if ((grid[i][j] ^ grid[0][j]) != (grid[0][0] ^ grid[i][0])) {
                    return false;
                }

            }

        }

        return true;
    }

    public static boolean removeOnes2(int[][] grid) {

        int N = grid.length;
        int M = grid[0].length;


        for (int i = 1; i < N; i++) {

            for (int j = 1; j < M; j++) {

                if (grid[i][0] == grid[0][0]) {
                    if (grid[i][j] != grid[0][j]) {
                        return false;
                    }
                } else {
                    if (grid[i][j] == grid[0][j]) {
                        return false;
                    }
                }

            }

        }

        return true;
    }

}
